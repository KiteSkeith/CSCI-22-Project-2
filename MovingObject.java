
/*
 * The MovingObject class extends Collider and creates and animates the object.
 * The MovingObject moves when the correct player moves. 
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
import java.awt.Color;
import java.util.ArrayList;

public class MovingObject extends Collider {

    public static ArrayList<MovingObject> lis = new ArrayList<MovingObject>();
    private boolean blue;
    private int prevX, prevY;
    private int yDir = -1;
    private int dist;
    GameCanvas gc;
    Color c;
    PlayerCharacter p;
    
    /*
     * The constructor accepts 5 integers for the x, y, width, height, and distance.
     * as well as a GameCanvas and a boolean.
     */

    public MovingObject(int x, int y, int width, int height, GameCanvas gc, int dist, boolean blue) {
        super(x, y, width, height, gc, blue ? Color.BLUE : Color.RED);
        this.blue = blue;
        this.dist = dist;
        prevY = y;
        lis.add(this);
    }

    public static void setPlayer() {
        for (MovingObject m : lis) {
            if (m.blue) {
                m.p = PlayerCharacter.p1;
            } else {
                m.p = PlayerCharacter.p2;
            }
        }
    }

    @Override
    public void anim() {
        if (p.goRight || p.goLeft || p.yVel != 0) {
            y += 5 * yDir;
        }
        if (y <= prevY - dist) {
            y = prevY - dist;
            yDir = yDir * -1;
        } else if (y >= prevY) {
            y = prevY;
            yDir = yDir * -1;
        }
    }
}
