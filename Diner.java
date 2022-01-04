import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
/**
 * The main world in which customers eat and food is served.
 * 
 * <h2>Description:</h2>
 * <p>In our restaurant simulation, a random customer is spawned at the entrance of the restaurant and waits in line. When it reaches the front of the line, 
 * the customer will find a seat, sit down and order. If the customer is unable to find a seat after some time, they leave and the rating drops. 
 * The stove then cooks the food and the server delivers the food, and if the customer has not left yet, the food is delivered. Once the meal is finished, 
 * the customer pays and the rating increases. If the food is poisoned, the customer will not pay and the rating drops. The higher the ratings, the more 
 * customers will dine at the restaurant. The inspector customer has the most influence on the rating, the Rich customer has the most money, and the Poor 
 * customer has the least. The amount of money restricts what kind of food is ordered. At the end of every hour, servers are paid a wage, and occasionally, 
 * a robber will appear and steal $50 from the cash register.</p>
 * 
 * <h2>Important Elements:</h2>
 * <ol>
 * <li>Watch how the number of customers, cook time, and the number of servers interact together to create unpredictable circumstances. Slow cook time and 
 * fewer servers lead to lost revenue, and the surge in customers due to increased spawn rates during lunch and dinner time can lead to logistical 
 * nightmares and overwhelm the restaurant’s ability to serve its customers.</li>
 * <li>Look out for equipment upgrades as extra profits are taken to expand the capacity of the counter, increase the speed of the cooking, and increased 
 * customer patience. When this occurs, the images of the counter, seats, and tables are changed.</li>
 * <li>Different foods have different costs, price, and poison chance. Higher-end food have higher profit margins but has a higher probability of 
 * causing food poisoning.</li>
 * <li>Number of customers and servers can be adjusted using the buttons on the right and can be pre-set in the settings menu prior to the simulation.</li>
 * </ol>
 * 
 * <h2>Code Credits:</h2>
 * <ul>
 * <li>Label and SimpleTimer classes are credited to original Greenfoot Authors</li>
 * <li>Timer class is credited to Albert Lai and Star Xie, and used with permission</li>
 * </ul>
 * 
 * <h2>Image Credits:</h2>
 * <h3>Screens</h3>
 * <ul>
 * <li>Title and Setting Screen: https://dribbble.com/shots/3260829-Restaurant</li>
 * </ul>
 * <h3>People</h3>
 * <ul>
 * <li>Poor Customer: https://permadi.com/2010/11/html5-animation-with-canvas/</li>
 * <li>Rich Customer: https://www.pngkey.com/maxpic/u2e6r5r5t4a9w7a9/</li>
 * <li>Inspector: https://i.pinimg.com/236x/f0/41/9b/f0419ba3919e0de6fe6fa93e4aafce5f--sprites-game-art.jpg</li>
 * <li>Server:  https://www.pinterest.es/pin/378020962469291110/</li>
 * <li>Robber:  https://www.artstation.com/artwork/GXnBYV</li>
 * <li>Cook: http://cliparts101.com/free_clipart/82575/Chef?hcb=1</li>
 * <li>Waiter: https://creativemarket.com/Silpin/1147087-Male-Waiter-Game-Sprite</li>
 * </ul>
 * <h3>Objects</h3>
 * <ul>
 * <li>Cash Register: https://opengameart.org/content/cash-machine</li>
 * <li>Table: https://www.istockphoto.com/photo/overhead-view-of-light-brown-wooden-table-gm491606503-40352856</li>
 * <li>Chair: https://www.morleypianos.co.uk/round-victorian-stool-151-p.asp</li>
 * <li>Counter: Greenfoot default image</li>
 * <li>Stove: https://wdrfree.com/stock-vector/download/gray-blue-stove-86468466</li>
 * <li>Button: https://www.pngitem.com/middle/Thoxx_button-png-picture-blue-button-transparent-background-png/</li>
 * </ul>
 * <h3>Temporary Images</h3>
 * <ul>
 * <li>Dollar: https://opengameart.org/content/coin-icon</li>
 * <li>Poison: https://opengameart.org/content/poison-skull</li>
 * </ul>
 * <h3>Food</h3>
 * <ul>
 * <li>Apple: Greenfoot default image</li>
 * <li>Burger: http://clipart-library.com/clipart/149301.htm</li>
 * <li>Fries: Greenfoot default image</li>
 * <li>Icecream: http://clipart-library.com/clipart/1144032.htm</li>
 * <li>Lobster: https://webstockreview.net/pict/getfirst?no1</li>
 * <li>Steak: https://creazilla.com/nodes/34030-beef-steak-meal-clipart</li>
 * </ul>
 * <h3>Sounds Organized</h3>
 * <ul>
 * <li>Register sound: http://soundbible.com/1997-Cha-Ching-Register.html</li>
 * <li>Breaking sound: http://soundbible.com/1945-Smashing.html</li>
 * <li>BGM music: https://www.youtube.com/watch?v=QP2a6gGr534</li>
 * </ul>
 * 
 * @author Ryan Lin
 * @author Rachel Tong
 * @version 4
 */
