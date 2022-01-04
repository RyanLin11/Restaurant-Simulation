import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A button that resumes the simulation.
 * 
 * @author Ryan Lin
 * @version 3
 */
public class ContinueButton extends Button
{
    private End world;
    
    /**
     * Creates a ContinueButton
     */
    public ContinueButton()
    {
        super("CONTINUE");
    }
    
    /**
     * Called when the ContinueButton is added to the world.
     * @param world the world to which the ContinueButton is added.
     */
    public void addedToWorld(World world)
    {
        this.world = (End) world;
    }
    
    /**
     * Respond to button clicks.
     */
    protected void respond()
    {
        //reset the simulation for another day and go back into the simulation
        world.getSimulation().reset();
        Greenfoot.setWorld(world.getSimulation());
    }
}
