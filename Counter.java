import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
/**
 * Keeps track of the food being cooked and those waiting to be picked up.
 * 
 * @author Eric Wang
 * @author Rachel Tong
 * @author Ryan Lin
 * @version 4
 */
public class Counter extends Equipment
{
    private Queue<Food>orders = new LinkedList<>(); //the orders coming in
    /**the list of food already prepared*/
    public ArrayList<Food>prepared = new ArrayList<>(); //the list of food already prepared
    /**cooking timer*/
    public SimpleTimer timer = new SimpleTimer(); //cooking timer
    private int width, height; //the width and height of the counter
    private boolean stoveInUse; //true if the stove is currently cooking food
    private int foodCount, maxFoodCount;
    
    /**
     * Constructor for class Counter
     * @param width the width of the counter
     * @param height the height of the counter
     */
    public Counter(int width, int height)
    {
        super(150, 250, 0, 2);
        this.width = width;
        this.height = height;
        this.maxFoodCount = 4;
        stoveInUse = false;
        getImage().scale(width, height);
        timer.mark();
        images = new GreenfootImage[3];
        //set images
        for(int i = 0; i<images.length; i++) {
            images[i] = new GreenfootImage("counterlvl"+(i+1)+".jpg");
            images[i].scale(width, height);
        }
    }
    
    /**
     * Get the level up images of the counter
     * @return the level up images of the counter
     */
    protected GreenfootImage[] getImages()
    {
        return images;
    }
    
    /**
     * Act - do whatever the Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        cookDish();
        shiftDishes();
    }
    
    /**
     * Adds a food to the counter if it is cooked, and updates cooking progress
     */
    private void cookDish()
    {
        if(orders.isEmpty())
        {
            timer.mark(); //if there are no orders reset the timer to zero
        }
        else if(!stoveInUse && foodCount < maxFoodCount)
        {
            orders.peek().setCookTime((int)((1-0.25*getLevel()) * orders.peek().getCookTime()));
            world.addObject(new Timer(orders.peek().getCookTime()), 130, 550);
            stoveInUse = true;
        }
        else if(orders.peek().getCookTime() <= timer.millisElapsed() && foodCount < maxFoodCount)
        {
            //if there are orders and the timer has expired for the first food
            //remove the food and add it onto the counter
            Food f = orders.poll();
            addCookedDish(f);
            timer.mark();
        }
    }
    
    /**
     * Shifts the dishes on the counter if a dish is removed or added
     */
    private void shiftDishes()
    {
        //moves the food to a new location if the list of prepared food is modified
        int x = getX() + getImage().getWidth()/2, y = getY();
        for(Food f: prepared)
        {
            x -= f.getImage().getWidth()/2;
            f.setLocation(x, y);
            x -= f.getImage().getWidth()/2;
        }
    }
    
    /**
     * Adds an order to the end of the list
     */
    public void addOrder(Food order)
    {
        orders.add(order);
    }
    
    /**
     * Select and order food for the customer
     */
    public void addOrder(Customer customer)
    {
        //limit food options depending on how much money the customer has
        if(customer.wallet == 100)
        {
            switch(Greenfoot.getRandomNumber(6))
            {
                case 0:
                    addOrder(new Apple(customer));
                    break;
                case 1:
                    addOrder(new Burger(customer));
                    break;
                case 2:
                    addOrder(new Fries(customer));
                    break;
                case 3:
                    addOrder(new IceCream(customer));
                    break;
                case 4:
                    addOrder(new Lobster(customer));
                    break;
                case 5:
                    addOrder(new Steak(customer));
                    break;
            }
        }
        else if(customer.wallet == 50)
        {
            switch(Greenfoot.getRandomNumber(5))
            {
                case 0:
                    addOrder(new Apple(customer));
                    break;
                case 1:
                    addOrder(new Burger(customer));
                    break;
                case 2:
                    addOrder(new Fries(customer));
                    break;
                case 3:
                    addOrder(new IceCream(customer));
                    break;
                case 4:
                    addOrder(new Steak(customer));
                    break;
            }
        }
        else if(customer.wallet == 25)
        {
            switch(Greenfoot.getRandomNumber(3))
            {
                case 0:
                    addOrder(new Apple(customer));
                    break;
                case 1:
                    addOrder(new IceCream(customer));
                    break;
                case 2:
                    addOrder(new Fries(customer));
                    break;
            }
        }
    }
    
    /**
     * Removes the oldest order from the list
     */
    public void removeOrder(Food order)
    {
        orders.remove(order);
    }
    
    /**
     * Removes and returns the oldest dish prepared in the list
     * @return the oldest dish in the list that is prepared
     */
    public Food removeCookedDish()
    {
        world.removeObject(prepared.get(0)); //remove food from world
        foodCount--; //decreases the amount of food on counter
        return prepared.remove(0);
    }
    
    /**
     * Adds a food to the list of prepared foods
     */
    public void addCookedDish(Food food)
    {
        world.money -= food.getCost(); //the world's money decreases by the food's cost
        world.foodexp += food.getCost(); //food expense increases by the food's cost
        world.addObject(food, getX(), getY()); //place the food on the counter
        prepared.add(food); //add the food to the list of prepared foods on the counter
        stoveInUse = false; //stove is no longer in use
        foodCount++; //increases the amount of food on counter
    }
    
    /**
     * Action the counter takes when it is upgraded
     */
    protected void levelUp()
    {
        super.levelUp();
        maxFoodCount = Math.min(12, maxFoodCount + 4); //increase the max food count, but to a maximum of 12 foods
    }
}
