import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A burger.
 * 
 * @author Eric Wang
 * @author Rachel Tong
 * @version 4
 */
public class Burger extends Food
{
    /**
     * Creates a burger.
     * @param customer the customer that ordered the burger
     */
    public Burger(Customer customer)
    {
        super(new GreenfootImage("images/burger.png"), customer, 1000, 5000, 2, 30, 12);
    }   
}
