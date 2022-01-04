import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button to spawn servers.
 * 
 * @author Ryan Lin
 * @version 11.10
 */
public class ServerButton extends Button
{
    private Diner world;
    
    /**
     * Creates a button to spawn servers.
     */
    public ServerButton()
    {
        super("Add Server");
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
        world.spawnServer();
    }
}
