import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A button that sets starting money.
 * 
 * @author Rachel Tong
 * @version 3
 */
public class MoneyButton extends Button
{
    private Settings world;
    private int amount;
    private ButtonBackground background;
    
    /**
     * Creates a button to set starting money.
     * @param amount the desired starting amount of money 
     */
    public MoneyButton(int amount)
    {
        super("$"+Integer.toString(amount));
        this.amount = amount;
        background = new ButtonBackground();
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
        world.money = amount; 
        world.selectedMoneyButton.resetBackground();
        background.setClicked(true);
        world.selectedMoneyButton = this;
    }    
    
    /**
     * Resets the button's background to default
     */
    public void resetBackground()
    {
        background.setClicked(false);
    }
}