import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The equipment that cooks food.
 * 
 * @author Ryan Lin
 * @version 2 (11.10)
 */
public class Stove extends Actor
{
    /**
     * Scales the stove image to the right size upon being added to the world
     */
    public void addedToWorld(World world)
    {
        getImage().scale((int)(((double)120/getImage().getHeight()) * getImage().getWidth()), 120);
    }  
}
