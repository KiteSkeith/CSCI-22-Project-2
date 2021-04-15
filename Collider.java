/**
 * Any object that collides are colliders. The players cannot move againts colliders
 *
 * @author Christopher Nicolo C. Linaac, Christian Paul V. Penaflor
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
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;


public class Collider implements DrawingObject {

    public boolean trigger = false;
    public int x, y, width, height;
    public int prevX, prevY;
    PlayerCharacter pc;
    Color c;

    //creates sthe object of collider of collides
    public Collider(int x, int y, int width, int height, GameCanvas gc, Color c) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.c = c;
        trigger = false;
    }

    
    //creates a collider with a trigger
    public Collider(int x, int y, int width, int height, GameCanvas gc, Color c, boolean trigger) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.c = c;
        this.trigger = trigger;
    }
    
    //normal colliders do not have triggers
    public boolean triggerEffect(PlayerCharacter c) {
        return false;
    }

    //stops triggers
    public void undoTrigger(PlayerCharacter c) {
    }

    //draws colliders
    @Override
    public void draw(Graphics2D g2d) {
        Rectangle2D.Double plat = new Rectangle2D.Double(x, y, width, height);
        g2d.setColor(c);
        g2d.fill(plat);
    }

    @Override
    //animation of colliders
    public void anim() {
        prevX = x;
        //x += 5;
    }
    
    //gets X of colliders
    public float getX() {
        return x;
    }
    
    //gets Y of colliders
    public float getY() {
        return y;
    }

    //tells if colliders is trampoline
    public boolean isTrampoline() {
        return false;
    }

    //tells if collider is a hazzard
    public boolean isHazard() {
        return false;
    }
}
