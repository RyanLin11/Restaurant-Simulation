import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A red fruit.
 * 
 * @author Eric Wang
 * @author Rachel Tong
 * @version 4
 */
public class Apple extends Food
{
    /**
     * Creates an apple.
     * @param customer the customer that ordered the apple.
     */
    public Apple(Customer customer)
    {
        super(new GreenfootImage("images/apple1.png"), customer, 500, 5000, 0, 10, 5);
    }
}