public class Diner extends World
{
    //Game variables
    int money; //amount of cash on hand
    double rating; //rating value
    int spawnRate = 500; //spawn rate of customers
    int frontOfLine = 250; //front of the line
    int numServers; //number of servers
    //financial statistics
    int revenue = 0; //amount of money received from customers today
    int foodexp = 0; //amount of money used to pay for ingredients today
    int salaryexp = 0; //amount of money used to pay servers today
    int stolen = 0; //amount of money stolen by robbers today
    int upgradecost = 0; //amount of money used for upgrades
    //customer statistics
    int numCustomers; //number of customers that have been added to this simulation today
    int customersServed; //number of customers who have left the line today
    int happyCustomers = 0; //number of customers who finished their meal and paid today
    int angryCustomers = 0; //number of customers who waited too long or were poisoned today
    //start and end times
    int startms = 9 * 3600000; //the time the diner simulation starts, in milliseconds after midnight
    int endms = 22 * 3600000; //the time the diner simulation ends, in milliseconds after midnight
    //objects in the simulation
    ArrayList<Table> tables = new ArrayList<>(); //a list of all tables in world
    Counter counter = new Counter(400,50); //the serving table
    Countertop cashCounter = new Countertop(200, 50); //the cash table
    Clock clock = new Clock(startms, 400); //the clock
    StatWidget statWidget = new StatWidget(); //a widget that displays the rating and money
    ServerButton serverButton = new ServerButton(); //a button that allows the user to add servers into this simulation
    RemoveServerButton removeServerButton = new RemoveServerButton(); //a button that allows the user to remove servers from this simulation
    CustomerButton customerButton = new CustomerButton(); //a button that adds customers into this simulation
    private GreenfootSound music = new GreenfootSound("bgm.mp3"); //background music
    Stove stove = new Stove(); //the stove
    
    /**
     * Constructs MyWorld using default values
     */
    public Diner()
    {
        this(100, 1);
    }
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Diner(int money, int numServers)
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        //add objects to the world
        constructTables();
        addObject(counter, 300 , 475);
        addObject(cashCounter, 700, 475);
        addObject(new Divider(10, 330), 96, 436);
        addObject(new Divider(400, 10), 300, 445);
        addObject(new Divider(200, 10), 700, 445);
        addObject(clock, 755, 20);
        addObject(statWidget, 687, 564);
        addObject(serverButton, 758, 444);
        addObject(removeServerButton, 758, 479);
        addObject(customerButton, 758, 513);
        addObject(new Register(50,50), 650, 475);
        addObject(stove, 130, 550);
        GreenfootImage image = new GreenfootImage(getWidth(), getHeight());
        image.setColor(new Color(231, 201, 169));
        image.fill();
        setBackground(image);
        music.setVolume(35);
        this.money = money;
        this.numServers = numServers;
        this.numCustomers = 0;
        //spawn servers
        for(int i = 0; i<numServers; i++)
        {
            spawnServer();
        }
    }
    
    /**
     * Add tables to the world
     */
    private void constructTables()
    {
        //Add the top row of horizontal tables
        for(int x = 170; x<=600; x+=172)
        {
            Table t = new Table(true);
            tables.add(t);
            addObject(t, x, 100);
        }
        //Add the bottom row of vertical tables
        for(int x = 200; x<=500; x+=200)
        {
            Table t = new Table(false);
            tables.add(t);
            addObject(t, x, 350);
        }
        //Add right side row of tables
        for(int y = 125; y<=350; y += 225)
        {
            Table t = new Table(false);
            tables.add(t);
            addObject(t, 700, y);
        }
    }
    
    /**
     * Runs the MyWorld class
     */
    public void act()
    {
        rating = Math.min(5,Math.max(rating, 0.1));
        statWidget.setRatingValue(rating);
        statWidget.setMoney(money);
        if(clock.getMillis() >= endms)
        {
            if(getObjects(Customer.class).isEmpty() && getObjects(Robber.class).isEmpty()) 
            {
                Greenfoot.setWorld(new End(this));
            }
            clock.stop();
        } else {
            spawnCustomer();
            spawnRobber();
        }
    }
    
    /**
     * Helper function for determining spawn rate
     */
    private double f(double x)
    {
        return Math.pow(Math.max(0, Math.cos(x*Math.PI/12)),4);
    }
    
    /**
     * Spawn customers method
     */
    public void spawnCustomer()
    {
        Customer customer = null;
        switch(Greenfoot.getRandomNumber(spawnRate)){
            case 1:
                customer = new Inspector();
                break;
            case 2:
                customer = new Poor();
                break;
            case 3:
                customer = new Rich();
                break;
        }
        if(customer != null){
            addObject(customer, 20, 580);
        }
        spawnRate = 600 - (int)Math.round((2*f((double)clock.getMillis()/3600000 - 12) + 3*f((double)clock.getMillis()/3600000 - 19))*25*rating);
    }
    
    /**
     * Spawns a server
     */
    public void spawnServer()
    {
        addObject(new Server(), 550, 500);
    }
    
    /**
     * Spawns a robber
     */
    public void spawnRobber()
    {
        if(Greenfoot.getRandomNumber(5000) == 1)
        {
            addObject(new Robber(), 20, 225);
        }
    }
    
    /**
     * Called when the world is stopped
     */
    public void stopped()
    {
        clock.stop();
        music.stop();
        for(Customer customer: getObjects(Customer.class))
        {
            customer.timer.stop();
        }
        counter.timer.stop();
    }
    
    /**
     * Called when the world is started
     */
    public void started()
    {
        clock.start();
        music.playLoop();
        for(Customer customer: getObjects(Customer.class))
        {
            customer.timer.start();
        }
        counter.timer.start();
    }
    
    /**
     * Reset variables at the beginning of the day
     */
    public void reset()
    {
        this.revenue = 0;
        this.salaryexp = 0;
        this.foodexp = 0;
        this.stolen = 0;
        this.upgradecost = 0;
        this.happyCustomers = 0;
        this.angryCustomers = 0;
        this.numCustomers = 0;
        this.customersServed = 0;
        this.clock.reset(startms);
        this.started();
    }
}
