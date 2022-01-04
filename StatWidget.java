import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.text.DecimalFormat;
/**
 * Displays the money and ratings of the restaurant
 * 
 * @author Ryan Lin 
 * @version 11.10
 */
public class StatWidget extends Actor
{
    private RatingBar ratingBar;
    private Label moneyLabel, ratingsLabel, moneyValueLabel;
    private double money;
    private DecimalFormat df = new DecimalFormat("0.00");
    
    /**
     * Creates a widget that displays money and rating information.
     */
    public StatWidget()
    {
        //initialize labels
        ratingBar = new RatingBar(5, 1, 5, 125, 25);
        moneyLabel = new Label("Money: ", 30);
        ratingsLabel = new Label("Rating: ", 30);
        moneyValueLabel = new Label("$0.00", 30);
        GreenfootImage image = new GreenfootImage("images/statboard.png");
        image.scale(225, 72);
        setImage(image);
    }
    
    /**
     * Called when the StatWidget is added to the world.
     */
    public void addedToWorld(World world)
    {
        //add the "rating: " label to the top left
        world.addObject(ratingsLabel, getX() -60, getY() -20);
        //add the stars bar to the top right
        world.addObject(ratingBar, getX() + 40, getY() -20);
        //add the "money: " label to the bottom left
        world.addObject(moneyLabel, getX() - 60, getY() + 10);
        //add the money counter to the bottom right
        world.addObject(moneyValueLabel, getX() + 10, getY() + 10);
    }
    
    /**
     * Act - do whatever the StatWidget wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //format the money value rounded to two decimal places
        moneyValueLabel.setValue("$"+df.format(Math.round(money * 100)/100.0));
        //set the location of the money value to the right place
        moneyValueLabel.setLocation(getX() + moneyValueLabel.getImage().getWidth()/2 - 10, getY() + 10);
    }
    
    /**
     * Updates the maximum rating that the widget can display
     * @param value the new maximum rating
     */
    public void setRatingMaxValue(double value)
    {
        ratingBar.setMaxValue(value);
    }
    
    /**
     * Gets the maximum rating that the widget can display
     * @return the maximum rating
     */
    public double getRatingMaxValue()
    {
        return ratingBar.getMaxValue();
    }
    
    /**
     * Updates the displayed rating
     * @param value the new rating
     */
    public void setRatingValue(double value)
    {
        ratingBar.setValue(value);
    }
    
    /**
     * Updates the displayed rating
     * @return the displayed rating
     */
    public double getRatingValue()
    {
        return ratingBar.getValue();
    }
    
    /**
     * Updates the amount of money displayed
     * @param value the new amount of money
     */
    public void setMoney(double value)
    {
        this.money = value;
    }
    
    /**
     * Gets the amount of money displayed
     * @return the amount of money displayed
     */
    public double getMoney()
    {
        return this.money;
    }
}