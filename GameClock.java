/**
 * Game Clock is the clock or timer for the game. It affects how it animates the movement of player
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
import java.util.*;

public class GameClock extends TimerTask {

    public static boolean running = false;
    public static float frameRate = 1000 / 24;
    PlayerCharacter pc;
    OtherPlayer pc2;
    private ArrayList<DrawingObject> objectList;

    GameFrame frame;

    //This constructor accepts an ArrayList of DrawingObjects to access the drawings and a GameFrame to draw in.
    public GameClock(PlayerCharacter pc, OtherPlayer pc2, GameFrame gf, GameCanvas gc) {
        running = true;
        this.pc = pc;
        this.pc2 = pc2;
        objectList = gc.lis;
        frame = gf;
    }

    @Override
    //This method goes through all DrawingObjects in the ArrayList and runs the animation method of the objects.
    public void run() {

        //pc.fall();
        pc.move();
        pc2.move();

        for (DrawingObject d : objectList) {
            //d.anim();
        }

        frame.repaint();
        frame.revalidate();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        running = false;

    }
}
