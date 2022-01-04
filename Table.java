import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * The furniture on which customers' food is placed.
 * 
 * @author Ryan Lin
 * @author Eric Wang
 * @version 4
 */
public class Table extends Equipment
{
    /**the chairs that correspond to this table*/
    public Chair[]chairs; //the chairs that correspond to this table
    private boolean horizontal; //true if the table is horizontally placed
    private int offsetX, offsetY;
    
    /**
     * Creates a Table
     * @param horizontal true if the table is aligned horizontally, otherwise false
     */
    public Table(boolean horizontal)
    {
        super(200, 500, 0, 2);
        this.horizontal = horizontal;
        //set display images
        images = new GreenfootImage[3];
        if(horizontal) {
            for(int i = 0; i<images.length; i++) {
                images[i] = new GreenfootImage("tablehorilvl"+(i+1)+".png");
            }
        } else {
            for(int i = 0; i<images.length; i++) {
                images[i] = new GreenfootImage("tablevertlvl"+(i+1)+".png");
            }
        }
        setImage(images[0]);
        //set the placement of chairs
        chairs = new Chair[4];
        if(horizontal) {
            offsetX = getImage().getWidth()/2 - (int)(0.25 * getImage().getWidth());
            offsetY = getImage().getHeight()/2 + (int) (0.3 * getImage().getHeight());
            chairs[0] = new Chair(2);
            chairs[1] = new Chair(2);
            chairs[2] = new Chair(0);
            chairs[3] = new Chair(0);
        } else {
            offsetX = getImage().getWidth()/2 + (int) (0.3 * getImage().getWidth());
            offsetY = getImage().getHeight()/2 - (int) (0.25 * getImage().getHeight());
            chairs[0] = new Chair(1);
            chairs[1] = new Chair(3);
            chairs[2] = new Chair(1);
            chairs[3] = new Chair(3);
        }
    }
    
    /**
     * Place chairs in the world when the table is added to the world
     */
    public void addedToWorld(World world)
    {
        super.addedToWorld(world);
        //upper left chair
        getWorld().addObject(chairs[0], getX() - offsetX, getY() - offsetY);
        //upper right chair
        getWorld().addObject(chairs[1], getX() + offsetX, getY() - offsetY);
        //lower left chair
        getWorld().addObject(chairs[2], getX() - offsetX, getY() + offsetY);
        //lower right chair
        getWorld().addObject(chairs[3], getX() + offsetX, getY() + offsetY);
    }
    
    /**
     * Set levelup images
     * @return the images at all levels for table
     */
    protected GreenfootImage[] getImages()
    {
        return images;
    }
    
    /**
     * Makes the table level up
     */
    public void levelUp()
    {
        super.levelUp();
        for(int i = 0; i<chairs.length; i++)
        {
            chairs[i].levelUp();
        }
    }
}
