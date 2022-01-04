import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The food that the diner serves its customers. 
 * 
 * @author Ryan Lin
 * @version V4
 */
public abstract class Food extends Actor
{
    private int cookTime = 5000; //the amount of time to cook the food
    private int eatTime = 10000; //the amount of time to eat the food
    private double price = 0; //the price of the food
    private double cost = 0; //the money it takes to cook the food
    private boolean poisoned; //true if the food is poisoned
    private Customer customer; //the customer who ordered it
    private GreenfootImage image; //the food image
    private int imageHeight = 32; //desired image height
    
    /**
     * Simple constructor for Food
     * @param image the image of the food
     * @param customer the customer who ordered the food
     */
    public Food(GreenfootImage image, Customer customer)
    {
        this(image, customer, 5000, 10000);
    }
    
    /**
     * Customized constructor for Food
     * @param image the image of the food
     * @param customer the customer who ordered the food
     * @param cookTime the number of milliseconds to cook the food
     * @param eatTime the number of milliseconds it takes for the customer to eat the food
     */
    public Food(GreenfootImage image, Customer customer, int cookTime, int eatTime)
    {
       this(image, customer, cookTime, eatTime, 0, 10, 5);
    }
    
    /**
     * Most customizable constructor for Food
     * @param the image of the food
     * @param customer the customer who ordered the food
     * @param cookTime the number of milliseconds to cook the food
     * @param eatTime the number of milliseconds it takes for the customer to eat the food
     * @param poisonChance the percentage chance that the food is poisoned
     * @param price the price of the food
     * @param cost the cost of producing the food
     */
    public Food(GreenfootImage image, Customer customer, int cookTime, int eatTime, int poisonChance, double price, double cost)
    {
        image.scale((int)(((double)imageHeight/image.getHeight()) * image.getWidth()),imageHeight);
        this.image = image;
        this.customer = customer;
        this.cookTime = cookTime;
        this.eatTime = eatTime;
        this.poisoned = Greenfoot.getRandomNumber(100) <= poisonChance;
        this.price = price;
        this.cost = cost;
        setImage(image);
    }
    
    /**
     * Gets whether or not the food is poisoned
     * @return true if the food is poisoned, otherwise false
     */
    public boolean isPoisoned()
    {
        return this.poisoned;
    }
    
    /**
     * Gets the amount of time it takes for food to be cooked
     * @return the number of milliseconds for the food to be cooked
     */
    public int getCookTime()
    {
        return this.cookTime;
    }
    
    /**
     * Sets the amount of time it takes for food to be cooked
     * @param the number of milliseconds for the food to be cooked
     */
    public void setCookTime(int cookTime)
    {
        this.cookTime = cookTime;
    }
    
    /**
     * Gets the price of the dish
     * @return the price of the dish
     */
    public double getPrice()
    {
        return this.price;
    }
    
    /**
     * Gets the amount of time it takes for the food to be consumed
     * @return the number of milliseconds for the food to be consumed
     */
    public int getEatTime()
    {
        return this.eatTime;
    }
    
    /**
     * Gets the cost of the food
     * @return the amount of money it costs to cook a dish
     */
    public double getCost()
    {
        return this.cost;
    }
    
    /**
     * Get the customer who ordered the dish
     * @return the customer that ordered the dish
     */
    public Customer getCustomer()
    {
        return this.customer;
    }
}
