
/*
 * The Trampoline class extends collider and draws the trampoline
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

public class Trampoline extends Collider {

    Image trampolineImage;
    GameCanvas gc;
    public Trampoline(int x, int y, int width, int height, GameCanvas gc, Color c) {
        super(x, y, width, height, gc, c);
        this.gc = gc;
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        loadImage();
        g2d.drawImage(trampolineImage, x, y, width, height, gc);
    }

   
    @Override
   public boolean isTrampoline(){
        return true;
    }
   
   public void loadImage() {
        ImageIcon ii = new ImageIcon("src/images/Assets/Trampoline.png");
       trampolineImage= ii.getImage();
    }
    
}
