import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A world that allows the user to set variables that will affect the simulation.
 * 
 * @author Rachel Tong
 * @version 3
 */
public class Settings extends World
{
    int money = 100, numServers = 1;
    private SetServerButton[]serverButton;
    private MoneyButton[]moneyButton;
    public SetServerButton selectedServerButton;
    public MoneyButton selectedMoneyButton;
    /**
     * Constructor for objects of class Settings.
     * 
     */
    public Settings()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        setPaintOrder(Label.class, Button.class, ButtonBackground.class);
        
        moneyButton = new MoneyButton[3];
        moneyButton[0] = new MoneyButton(100);
        moneyButton[1] = new MoneyButton(200);
        moneyButton[2] = new MoneyButton(300);
        serverButton = new SetServerButton[3];
        serverButton[0] = new SetServerButton(1);
        serverButton[1] = new SetServerButton(2);
        serverButton[2] = new SetServerButton(3);
        
        addObject(moneyButton[0], 400, 490);
        addObject(moneyButton[1], 550, 490);
        addObject(moneyButton[2], 700, 490);
        addObject(serverButton[0], 400, 540);
        addObject(serverButton[1], 550, 540);
        addObject(serverButton[2], 700, 540);
        
        addObject(new Label("Money:", 35), 150, 490);
        addObject(new Label("Number of Servers:", 35), 175, 540);
        addObject(new Label("Use Buttons To Set Starting Values", 50), 400, 100);
        addObject(new Label("Press Enter To Continue", 25), 650, 580);
        
        selectedMoneyButton = moneyButton[0];
        selectedServerButton = serverButton[0];
        selectedMoneyButton.respond();
        selectedServerButton.respond();
    }
    
    /**
     * Sets the world to the simulation once the user presses enter
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("enter")) 
        {
            Diner sim = new Diner(money, numServers);
            Greenfoot.setWorld(sim);
            sim.started();
        }
    }
}
