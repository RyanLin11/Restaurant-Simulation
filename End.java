import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A world that displays the statistics of the simulation at the end of each day.
 * 
 * @author Ryan Lin 
 * @version V2
 */
public class End extends World
{
    private Label report, finances, revenue, salary, food, profit, customers, happy, angry, charts, stolen, upgrade;
    private int hSize = 50; //size of header text
    private int pSize = 30; //size of paragraph text
    private int leftCol = 50; //x coordinate that the left column aligns to
    private int rightCol = 500; //y coordinate that the right column aligns to
    private int spacing = 40;
    private int hSpacing = 50;
    private Diner simulation;
    
    /**
     * Creates the end screen.
     * @param simulation the world in which the simulation runs
     */
    public End(Diner simulation)
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        this.simulation = simulation;
        simulation.stopped();
        setBackground(new GreenfootImage("images/bluerock.jpg"));
        report = new Label("DAILY REPORT", hSize);
        addObject(report, getWidth()/2, 50);
        //------left side-----------
        //FINANCES
        finances = new Label("Finances", hSize);
        addObject(finances, leftCol + finances.getWidth()/2, 100);
        //revenue
        revenue = new Label("Revenue: $"+simulation.revenue, pSize);
        addObject(revenue, leftCol + revenue.getWidth()/2, 100 + hSpacing);
        //salary expense
        salary = new Label("Salary Expense: $"+simulation.salaryexp, pSize);
        addObject(salary, leftCol + salary.getWidth()/2, 100 + spacing + hSpacing);
        //food expense
        food = new Label("Food Expense: $"+simulation.foodexp, pSize);
        addObject(food, leftCol + food.getWidth()/2, 100 + 2*spacing + hSpacing);
        //stolen money
        stolen = new Label("Stolen Money: $"+simulation.stolen, pSize);
        addObject(stolen, leftCol + food.getWidth()/2, 100 + 3*spacing + hSpacing);
        //upgrades
        upgrade = new Label("Upgrade cost: $"+simulation.upgradecost, pSize);
        addObject(upgrade, leftCol + food.getWidth()/2, 100 + 4*spacing + hSpacing);
        //profit
        profit = new Label("Net Profit: $"+(simulation.revenue - simulation.salaryexp - simulation.foodexp - simulation.stolen - simulation.upgradecost), pSize);
        addObject(profit, leftCol + profit.getWidth()/2, 100 + 5*spacing + hSpacing);
        //CUSTOMERS
        customers = new Label("Customers", hSize);
        addObject(customers, leftCol + customers.getWidth()/2, 100 + 5*spacing + 2*hSpacing);
        //number of happy customers
        happy = new Label("Happy Customers: "+simulation.happyCustomers, pSize);
        addObject(happy, leftCol + happy.getWidth()/2, 100 + 5*spacing + 3*hSpacing);
        //number of angry customers
        angry = new Label("Angry Customers: "+simulation.angryCustomers, pSize);
        addObject(angry, leftCol + angry.getWidth()/2, 100 + 6*spacing + 3*hSpacing);
        //------right side----------
        Cook cook = new Cook();
        cook.getImage().scale(200, 300);
        addObject(cook,600, 300);
        //bottom buttons
        ContinueButton continueButton = new ContinueButton();
        addObject(continueButton, getWidth()/3, 550);
        RestartButton restartButton = new RestartButton();
        addObject(restartButton, 2*getWidth()/3, 550);
    }
    
    /**
     * Gets the simulation world that this end world holds a reference to
     * @return the simulation world
     */
    public Diner getSimulation()
    {
        return this.simulation;
    }
}