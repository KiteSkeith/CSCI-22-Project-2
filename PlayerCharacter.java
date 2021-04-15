/*
 * The PlayerCharacter class is the class where the mechanics of the player are created and applied
 * such as collision and jumping. The PlayerCharacter class is also used for the main block of the game. 
 */

/**
 * @author Christopher Nicolo C. Linaac, Christian Paul V. Peñaflor
 * @version 05-21-19
 */

/*
I have not discussed the Java language code 
in my program with anyone other than my instructor 
or the teaching assistants assigned to this course.

I have not used Java language code obtained 
from another student, or any other unauthorized 
source, either modified or unmodified.

If any Java language code or documentation 
used in my program was obtained from another source, 
such as a text book or webpage, those have been 
clearly noted with a proper citation in the comments 
of my code.
*/
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.io.*;
import java.net.*;

public class PlayerCharacter implements DrawingObject {

    public static PlayerCharacter p1;
    public static PlayerCharacter p2;
    public static GameFrame gf;
    private static float baseWidth = 148;
    private static float baseHeight = 222;
    public static float scale = 0.3f;
    public static int width = (int) (baseWidth * scale);
    public static int height = (int) (baseHeight * scale);
    public String character;

    public int direction = 1;
    public float x, y, prevX;
    private float gravity = -2000f;
    private float jumpSpeed = 800f;
    public float yVel = 0;
    private float xSpeed = 300;
    public float xVel = 0;
    private ImageIcon phase1;
    private Image image;
    Collider ground;
    private Hashtable animCounts = new Hashtable();
    private Hashtable animCountsJump = new Hashtable();
    private Hashtable animCountsFall = new Hashtable();

    public boolean goRight = false;
    public boolean goLeft = false;
    public boolean jumpCommand = false;
    public boolean jumped = false;

    private int animFrame = 1;
    private int maxAnimFrame = 0;
    
    /*
     * The constructor accepts 2 integers for the x,and y,
     * as well as a boolean.
     */
    public PlayerCharacter(float x, float y, boolean isHost) {
        if (isHost) {
            character = "Blue";
            p1 = this;
        } else {
            character = "Red";
            p2 = this;
        }
        //animation styles matched to their frame counts, update as necessary
        animCounts.putIfAbsent("running", 4);
        animCountsJump.putIfAbsent("jumping", 1);
        animCountsFall.putIfAbsent("falling", 1);

        this.x = x;
        this.y = y;
    }

    /*
     * this method draws the PlayerCharacter.
     */
    public void draw(Graphics2D g2d) {

        Rectangle2D.Double pc = new Rectangle2D.Double(x, y, width, height);

        g2d.setColor(new Color(6, 6, 6));
        int xPos = (int) x;
        if (direction == -1) {
            xPos += width;
        }
        g2d.drawImage(image, xPos, (int) y, width * direction, height, GameCanvas.gc);

    }

    /*
     * this method animates the PlayerCharacter
     */
    public void anim() {
        String animType = "running";

        //animations for running, jumping and falling
        if (isGrounded()) {
            if (animType.equals("running") && !goRight && !goLeft) {
                animFrame = 1;
            } else if (animType.equals("running")) {
                maxAnimFrame = (int) animCounts.get(animType);
                animFrame = 1 + ((animFrame) % maxAnimFrame);
            }
            loadImage("running", animFrame);
            return;
        } else if (yVel > 0) {
            animType = "jumping";
            if (animType.equals("jumping")) {
                animFrame = 1;
            }
            loadImage("jumping", animFrame);
            return;
        } else if (yVel < 0) {
            animType = "falling";
            if (animType.equals("falling")) {
                animFrame = 1;
            }
            loadImage("falling", animFrame);
            return;
        }
        loadImage("running", 1);
    }

    ArrayList<Integer> collisions = new ArrayList<>();

    /**
     * Detects Collision and tells if player is colliding with something There
     * is collision if value returned is 1 - 4, None if it is 0.
     *
     * @param c the collision object
     * @return a value which determines the character reaction.
     */
    public int collide(Collider c) {
        boolean collideHazard = false;
        if (c.isHazard()) {
            collideHazard = true;
        }

        //First if statement confirms if it is colliding
        if (x < c.x + c.width
                && x + width > c.x
                && y < c.y + c.height
                && y + height >= c.y) {

            if (c.trigger) {
                if (c.triggerEffect(this)) {
                    return -1;
                }
                return 0;
            }
            //lateral collision
            //If left of collision object
            if (prevX + width <= c.x) {
                //put the player back near to Collision object
                if (!collideHazard) {
                    x = c.x - width;
                } else {
                    HazardEffect();
                }
                //prevX = x;
                return 1;
            } //If right of collision object
            else if (prevX >= c.x + c.width) {
                //put the player back, near to collision object when intersecting
                if (!collideHazard) {
                    x = c.x + c.width;
                } else {
                    HazardEffect();
                }
                //prevX = x;
                return 2;
            }

            //vertical collision
            //jumping and hittting a object at top colllison
            if (yVel > 0 && y > c.y + height / 2) {
                if (!collideHazard) {
                    y = c.y + c.height;
                    yVel = 1;
                } else {
                    HazardEffect();
                }
                return 3;
            } //falling and hitting an object at ground
            else if (yVel <= 0 && y < c.y) {
                if (!collideHazard) {
                    y = c.y - height;
                } else {
                    HazardEffect();
                }
                return 4;
            }
        } else if (c.trigger) {
            c.undoTrigger(this);
            return 0;
        }
        return 0;
    }

