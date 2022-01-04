import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The robber steals money from the restaurant.
 * 
 * @author Rachel Tong
 * @author Gordon Xie
 * @version 2 (11.10)
 */
public class Robber extends Person
{
    private GreenfootSound breakSound;
    /**
     * Creates a Robber.
     */
    public Robber()
    {
        super(3, 3);
        goRight();
        breakSound = new GreenfootSound("break.wav");
    }
    
    /**
     * Sets the images of the robber
     */
    protected void setImages()
    {
        walkLeftImage = new GreenfootImage[7];
        walkRightImage = new GreenfootImage[7];
        walkUpImage = walkLeftImage;
        walkDownImage = walkRightImage;
        defaultImage = walkLeftImage[0];
        for(int i = 0; i<walkLeftImage.length; i++){
            walkLeftImage[i] = new GreenfootImage("images/robberLeft"+(i+1)+".png");
        }
        for(int i = 0; i<walkRightImage.length; i++){
            walkRightImage[i] = new GreenfootImage("images/robberRight"+(i+1)+".png");
        }
    }
    
    /**
     * Act - do whatever the Robber wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        if(vy == 0) { //if the robber is moving horizontally
            if(getX() >= 550) { //if the robber has gone past the vertical hallway move down
                setLocation(550, 225);
                goDown();
            }
        } else { //if the robber is moving vertically
            if(getY() >= 475) {//if the robber has reached the cash counter steal the cash and go back up
                setLocation(550, 475);
                steal();
                goUp();
            } else if(getY() < 225) { //if the robber has reached the horizontal hallway go left
                setLocation(550, 225);
                goLeft();
            }
        }
        checkEdges();
    }    
    
    /**
     * Steal money from the restaurant.
     */
    private void steal()
    {
        breakSound.play(); //play sound
        world.money -= 50; //decrease the diner's money
        world.stolen += 50; //increase the total amount of money stolen
    }
}
