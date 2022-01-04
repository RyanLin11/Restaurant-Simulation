import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The top of a counter.
 * 
 * @author Ryan Lin
 * @version 4
 */
public class Countertop extends Actor
{
    /**
     * Creates a Countertop.
     * @param width the width of the countertop
     * @param height the height of the countertop
     */
    public Countertop(int width, int height)
    {
        getImage().scale(width, height);
    } 
}
