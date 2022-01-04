import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An image that disappears after a set amount of act() calls.
 * 
 * @author Rachel Tong
 * @version 4
 */
public class QuickImage extends Actor
{
    private int width, height, showTime;
    
    /**
     * Creates an image that disappears after a set amount of time.
     * @param width the width of the image
     * @param height the height of the image
     * @param showTime the amount of time the quickImage lasts for, measured in acts
     */
    public QuickImage(int width, int height, int showTime)
    {
        getImage().scale(width, height);
        this.showTime = showTime;
    }
    
    /**
     * Counts down the time to disappearance
     */
    public void act() 
    {
        showTime--;
        if(showTime <= 0)
        {
            getWorld().removeObject(this);
        }
    }    
}
