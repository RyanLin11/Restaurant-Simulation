import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A customer that has a greater influence on rating.
 * 
 * @author Gordon Xie
 * @author Rachel Tong
 * @version 4
 */
public class Inspector extends Customer
{
    /**
     * Creates an inspector customer.
     */
    public Inspector()
    {
        super(50, 0.3);
    }   
    
    /**
     * Sets the images of the inspector.
     */
    protected void setImages()
    {
        walkUpImage = new GreenfootImage[6];
        walkLeftImage = new GreenfootImage[6];
        walkRightImage = new GreenfootImage[6];
        walkDownImage = new GreenfootImage[6];
        stillImage = new GreenfootImage[4];
        for(int i = 0; i<walkUpImage.length; i++)
        {
            walkUpImage[i] = new GreenfootImage("images/inspectorUp"+(i+1)+".png");
        }
        for(int i = 0; i<walkLeftImage.length; i++)
        {
            walkLeftImage[i] = new GreenfootImage("images/inspectorLeft"+(i+1)+".png");
            walkDownImage[i] = walkLeftImage[i];
        }
        for(int i = 0; i<walkRightImage.length; i++)
        {
            walkRightImage[i] = new GreenfootImage("images/inspectorRight"+(i+1)+".png");
        }
        for(int i = 0; i<stillImage.length; i++)
        {
            stillImage[i] = new GreenfootImage("images/inspector"+i+".png");
        }
    }
}
