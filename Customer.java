import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A person that orders food and generates revenue for the business.
 * 
 * @author Rachel Tong
 * @author Gordon Xie
 * @author Ryan Lin
 * @version 4
 */
public abstract class Customer extends Person
{
    private Chair chair;
    private int id; //the customer is the id'th customer added to the world
    private boolean inLine; //true if customer is in line and waiting for a seat
    private boolean ordered; //true if the customer has already placed an order
    private int orderTime; //time it takes from the moment the customer sits down to the time the customer places an order
    private int maxOrderTime; //the maximum amount of time a customer will wait for an order
    private int maxLineTime; //the maximum amount of time a customer will wait in line
    /**A general purpose timer for the customer*/
    protected SimpleTimer timer; //a general purpose timer for the customer
    /**The dish that the customer orders*/
    protected Food dish; //the dish that the customer orders
    /**The amount of money the customer has*/
    public int wallet;
    private double ratingInfluence; //influence on the restaurant's rating
    /**The images of the customer remaining still facing different directions*/
    protected GreenfootImage[]stillImage; //the images of the character facing in different directions while sitting down
    private GreenfootSound moneySound; //the sound that plays when the customer pays
    
    /**
     * No argument constructor for customer
     */
    public Customer()
    {
        this(25, 0.1);
    }
    
    /**
     * Simple constructor for customer
     * @param wallet the amount of money the customer has
     * @param ratingInfluence the amount of influence the customer has on the rating
     */
    public Customer(int wallet, double ratingInfluence)
    {
        this(wallet, ratingInfluence, 5000, 10000, 10000);
    }
    
    /**
     * Customizable constructor for customer
     * @param wallet the amount of money the customer has
     * @param ratingInfluence the amount of influence the customer has on the rating
     * @param orderTime the amount of time the customer takes before ordering
     * @param maxOrderTime the maximum amount of time the customer will wait for their order before leaving
     * @param maxLineTime the maximum amount of time the customer will wait in line before leaving
     */
    public Customer(int wallet, double ratingInfluence, int orderTime, int maxOrderTime, int maxLineTime)
    {
        super(2, 4);
        //customizable variables
        this.defaultImage = stillImage[0];
        this.wallet = wallet;
        this.ratingInfluence = ratingInfluence;
        this.orderTime = orderTime;
        this.maxOrderTime = maxOrderTime;
        this.maxLineTime = maxLineTime;
        //default set variables
        this.inLine = true; //the customer starts off in line
        this.ordered = false; //the customer has not ordered yet
        this.timer = new SimpleTimer(); //initialize timer
        this.timer.mark(); //start the timer
        //set up sound
        moneySound = new GreenfootSound("kaching.wav");
        moneySound.setVolume(85);
    }
    
    /**
     * Called when the customer is added to the world
     */
    public void addedToWorld(World world)
    {
        super.addedToWorld(world);
        this.id = this.world.numCustomers;
        this.world.numCustomers++;
    }

    /**
     * Act - do whatever the Customer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        if(inLine){ //if the person is in line
            waiting();
            if(getY() <= world.frontOfLine) { //if the customer is at the front of the line, check for a seat
                checkSeat();
            }
        } else { //the customer is not in line
            if(vx>0){ //if the customer is walking to the right, look for a table
                locateTable();
            } else if(vx==0){ //if the customer is seated
                if(!ordered) { //if they haven't ordered yet, order
                    order();
                } else { //if they have already ordered, wait or eat the food
                    eat();
                }
            } else { //if the customer is walking to the left, check if the customer has left the restaurant
                checkEdges();
            }
        }
    }
    
    /**
     * Customer is waiting in line
     */
    private void waiting()
    {
        //if there is enough space in front of the customer in line, move forward
        vy = world.frontOfLine + Math.max(0,(id - world.customersServed)*50) < getY()? -2: 0;
    }
    
    /**
     * called when the customer is walking to table
     */
    private void locateTable()
    {
        //if the customer has walked past the chair, the customer sits at the chair
        if(getX() >= chair.getX()){
            stop();
            maxOrderTime += chair.getLevel() * 2000;
            defaultImage = stillImage[chair.getDirection()];
            setLocation(chair.getX(), chair.getY());
            timer.mark();
        }
    }
    
    /**
     * Checks if a seat is open
     */
    private void checkSeat()
    {
        //if the customer waits too long, leave the restaurant
        if(timer.millisElapsed() >= maxLineTime) {
            setLocation(40,250); //walk to the hallway
            goLeft(); //start walking to the left
            inLine = false; //the customer is no longer in line
            world.customersServed++; //increment num of customers that are no longer in line
            world.rating -= ratingInfluence; //decrease the restaurant's rating by the customer's influence
            world.angryCustomers++; //increment num of customers who are angry
            return;
        }
        //Check every chair in every table to see if it is not occupied
        for(Table t: world.tables) {
            for(Chair c: t.chairs){
                //if the chair is not occupied
                if (!c.occupied){
                    c.occupied = true; //the chair is occupied
                    inLine = false; //the person is no longer in line
                    world.customersServed++; //increment num of customers that are no longer in line
                    chair = c; //assign the empty chair to the customer
                    goRight(); //walk right into the diner
                    break;
                }   
            }
            if(chair != null) break;
        }
    }
    
    /**
     * called when the customer is waiting to order or ordering
     */
    private void order()
    {
        //the customer orders once his timer is up
        if(timer.millisElapsed() >= orderTime) {
            world.counter.addOrder(this);
            ordered = true;
            timer.mark();
        }
    }
    
    /**
     * Called when the customer is waiting for order and eating
     */
    private void eat()
    {
        if(dish!=null) { //if the customer is assigned a dish
           if(timer.millisElapsed() >= dish.getEatTime()){ //if the customer has finished eating the food
               setLocation(getX(), 200); //teleport to the hallway
               goLeft(); //start walking left
               chair.occupied = false; //the chair is no longer occupied
               if(dish.isPoisoned()) { //if the food is poisoned
                   world.rating -= ratingInfluence; //the world's rating decreases
                   world.angryCustomers++; //increment the number of angry customers
                   world.addObject(new Poison(), dish.getX(), dish.getY()); //display poison symbol on the food
               } else {
                   pay(); //pay for the meal
               }
               world.removeObject(dish); //remove the food
           } 
        } else if(timer.millisElapsed() >= maxOrderTime){ //if the customer has waited too long leave the restaurant
            setLocation(getX(), 200); //teleport to the hallway
            goLeft(); //start walking left 
            chair.occupied = false; //the chair is no longer occupied
            world.rating -= ratingInfluence; //lower the diner's rating
            world.angryCustomers++; //increase the number of angry customers
        }
    }
    
    /**
     * Customer pays for their meal
     */
    private void pay()
    {
        world.money += dish.getPrice(); //increase the world's money
        world.revenue += dish.getPrice(); //increase the world's revenue
        world.happyCustomers++; //add one to the number of happy customers
        world.rating += ratingInfluence; //increase the world's rating
        moneySound.play(); //play the money sound
        getWorld().addObject(new Dollar(), dish.getX(), dish.getY()); //the customer leaves a dollar on the table
    }
    
    /**
     * Gets the chair the customer is currently sitting on
     * @return the chair the customer is siting on
     */
    public Chair getChair()
    {
        return this.chair;
    }
}
