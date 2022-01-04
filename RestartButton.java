import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A button that brings the user back to the settings page.
 * 
 * @author Ryan Lin 
 * @version 3
 */
public class RestartButton extends Button
{
    /**
     * Creates a button that allows the user to start a new Diner simulation.
     */
    public RestartButton()
    {
        super("RESTART");
    }
    
    /**
     * Takes the user back to settings if the button is clicked
     */
    public void respond()
    {
        //go back to main simulation world
        Greenfoot.setWorld(new Settings());
    }
}
