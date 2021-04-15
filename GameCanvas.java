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
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class GameCanvas extends JComponent {

    private int width, height;
    public static int startCharX = 150, startCharY = 700;
    public MenuScreen ms;
    public static PlayerCharacter pc;
    public static OtherPlayer pc2;
    public static ArrayList<DrawingObject> lis = new ArrayList<DrawingObject>();
    public static GameCanvas gc;
    public BackGround bg;
    public static ArrayList<Collider> colliders = new ArrayList<Collider>();
    public GameFrame gf;
    public String screenStatus;
    public GameServer gs;
    public GameClient gcl;
    public int currentLevel = 1;

    public static Goal g1;

    // Creates The Canvas
    public GameCanvas(int w, int h, Color c, GameFrame gf) {
        this.gf = gf;
        gc = this;
        bg = new BackGround(this);

        width = w;
        height = h;
        screenStatus = "firstScreen";

        ms = new MenuScreen(width, height, this);

    }
    
    
    //paints the contents
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        bg.draw(g2d);
        if (screenStatus.equals("firstScreen")) {
            ms.firstScreen(g2d);
        } else if (screenStatus.equals("secondScreen")) {
            ms.secondScreen(g2d);
        } else if (screenStatus.equals("hostScreen")) {
            gf.InGameKeyStroke();
            ms.hostScreen(g2d);
            createServer();
        } else if (screenStatus.equals("joinScreen")) {
            ms.joinScreen(g2d);
        } else if (screenStatus.equals("joiningGame")) {
            gf.InGameKeyStroke();
            gc.remove(ms.joinTextField);
            joinServer();

        } else if (screenStatus.equals("inGame")) {
            for (DrawingObject d : lis) {
                d.draw(g2d);
            }
        }

    }
    
    
    //when Host is pressed, server is made
    public void createServer() {

        pc = new PlayerCharacter(startCharX, startCharY, true);
        
        gs = new GameServer(gc);
        pc2 = new OtherPlayer(startCharX, startCharY, false);
        gs.acceptConnections();
        
        

        String filepath = "CSCI 22 PROJECT.wav";
        Music musicObject = new Music();
        musicObject.playMusic(filepath);
        Level.loadLevel(1);
        screenStatus = "inGame";
    }

    //the characters will join a server when join button is clicked
    public void joinServer() {

        pc = new PlayerCharacter(startCharX, startCharY, false);
       
        gcl = new GameClient(ms.returnText().getText());
         pc2 = new OtherPlayer(startCharX, startCharY, true);
        gcl.connectToServer();

        String filepath = "CSCI 22 PROJECT.wav";
        Music musicObject = new Music();
        musicObject.playMusic(filepath);
        Level.loadLevel(1);
        screenStatus = "inGame";

    }

}
