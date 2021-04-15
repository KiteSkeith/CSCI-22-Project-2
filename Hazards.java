
/*
 * This class extends the Collider class and draws the spikes in the level that
 * act as hazards for the player. 
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
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Hazards extends Collider {

    private Image hazardImage;
    GameCanvas gc;

    /*
     * The constructor accepts 4 integers for the x, y, width, and height,
     * as well as a GameCanvas and a color.
     */
    public Hazards(int x, int y, int width, int height, GameCanvas gc, Color c) {
        super(x, y, width, height, gc, c);
        this.gc = gc;
    }

    /*
     * The draw() method draws the image.
     */
    @Override
    public void draw(Graphics2D g2d) {
        loadImage();
        g2d.drawImage(hazardImage, x, y, width, height, gc);
    }

    /* 
     * The loadImage() method loads the image into an Image type.
     */
    public void loadImage() {
        ImageIcon ii = new ImageIcon("src/images/Assets/Spike.png");
        hazardImage = ii.getImage();
    }

    /*
     * isHazard() returns a boolean to check if the collider is
     * a hazard to the player.
     */
    @Override
    public boolean isHazard() {
        return true;
    }

}
