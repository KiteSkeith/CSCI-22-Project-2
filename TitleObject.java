
/*
 * The TitleObject class is the class that shows the title in the main menu. 
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
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class TitleObject implements DrawingObject {

    Image titleImage;
    int x, y, width, height;
    GameCanvas gc;

    public TitleObject(GameCanvas gc) {
        x = 306;
        y = 0;
        width = 412;
        height = 304;
        loadImage();
        this.gc = gc;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(titleImage, x, y, width, height, gc);
    }

    @Override
    public void anim() {

    }

    public void loadImage() {
        ImageIcon ii = new ImageIcon("src/images/Assets/TitleScreen.png");
        titleImage = ii.getImage();
    }

}