    /**
     * A method that determines if the player is walking on land.
     *
     * @return boolean that tells if the player is grounded or not
     */
    public boolean isGrounded() {
        //If hitting the bottom part of the screen
        boolean grounded = (y + height >= GameStarter.height);
        //
        Collider ground = null;
        collisions.clear();

        //checking all collider objects and if it is landing on object it is grounded
        for (Collider c : GameCanvas.colliders) {
            int col = collide(c);
            collisions.add(col);
            if (col == -1 && character.equals("Blue")) {
                System.out.println("Victory");
                gf.stopAnimation();
                break;
            }
            if (col == 4) {
                grounded = true;
                ground = c;
            }
        }
        //if landing on the bottom of the screen y position will be constant
        if (grounded) {
            if (ground == null) {
                y = GameStarter.height - height;
            }
        }
        return grounded;
    }

    /**
     * If the right arrow key is pressed, the going right character animation
     * play
     *
     * @param start is from the key press of the right arrow key.
     */
    public void right(boolean start) {
        goRight = start;
    }

    /**
     * If the left arrow key is pressed, the going right character animation
     * play
     *
     * @param start is from the key press of the left arrow key.
     */
    public void left(boolean start) {
        goLeft = start;
    }

    /**
     *
     */
    public void move() {
        boolean isFalling = fall();
        //jumping initiates
        if (jumpCommand) {
            jumped = true;
            // not in falling state, change of Velocity to jump speed velocity, not in JumpCommand
            if (!isFalling) {
                yVel = jumpSpeed;
            }
            jumpCommand = false;
        }
        //previous x is the x before lateral movement
        prevX = x;

        // going Right direction faces to the right
        if (goRight && !goLeft && !collisions.contains(1)) {
            direction = 1;
            xVel = (xSpeed / GameClock.frameRate);
            x += xVel;

            // going Left direction faces to the left
        } else if (goLeft && !goRight && !collisions.contains(2)) {
            xVel = -(xSpeed / GameClock.frameRate);
            x += xVel;
            direction = -1;
        } else {
            xVel = 0;
        }
        //System.out.println(isFalling);
        if (isTrampoline()) {
            trampolineJump();
        }

    }

    
    /*
     * method turns boolean jumpCommand true.
     */
    public void jump() {
        jumpCommand = true;
    }

    
    /*
     * returns a boolean for if PlayerCharacter is falling. 
     */
    public boolean fall() {

        y -= yVel / GameClock.frameRate;
        //Not on ground, Airborne state
        if (!isGrounded()) {
            //Determines if its jumping state, reduces yVelocity
            if (yVel > -jumpSpeed) {
                yVel += gravity / GameClock.frameRate;
            }
            //falling
            return true;
        } //On Ground, not falling
        else {
            yVel = 0;
            jumped = false;
            return false;
        }
    }

    
    /*
     * loads the player image
     */
    public void loadImage(String animType, int count) {
        String src = "src/images/" + animType + "/" + character + count + ".png";
        phase1 = new ImageIcon(src);
        image = phase1.getImage();
    }

    
    /*
     * method for if the player interacts with a trampoline
     */
    public void trampolineJump() {
        if (isTrampoline()) {
            yVel = 1300;
        }
    }

    
    /*
     * boolean for if the object that the player interacts with is a trampoline
     */
    public boolean isTrampoline() {

        if (isGrounded()) {
            for (Collider c : GameCanvas.colliders) {
                if (c.isTrampoline() && x + width >= c.x && x <= c.x + c.width && y + height == c.y) {
                    return true;

                }
            }
        }
        return false;

    }

    /*
     * boolean for if the object that the player interacts with is a hazard
     */
    public boolean isHazard() {
        for (Collider c : GameCanvas.colliders) {
            if (c.isHazard()) {
                return true;
            }
        }

        return false;
    }

    /*
     * activates the hazard effect
     */
    public void HazardEffect() {

        x = GameCanvas.gc.startCharX;
        y = GameCanvas.gc.startCharY;
        yVel = 0;
        xVel = 0;

    }

    public boolean isGoingRight() {
        return goRight;
    }

    public boolean isGoingLeft() {
        return goLeft;
    }

    //Might remove (not used)
    public boolean isJumping() {
        return jumped;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getXVel() {
        return xVel;
    }

    public float getYVel() {
        return yVel;
    }

    public void changeX(float x) {
        this.x = x;
    }

    public void changeY(float y) {
        this.y = y;
    }

}
