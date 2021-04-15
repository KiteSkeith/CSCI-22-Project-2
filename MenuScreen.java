
/*
 * The MenuScreen class is the class that changes the menu screens between the 
 * different menus. The menus include the FirstScreen, the Host Screen, the Join Screen, etc.
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
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class MenuScreen {

    private int width, height, cursorY;
    public MenuButton playButton, quitButton, hostButton, joinButton, joinGameButton;
    private Image cursor, wallpaper;
    private GameCanvas gc;
    private Rectangle2D.Double background;
    private static Font monoFont = new Font("Monospaced", Font.BOLD
            | Font.ITALIC, 36);
    public JTextField joinTextField;
    public TitleObject title;

    /*
     * The constructor accepts 2 integer arguments and a GameCanvas argument.
     */
    public MenuScreen(int width, int height, GameCanvas gc) {

        this.width = width;
        this.height = height;
        this.gc = gc;
        cursorY = 460;
        playButton = new MenuButton(420, 450, 200, 75, "PLAY", true);
        quitButton = new MenuButton(420, 550, 200, 75, "QUIT", false);
        title = new TitleObject(gc);
        hostButton = new MenuButton(420, 450, 200, 75, "HOST", true);
        joinButton = new MenuButton(420, 550, 200, 75, "JOIN", false);
        joinGameButton = new MenuButton(600, 390, 200, 75, "PLAY", false);
        joinTextField = new JTextField("insert IP Address here...");
        joinTextField.setBounds(width / 15, height / 2, width / 2, 100);
        joinTextField.setFont(monoFont);

    }

    //loads the first screen containing the button play and quit
    public void firstScreen(Graphics2D g2d) {
        loadMenuBackground();

        g2d.drawImage(wallpaper, 0, 0, gc);
        title.draw(g2d);
        playButton.draw(g2d);
        quitButton.draw(g2d);
        loadCursorImage();
        g2d.drawImage(cursor, 320, cursorY, gc);

    }

    //loads the image of the cursor
    private void loadCursorImage() {
        ImageIcon ii = new ImageIcon("src/images/Assets/Cursor.png");
        cursor = ii.getImage();
    }
    //loads the image to be used for the background
    private void loadMenuBackground() {
        ImageIcon ii = new ImageIcon("src/images/Assets/BGBackground.png");
        wallpaper = ii.getImage();
    }

    //changes the y value of cursor, changing the button it points at
    public void changeCursor(int y) {
        cursorY = y;
    }

    //shows the second screen which contains the screen of the second Image
    public void secondScreen(Graphics2D g2d) {

        g2d.drawImage(wallpaper, 0, 0, gc);
        title.draw(g2d);
        hostButton.draw(g2d);
        joinButton.draw(g2d);
        loadCursorImage();
        g2d.drawImage(cursor, 320, cursorY, gc);
    }

    //shows the screen when the host button is pressed
    public void hostScreen(Graphics2D g2d) {
        g2d.drawImage(wallpaper, 0, 0, gc);
        title.draw(g2d);
        g2d.setFont(monoFont);
        g2d.setColor(Color.white);
        String waitingText = "waiting for player...";
        g2d.drawString(waitingText, (int) (width * 0.25), (int) (height * 0.45));

    }

    //shows the screen when the join button is pressed
    public void joinScreen(Graphics2D g2d) {

        g2d.drawImage(wallpaper, 0, 0, gc);
        title.draw(g2d);
        gc.add(joinTextField);
        joinTextField.requestFocus();
        joinGameButton.draw(g2d);
    }

    //shows the screen when the person joins a game and waiting for a game to be found
    public void joinScreen2(Graphics2D g2d) {
        g2d.drawImage(wallpaper, 0, 0, gc);
        title.draw(g2d);
        g2d.setFont(monoFont);
        g2d.setColor(Color.white);
        gc.remove(joinTextField);
        String waitingText = "joining Game...";
        g2d.drawString(waitingText, (int) (width * 0.25), (int) (height * 0.45));

    }

    public JTextField returnText() {
        return joinTextField;
    }

}
