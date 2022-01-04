import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ice Cream.
 * 
 * @author Eric Wang
 * @author Rachel Tong
 * @version 4
 */
public class IceCream extends Food
{
    /**
     * Creates ice cream.
     * @param customer the customer that ordered the ice cream.
     */
    public IceCream(Customer customer)
    {
        super(new GreenfootImage("images/sundae.png"), customer, 500, 5000, 1, 25, 10);
    }
}
