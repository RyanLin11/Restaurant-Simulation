import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A border around a button that displays whether or not the button is selected.
 * 
 * @author Eric Wang 
 * @version 5
 */
public class ButtonBackground extends Actor
{
    private boolean selected; //true if the button is selected
    private Color selectColor; //the color of the button if it is selected
    private Color defaultColor; //the color of the button if it is not selected
    
    /**
     * Creates a red or transparent background to indicate whether or not the button is selected
     */
    public ButtonBackground()
    {
        this(Color.RED, new Color(247, 247, 245));
    } 
    
    /**
     * Creates a background with custom colours to indicate where or not the button is selected
     */
    public ButtonBackground(Color selectColor, Color defaultColor)
    {
        this.selected = false;
        this.selectColor = selectColor;
        this.defaultColor = defaultColor;
    }
    
    /**
     * Sets the selected status of the button and changes the display if necessary
     * @param selected true if the button is selected, otherwise false
     */
    public void setClicked(boolean selected)
    {
        this.selected = selected; //set the selected status
        GreenfootImage image = new GreenfootImage(86, 36); //create the base image
        image.setColor(selected? Color.RED: defaultColor); //if the button is selected make it red, otherwise make it the default color
        image.fillRect(0, 0, image.getWidth(), image.getHeight());//fill the rectangle with the previously selected colo
        setImage(image); //set the image
    }
}
