import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A button that sets the number of servers.
 * 
 * @author Rachel Tong
 * @version 3
 */
public class SetServerButton extends Button
{
    private Settings world;
    private int numberOfServers;
    private ButtonBackground background;
    
    /**
     * Creates a button to set starting servers.
     * @param numberOfServers the desired number of servers
     */
    public SetServerButton(int numberOfServers)
    {
        super(Integer.toString(numberOfServers));
        this.numberOfServers = numberOfServers;
        this.background = new ButtonBackground();
    }
    
    /**
     * Called when the object is added to the world.
     * @param world the world that this actor belongs to
     */
    public void addedToWorld(World world)
    {
        this.world = (Settings) world;
        world.addObject(background, getX() - 2, getY() - 1);
    }
    
    /**
     * Responds to button clicks.
     */
    public void respond()
    {
        world.numServers = numberOfServers; 
        world.selectedServerButton.resetBackground();
        background.setClicked(true);
        world.selectedServerButton = this;
    }     
    
    /**
     * Resets the button's background to default
     */
    public void resetBackground()
    {
        background.setClicked(false);
    }
}