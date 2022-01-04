import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * A button that removes servers from the world.
 * 
 * @author Ryan Lin
 * @version 3
 */
public class RemoveServerButton extends Button
{
    private Diner world;
    
    /**
     * Creates a RemoveServerButton
     */
    public RemoveServerButton()
    {
        super("Remove Server");
    }
    
    /**
     * Called when the server is added to the world
     * @param world the world to which the server belongs
     */
    public void addedToWorld(World world)
    {
        this.world = (Diner) world;
    }
    
    /**
     * Attempts to remove a server (servers who are not currently delivering food are removed first)
     */
    public void respond()
    {
        ArrayList<Server>list = (ArrayList<Server>)world.getObjects(Server.class);
        for(Server server: list)
        {
            if(!server.isServing())
            {
                world.removeObject(server);
                return;
            }
        }
        if(!list.isEmpty())
        {
            world.removeObject(list.get(0));
        }
    }
}
