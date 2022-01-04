import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Chair here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chair extends Equipment
{
    /**true if the chair is taken*/
    boolean occupied; //if the chair is taken
    private int direction = 0; //0=up, 1=right, 2=down, 3=left
    
    /**
     * Creates a Chair
     * @param direction that the chair is facing, 0 for up, 1 for right, 2 for down, and 3 for left
     */
    public Chair(int direction)
    {
        super(50, 1000000000, 0, 2);
        this.direction = direction;
        this.occupied = false;
        //set images
        images = new GreenfootImage[3];
        for(int i = 0; i<images.length; i++){
            images[i] = new GreenfootImage("chairlvl"+(i+1)+".png");
            images[i].rotate(180 + direction * 90);
        }
    }
    
    /**
     * set the level up images
     * @return the images at all levels for the chair
     */
    protected GreenfootImage[] getImages()
    {
        return this.images;
    }
    
    /**
     * Get the direction that the chair is facing
     * @return 0 for up, 1 for right, 2 for down, and 3 for left
     */
    public int getDirection()
    {
        return this.direction;
    }
}
