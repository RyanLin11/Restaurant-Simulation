import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The title screen of the simulation.
 * 
 * @author Rachel Tong
 * @version 3
 */
public class TitleScreen extends World
{
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        Greenfoot.start();
        Label next = new Label("Press Space To Continue", 40);
        addObject(next, 400, 500);
        Label title = new Label("Restaurant Simulation", 60);
        addObject(title, 400, 100);
    }
    
    /**
     * Takes the user to the settings world when the space bar is pressed
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("space")) Greenfoot.setWorld(new Settings());
    }
}
