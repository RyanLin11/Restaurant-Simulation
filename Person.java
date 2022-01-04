import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * All characters in the world.
 * 
 * @author Ryan Lin
 * @version 4 (11.14)
 */
public abstract class Person extends Actor
{
    /**Animation frames for walking up*/
    protected GreenfootImage[]walkUpImage;
    /**Animation frames for walking down*/
    protected GreenfootImage[]walkDownImage;
    /**Animation frames for walking right*/
    protected GreenfootImage[]walkRightImage;
    /**Animation frames for walking left*/
    protected GreenfootImage[]walkLeftImage;
    /**The image shown when the person is still*/
    protected GreenfootImage defaultImage;
    /**The number of act() calls between animation frames*/
    private int animationSpeed = 5;
    /**The current index of the frame displayed*/
    private int frame = 0;
    /**The counter that keeps track of the number of act() calls to time animations*/
    private int animationCounter = 0;
    /**The current horizontal speed of the customer*/
    protected int vx = 0;
    /**The current vertical speed of the customer*/
    protected int vy = 0;
    /**The speed of the customer in any direction*/
    protected int speed;
    /**The simulation that the person belongs to*/
    protected Diner world;
    
    /**
     * Creates a Person.
     * @param speed the speed of the person
     * @param animationSpeed the speed of the animation
     */
    public Person(int speed, int animationSpeed)
    {
        this.speed = speed;
        this.animationSpeed = animationSpeed;
        setImages();
    }
    
    /**
     * Called when the Person is added to the world
     */
    public void addedToWorld(World world)
    {
        this.world = (Diner) world;
    }
    
    /**
     * Initialize images for characters
     */
    protected abstract void setImages();
    
    /**
     * Continuously updates the person
     */
    public void act() 
    {
        animate();
        setLocation(getX() + vx, getY() + vy);
    } 
    
    /**
     * Animate the character's movement
     */
    private void animate()
    {
        if(animationCounter%animationSpeed==0){ //if the animation counter is a multiple of the animation speed change the frame
            if(vx < 0){ //if the person is moving left update the left animation
                frame = (frame + 1) % walkLeftImage.length;
                setImage(walkLeftImage[frame]);
            } else if(vx > 0){ //if the person is moving right update the right animation
                frame = (frame + 1) % walkRightImage.length;
                setImage(walkRightImage[frame]);
            } else { //if the person is moving vertically
                if(vy < 0){ //if the person is moving up update the up animation
                    frame = (frame + 1) % walkUpImage.length;
                    setImage(walkUpImage[frame]);
                } else if(vy > 0){ //if the person is moving down update the down animation
                    frame = (frame + 1) % walkDownImage.length;
                    setImage(walkDownImage[frame]);
                } else { //if the person is not moving set the still image
                    setImage(defaultImage);
                }
            }
        }
        animationCounter++; //increment the animation counter
    }
    
    /**
     * Removes the person if they are at the edge of the world
     */
    protected void checkEdges()
    {
        if(isAtEdge()) world.removeObject(this);
    }
    
    /**
     * Set up the person to move left
     */
    protected void goLeft()
    {
        vx = -speed; vy = 0;
    }
    
    /**
     * Set up the person to move right
     */
    protected void goRight()
    {
        vx = speed; vy = 0;
    }
    
    /**
     * Set up the person to move up
     */
    protected void goUp()
    {
        vx = 0; vy = -speed;
    }
    
    /**
     * Set up the person to move down
     */
    protected void goDown()
    {
        vx = 0; vy = speed;
    }
    
    /**
     * Set up the person to stay still
     */
    protected void stop()
    {
        vx = 0; vy = 0;
    }
}
