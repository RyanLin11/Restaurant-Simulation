import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A customer with more money and food choices.
 * 
 * @author Gordon Xie
 * @author Rachel Tong
 * @version 4
 */
public class Rich extends Customer
{
    /**
     * Creates a rich customer.
     */
    public Rich()
    {
        super(100, 0.1);
    }
    /**
     * Sets the images of the rich customer.
     */
    protected void setImages()
    {
        walkUpImage = new GreenfootImage[4];
        walkLeftImage = new GreenfootImage[4];
        walkRightImage = new GreenfootImage[4];
        walkDownImage = walkLeftImage;
        stillImage = new GreenfootImage[4];
        for(int i = 0; i<walkUpImage.length; i++)
        {
            walkUpImage[i] = new GreenfootImage("images/richUp"+(i+1)+".png");
        }
        for(int i = 0; i<walkLeftImage.length; i++)
        {
            walkLeftImage[i] = new GreenfootImage("images/richLeft"+(i+1)+".png");
        }
        for(int i = 0; i<walkRightImage.length; i++)
        {
            walkRightImage[i] = new GreenfootImage("images/richRight"+(i+1)+".png");
        }
        for(int i = 0; i<stillImage.length; i++)
        {
            stillImage[i] = new GreenfootImage("images/rich"+i+".png");
        }
    }  
}
