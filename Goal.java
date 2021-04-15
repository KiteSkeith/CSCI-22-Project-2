
/*
 * This class extends the Collider class and has the main objective of the game. 
 * Once both characters collide with the goal, they move on to the next level.
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
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Goal extends Collider {

    private boolean win = false;
    private Image torch;

    private ArrayList<PlayerCharacter> finishedPlayers = new ArrayList<PlayerCharacter>();
    Image goal;
    GameCanvas gc;
    /*
     * The constructor accepts 4 integers for the x, y, width, and height,
     * as well as a GameCanvas and a color.
     */
    public Goal(int x, int y, int width, int height, GameCanvas gc, Color c) {
        super(x, y, width, height, gc, c, true);
    }
    /*
     * The draw() method draws the image.
     */
    @Override
    public void draw(Graphics2D g2d) {
        loadImage();
        if (finishedPlayers.size() < 2) {
            g2d.drawImage(goal, x, y, width, height, gc);
            g2d.drawImage(torch, (int) x - 38, (int) y + 20, (int) (width * 0.3), (int) (height * 0.5), gc);
            g2d.drawImage(torch, (int) x + 110, (int) y + 20, (int) (width * 0.3), (int) (height * 0.5), gc);
        } else {
            g2d.drawImage(goal, (int) (x - width * 0.55), (int) (y - height * 0.45), (int) (width * 2.1), (int) (height * 1.45), gc);
        }
    }
    /*
     * loadImage() method uses the checkWin() method to check for the state of the goal to know
     * which image to load. Also loads the torch image.    
     */
    public void loadImage() {
        this.checkWin();

        ImageIcon tt = new ImageIcon("src/images/Assets/Torch.png");
        torch = tt.getImage();
    }
    /*
     * triggerEffect() checks for number of players inside the goal and returns
     * a boolean to check if they win or not.
     */
    @Override
    public boolean triggerEffect(PlayerCharacter c) {
        if (!finishedPlayers.contains(c)) {
            finishedPlayers.add(c);
            System.out.println(c.character + " entered door");
            System.out.println(finishedPlayers.size() + " characters in door");
        }
        if (finishedPlayers.size() == 2) {
            win = true;
            return true;
        } else {
            win = false;
        }
        return false;
    }
    /*
     * undoTrigger() undoes the effects of triggerEffect by removing the player from
     * the ArrayList in the case that the player leaves the goal area.
     */
    @Override
    public void undoTrigger(PlayerCharacter c) {
        if (finishedPlayers.contains(c)) {
            System.out.println(c.character + " left door");
        }
        finishedPlayers.remove(c);
        win = false;
    }
    /*
     * checkWin() checks whether the win conditions have been met and changes the goal state
     * accordingly.
     */
    public boolean checkWin() {
        if (!win) {
            ImageIcon ii = new ImageIcon("src/images/Assets/closedDoor.png");
            goal = ii.getImage();
        } else {
            ImageIcon ii = new ImageIcon("src/images/Assets/openDoor.png");
            goal = ii.getImage();
            return true;
        }

        return false;
    }
}
