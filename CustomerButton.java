import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button to spawn servers.
 * 
 * @author Ryan Lin
 * @version 11.10
 */
public class CustomerButton extends Button
{
    private Diner world;
    
    /**
     * Creates a button to spawn customers.
     */
    public CustomerButton()
    {
        super("Add Customer");
    }
    
    /**
     * Called when the object is added to the world.
     * @param world the world that this actor belongs to
     */
    public void addedToWorld(World world)
    {
        this.world = (Diner) world;
    }
    
    /**
     * Responds to button clicks.
     */
    public void respond()
    {
        world.spawnRate = 3;
    }
}