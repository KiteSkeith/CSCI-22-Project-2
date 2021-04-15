/**
 * AnimClock is responsible for creating the animation. It changes the animation of the character
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

import java.util.ArrayList;
import java.util.TimerTask;

public class AnimClock extends TimerTask {

    public static float frameRate = 1000 / 8;
    PlayerCharacter pc;
    GameFrame frame;
    ArrayList<DrawingObject> objectList;

    //This constructor accepts an ArrayList of DrawingObjects to access the drawings and a GreetingCardFrame to draw in.
    public AnimClock(ArrayList<DrawingObject> objectList, GameFrame gf) {
        this.objectList = objectList;
        frame = gf;
    }

    @Override
    //This method goes through all DrawingObjects in the ArrayList and runs the animation method of the objects.
    public void run() {

        for (DrawingObject d : objectList) {
            d.anim();

        }
        frame.repaint();
        frame.revalidate();
    }

}
