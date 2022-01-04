import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Serves food to customer.
 * 
 * @author Rachel Tong
 * @author Gordon Xie
 * @author Ryan Lin
 * @version 2
 */
public class Server extends Person
{
    private Food dish; //the food the server is currently serving
    private int hourlyWage = 10; //the amount of money the server is paid hourly
    private boolean goBack = false; //true if the server is walking back to the counter
    private boolean serve = false; //true if the server is walking towards a customer
    
    /**
     * Creates a server
     */
    public Server()
    {
        super(3, 5);
    }
    
    /**
     * Sets the images of the server
     */
    protected void setImages()
    {
        walkLeftImage = new GreenfootImage[6];
        walkUpImage = walkLeftImage;
        for(int i = 0; i<walkLeftImage.length; i++)
        {
            walkLeftImage[i] = new GreenfootImage("waiterDeliver"+(i+1)+".png");
            walkLeftImage[i].scale((int)(((double)60/walkLeftImage[i].getHeight()) * walkLeftImage[i].getWidth()), 60);
        }
        walkRightImage = new GreenfootImage[6];
        walkDownImage = walkRightImage;
        for(int i = 0; i<walkRightImage.length; i++)
        {
            walkRightImage[i] = new GreenfootImage("waiterBack"+(i+1)+".png");
            walkRightImage[i].scale((int)(((double)60/walkRightImage[i].getHeight()) * walkRightImage[i].getWidth()), 60);
        }
        defaultImage = walkLeftImage[0];
    }
    
    /**
     * Act - do whatever the Server wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        if(serve){ //if the server is currently serving, keep on serving food
            serveFood(dish);
        } else if(goBack){ //if the server is current moving back, go back to the counter
            goBack();
        } else { //if the server is not serving or moving back, they are waiting for a dish
            waiting();
        }
        if(world.clock.ring()) //if the hour has changed, pay the server
        {
            getPaid();
        }
    }
    
    /**
     * Waits for a dish to appear
     */
    private void waiting()
    {
        //if the counter is not empty and the server is next to the counter take the dish and start to serve it
        if(!world.counter.prepared.isEmpty() && getY() == 500){
            dish = world.counter.removeCookedDish();
            serve = true;
        }
    }
    
    /**
     * Serve food to customer
     * @param dish The food that the server needs to deliver
     */
    private void serveFood(Food dish)
    {
        //if the customer has left or is currently leaving go back to the counter
        if(dish.getCustomer() == null || dish.getCustomer().vx != 0){
            goBack = true;
            serve = false;
        }
        //if the server is not yet in the horizontal hallway move up, otherwise move left or right to the customer
        if(getY() > world.frontOfLine){
            goUp();
        } else {
            if(dish.getCustomer().getX() <= getX()) {
                goLeft();
            } else {
                goRight();
            }
        }
        //if the server is close to the customer serve the dish
        if(dish.getCustomer().getWorld() != null && dish.getCustomer().getChair().occupied && Math.abs(dish.getCustomer().getX() - getX()) <= speed){
            //assign the dish to the customer
            dish.getCustomer().dish = dish;
            dish.getCustomer().timer.mark();
            //place the dish in front of the customer based on which way their chair is facing
            switch(dish.getCustomer().getChair().getDirection()){
                case 0: //facing up
                    world.addObject(dish, dish.getCustomer().getX(), dish.getCustomer().getY() - 40);
                    break;
                case 1: //facing right
                    world.addObject(dish, dish.getCustomer().getX() + 40, dish.getCustomer().getY());
                    dish.getImage().rotate(90);
                    break;
                case 2: //facing down
                    world.addObject(dish, dish.getCustomer().getX(), dish.getCustomer().getY() + 40);
                    dish.getImage().rotate(180);
                    break;
                case 3: //facing left
                    world.addObject(dish, dish.getCustomer().getX() - 40, dish.getCustomer().getY());
                    dish.getImage().rotate(270);
                    break;
            }
            //go back to the counter
            goBack = true;
            serve = false;
        }
    }
    
    /**
     * Go back to the waiting spot
     */
    private void goBack()
    {
        //the server moves towards the counter
        if(getY() < 500){
            //if the server is not in the vertical hallway move towards it
            if(getX() > 550) {
                goLeft();
            } else if (getX() < 550) {
                goRight();
            }
            //if the server is in the vertical hallway move down
            if(Math.abs(getX() - 550) <= speed) {
                goDown();
            }
        } else {
            //the serve has arrived to the counter
            setLocation(550, 500);
            stop();
            goBack = false;
        }
    }
    
    /**
     * Pays the server every hour
     */
    private void getPaid()
    {
        world.money -= hourlyWage; //decrease the diner's money
        world.salaryexp += hourlyWage; //increase the sales expense
    }
    
    /**
     * Gets whether or not the server is currently delivering food to a customer
     * @return true if the server is delivering food to a customer
     */
    public boolean isServing()
    {
        return this.serve;
    }
}
