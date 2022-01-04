import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A steak.
 * 
 * @author Eric Wang
 * @author Rachel Tong
 * @version 4
 */
public class Steak extends Food
{
    /**
     * Creates a steak.
     * @param customer the customer that ordered the steak.
     */
    public Steak(Customer customer)
    {
        super(new GreenfootImage("images/steak.png"), customer, 2000, 5000, 3, 50, 25);
    }
}
