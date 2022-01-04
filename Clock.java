import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.text.DecimalFormat;
/**
 * A clock displays the time. Its speed can be adjusted and it can be started or stopped.
 * 
 * @author Ryan Lin
 * @version 11.06
 */
public class Clock extends Actor
{
    private long baseline = 0; //time when the clock is started
    private long ms, startingms = 0; //minutes since midnight
    private int ratio = 0; //the ratio of the speed of the simulation to the speed of real time
    private long lastUpdateTime = 0;
    private GreenfootImage image;
    private DecimalFormat df;
    private int prevHour;
    private Color transparent = new Color(0,0,0,0);
    private boolean stopped = false;
    
    /**
     * Constructor for class Clock
     * @param startingms the time when the clock is starting, in number of milliseconds since midnight
     * @param ratio the ratio of the speed of time in the game to the speed of time in real life
     */
    public Clock(int startingms, int ratio)
    {
        this.ratio = ratio;
        this.startingms = startingms;
        this.baseline = System.currentTimeMillis();
        this.lastUpdateTime = System.currentTimeMillis();
        df = new DecimalFormat("00");
    }
    
    /**
     * Called when the Clock object is added to the world
     */
    public void addedToWorld(World world)
    {
        updateTime();
        displayTime();
    }
    
    /**
     * Act - do whatever the Clock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //update the prevHour to the current hour
        prevHour = getHour();
        //if the clock is not stopped keep updating the display
        if(!stopped)
        {
            updateTime();
            displayTime();
        }
    } 
    
    /**
     * Gets the hour value of the clock
     * @return the hour in a 24-hour system
     */
    public int getHour()
    {
        return (int)((this.ms / 3600000) % 24);
    }
    
    /**
     * Gets the minute value of the clock
     * @return the number of minutes on the clock
     */
    public int getMin()
    {
        return (int)((this.ms/60000) % 60);
    }
    
    /**
     * Gets the millisecond value of the clock
     * @return the number of milliseconds on the clock since midnight
     */
    public long getMillis()
    {
        return this.ms % 86400000;
    }
    
    /**
     * Adjusts the clock to start off where it was left off
     */
    public void start()
    {
        baseline += System.currentTimeMillis() - lastUpdateTime;
        stopped = false;
    }
    
    /**
     * Saves the state of the clock when it is stopped
     */
    public void stop()
    {
        lastUpdateTime = System.currentTimeMillis();
        stopped = true;
    }
    
    /**
     * Draws the current time onto the clock's image
     */
    private void displayTime()
    {
        //set up border
        image = new GreenfootImage(90, 40);
        image.setColor(Color.GRAY);
        image.fill();
        //inside color
        GreenfootImage inner = new GreenfootImage(80, 30);
        inner.setColor(Color.BLACK);
        inner.fill();
        image.drawImage(inner, 5, 5);
        //place text
        GreenfootImage text = new GreenfootImage(getHour()+":"+df.format(getMin()), 25, Color.GREEN, transparent);
        image.drawImage(text, 20, 10);
        setImage(image);
    }
    
    /**
     * Updates the time while the game is running
     */
    private void updateTime()
    {
        ms = startingms + (long)(((System.currentTimeMillis() - baseline)) * ratio);
    }
    
    /**
     * Checks if the hour has changed
     * @return true if the hour has changed, otherwise false
     */
    public boolean ring()
    {
        return getHour() != prevHour;
    }
    
    /**
     * Resets the clock back to a specified time.
     * @param startingms the starting time of the clock, in number of milliseconds past midnight
     */
    public void reset(long startingms)
    {
        this.startingms = startingms;
        this.baseline = System.currentTimeMillis();
        this.lastUpdateTime = System.currentTimeMillis();
        updateTime();
        displayTime();
    }
}
