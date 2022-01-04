import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A customer with less money and therefore fewer food choices.
 * 
 * @author Gordon Xie
 * @author Rachel Tong
 * @version 4
 */
public class Poor extends Customer
{
    /**
     * Creates a poor customer.
     */
    public Poor()
    {
        super(25, 0.1);
    }    
    
    /**
     * Sets the poor customer's images
     */
    protected void setImages()
    {
        walkUpImage = new GreenfootImage[11];
        walkLeftImage = new GreenfootImage[11];
        walkRightImage = new GreenfootImage[11];
        walkDownImage = walkLeftImage;
        stillImage = new GreenfootImage[4];
        for(int i = 0; i<walkUpImage.length; i++)
        {
            walkUpImage[i] = new GreenfootImage("images/customerUp"+(i+1)+".png");
        }
        for(int i = 0; i<walkLeftImage.length; i++)
        {
            walkLeftImage[i] = new GreenfootImage("images/customerLeft"+(i+1)+".png");
        }
        for(int i = 0; i<walkRightImage.length; i++)
        {
            walkRightImage[i] = new GreenfootImage("images/customerRight"+(i+1)+".png");
        }
        for(int i = 0; i<stillImage.length; i++)
        {
            stillImage[i] = new GreenfootImage("images/customer"+i+".png");
        }
    }
}
