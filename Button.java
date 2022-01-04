import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A button that is responsive to clicks.
 * 
 * @author Ryan Lin
 * @version 11.10
 */
public abstract class Button extends Actor
{
    private static final Color transparent = new Color(0,0,0,0);
    
    /**
     * Creates a Button with white letters on a blue background.
     * @param label the text on the button.
     */
    public Button(String label)
    {
        this(label, 100, 50);
    }
    
    /**
     * Create a Button with white letters on a blue background with custom sizing.
     * @param label the text on the button
     * @param width the width of the button
     * @param height the height of the button
     */
    public Button(String label, int width, int height)
    {
        this(label, width, height, new GreenfootImage("images/button.png"), Color.WHITE);
    }
    
    /**
     * Creates a Button with custom coloured text on a custom image and with custom sizing.
     * @param label the text on the button
     * @param width the width of the button
     * @param height the height of the button
     * @param buttonImage the image of the button
     * @param textColor the color of the text on the button
     */
    public Button(String label, int width, int height, GreenfootImage buttonImage, Color textColor)
    {
        GreenfootImage image = buttonImage;
        image.scale(width, height);
        GreenfootImage text = new GreenfootImage(label, Math.min((int)(0.8*image.getHeight()),(int)(image.getWidth()/label.length() * 1.5)), textColor, transparent);
        image.drawImage(text, image.getWidth()/2 - text.getWidth()/2, image.getHeight()/2 - text.getHeight()/2);
        setImage(image);
    }
    
    /**
     * Updates the button
     */
    public void act()
    {
        //if the button is clicked respond to it
        if(Greenfoot.mouseClicked(this))
        {
            respond();
        }
    }
    
    /**
     * React to the button being clicked
     */
    protected abstract void respond();
}