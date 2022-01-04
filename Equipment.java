import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Appliances and furniture that can be levelled up.
 * 
 * @author Eric Wang
 * @author Ryan Lin
 * @version 4
 */
public abstract class Equipment extends Actor
{
    private int upgradeMoney;
    private int upgradeMoneyNeeded;
    private int level;
    private int maxLevel;
    /**The simulation the equipment belongs to*/
    protected Diner world;
    /**The levelup images of the equipment*/
    protected GreenfootImage[]images;
    
    /**
     * Creates an equipment
     * @param upgradeMoney the money used towards an upgrade
     * @param upgradeMoneyNeeded the threshold that triggers an upgrade
     * @param level the current level of the equipment
     * @param maxLevel the maximum level that the equipment can reach
     */
    public Equipment(int upgradeMoney, int upgradeMoneyNeeded, int level, int maxLevel)
    {
        this.upgradeMoney = upgradeMoney;
        this.upgradeMoneyNeeded = upgradeMoneyNeeded;
        this.level = level;
        this.maxLevel = maxLevel;
    }
    
    /**
     * Called when the equipment is added to the world
     */
    public void addedToWorld(World world)
    {
        this.world = (Diner) world;
        setImage(getImages()[level]);
    }
    
    /**
     * Updates the equipment
     */
    public void act() 
    {
        if(world.money > upgradeMoneyNeeded && level < maxLevel)
        {
            levelUp();
        }
    }
    
    /**
     * Action the equipment takes when levelling up
     */
    protected void levelUp()
    {
        level++; //increase level by 1
        setImage(getImages()[level]); //update the image
        world.money -= upgradeMoney; //decrease the world's money
        world.upgradecost += upgradeMoney; //increase the expenses associated with upgrading
    }
    
    /**
     * Sets the level up images
     */
    protected abstract GreenfootImage[] getImages();
    
    /**
     * Returns the level of the equipment
     * @return the level of the equipment
     */
    public int getLevel()
    {
        return this.level;
    }
}
