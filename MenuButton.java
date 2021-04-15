/*
 * The MenuButton class is the class for the buttons in the menu.
 * 
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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

public class MenuButton {

    public Rectangle2D.Double Button;
    public String buttonText;
    public int x, y, width, height;
    public boolean isSelected;

    private static Font monoFont = new Font("Monospaced", Font.BOLD
            | Font.ITALIC, 36);

    /**
     * Initiates the values of the buttons
     *
     * @param x position at x
     * @param y position at y
     * @param width width of button
     * @param height height of button
     * @param s Text within on button
     */
    public MenuButton(int x, int y, int width, int height, String s, boolean isSelected) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        buttonText = s;
        this.isSelected = isSelected;
        Button = new Rectangle2D.Double(x, y, width, height);
    }

    /**
     * Draws the button on the screen
     *
     * @param g2d - the Graphics2D used in painting
     */
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        g2d.fill(Button);
        if (isSelected) {
            g2d.setColor(Color.red);
        } else {
            g2d.setColor(Color.black);
        }
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(Button);
        g2d.setFont(monoFont);
        g2d.setColor(Color.black);
        g2d.drawString(buttonText, (int) (x + width * 0.275), (int) (y + height * 0.70));

    }

    //shows if the button is selected by the player
    public boolean isSelected() {
        return isSelected;
    }

    //if button is selected, deselect. Vise Versa
    public void switchSelection() {
        if (isSelected) {
            isSelected = false;
        } else {
            isSelected = true;
        }
    }

}
