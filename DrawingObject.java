/**
 * Drawing Objects are objects that are drawn in the canvas. Is an interface
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
import java.awt.*;

public interface DrawingObject {

    public void draw(Graphics2D g2d); //The draw method used to draw the shape on the frame.

    public void anim(); //The animation method used to move the shapes continuously
}
