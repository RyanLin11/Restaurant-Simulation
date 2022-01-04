import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A lobster.
 * 
 * @author Eric Wang
 * @author Rachel Tong
 * @version 4
 */
public class Lobster extends Food
{
    /**
     * Creates a lobster.
     * @param customer the customer that ordered the lobster.
     */
    public Lobster(Customer customer)
    {
        super(new GreenfootImage("images/lobster.png"), customer, 2000, 5000, 3, 90, 50);
    }   
}
