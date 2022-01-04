import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A wall that separates different sections of the restaurant.
 * 
 * @author Ryan Lin
 * @version 4
 */
public class Divider extends Actor
{
    private int width, height;
    
    /**
     * Creates a divider to separate different areas of a restaurant.
     * @param width the width of the divider
     * @param height the height of the divider
     */
    public Divider(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    
    /**
     * Sets the divider image when it is added to the world.
     */
    public void addedToWorld(World world)
    {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(Color.BLACK);
        image.fill();
        setImage(image);
    }   
}
