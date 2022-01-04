import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fries.
 * 
 * @author Eric Wang
 * @author Rachel Tong
 * @version 4
 */
public class Fries extends Food
{
    /**
     * Creates a serving of Fries.
     * @param customer the customer who ordered the fries.
     */
    public Fries(Customer customer)
    {
        super(new GreenfootImage("images/fries.png"), customer, 500, 5000, 1, 15, 7);
    }   
}
