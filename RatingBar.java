import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays the ratings in terms of stars.
 * 
 * @author Ryan Lin 
 * @version 11.09
 */
public class RatingBar extends Actor
{
    private double maxValue = 1000;
    private double value = 0;
    private int size, width, height;
    
    /**
     * Simple constructor for bar
     */
    public RatingBar()
    {
        this(5, 0, 100);
    }
    
    /**
     * Constructor with variable number of stars
     * @param size the number of stars in the bar
     * @param startingValue the starting value of the statistic that the bar is tracking
     * @param maxValue the maximum value of the statistic that the bar is tracking
     */
    public RatingBar(int size, int startingValue, int maxValue)
    {
        this(size, startingValue, maxValue, 50*size, 50);
    }
    
    /**
     * Constructor that allows resizing of RatingBar
     * @param size the number of stars in the bar
     * @param startingValue the starting value of the statistic that the bar is tracking
     * @param maxValue the maximum value of the statistic that the bar is tracking
     * @param width the width of the bar
     * @param height the height of the bar
     */
    public RatingBar(int size, int startingValue, int maxValue, int width, int height)
    {
        this.size = Math.max(size, 1);
        this.maxValue = Math.max(startingValue, 0);
        this.maxValue = Math.max(maxValue, 1);
        this.width = Math.max(width, 1);
        this.height = Math.max(height, 1);
        GreenfootImage image = new GreenfootImage(width, height);
        for(int i = 0; i<size; i++)
        {
            GreenfootImage star = new GreenfootImage("images/star.png");
            star.scale(width/size, height);
            image.drawImage(star, i*star.getWidth(), 0);
        }
        makeBackgroundTransparent(image);
        setImage(image);
    }
    
    /**
     * Periodically updates the bar
     */
    public void act() 
    {
        //creates the base image
        GreenfootImage base = new GreenfootImage(width, height);
        //calculate the width of the yellow background, in pixels
        int px = Math.min(width, (int)(((double)value/maxValue)*width));
        //draw a yellow background to show progress
        if(px > 0)
        {
            GreenfootImage progress = new GreenfootImage(px, height);
            progress.setColor(Color.YELLOW);
            progress.fill();
            base.drawImage(progress, 0, 0);
        }
        //draw the stars on top of the yellow background
        for(int i = 0; i<size; i++)
        {
            GreenfootImage star = new GreenfootImage("images/star.png");
            star.scale(width/size, height);
            base.drawImage(star, i*star.getWidth(), 0);
        }
        //make the white background of the stars transparent
        makeBackgroundTransparent(base);
        setImage(base);
    }
    
    /**
     * Updates the current value that the bar is displaying
     * @param newVal the new value
     */
    public void setValue(double newVal)
    {
        if(newVal >= 0)
        {
            this.value = newVal;
        }
    }
    
    /**
     * Return the current value that the bar is displaying
     * @return the current value
     */
    public double getValue()
    {
        return this.value;
    }
    
    /**
     * Updates the max value that the bar can hold
     * @param newMaxVal the new maximum value
     */
    public void setMaxValue(double newMaxVal)
    {
        if(newMaxVal > 0)
        {
            this.maxValue = newMaxVal;
        }
    }
    
    /**
     * Returns the max value that the bar can hold
     * @return the max value
     */
    public double getMaxValue()
    {
        return this.maxValue;
    }
    
    /**
     * Makes all white pixels transparent
     */
    private void makeBackgroundTransparent(GreenfootImage image)
    {
        //for every pixel in the rating bar, make it transparent if it is white
        for(int x = 0; x<image.getWidth(); x++)
        {
            for(int y = 0; y<image.getHeight(); y++)
            {
                if(image.getColorAt(x, y).equals(Color.WHITE))
                {
                    image.setColorAt(x, y, new Color(0,0,0,0));
                }
            }
        }
    }
}
