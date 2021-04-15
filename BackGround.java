/**
 * BackGround creates the background image from the tiles. Turns into the background of the game
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
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

//The Background of the level.
public class BackGround implements DrawingObject {

    private int width, height;
    private Image bgTile;
    private Image torch;
    GameCanvas gc;

    //draw image of the background
    public BackGround(GameCanvas gc) {
        this.gc = gc;
    }

    @Override
    //draws the image
    public void draw(Graphics2D g2d) {
        loadImage();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                g2d.drawImage(bgTile, i * 355 - 5, j * 236 - 5, gc);
            }
        }
    }

    //load image of a tile with bricks
    public void loadImage() {
        ImageIcon ii = new ImageIcon("src/images/Assets/BrickTile.png");
        bgTile = ii.getImage();
    }

    @Override
    public void anim() {
        //no animation
    }

}
