/**
 * Is the window frame for the game. Creates the canvas
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
import java.awt.event.*;

public class GameFrame extends JFrame {

    private int width;
    private int height;
    private String title;
    private JPanel contentPane;
    private GameCanvas gc;
    private MenuButton menuButton1, menuButton2;
    private java.util.Timer timer;

    // Sets the title of the game
    public GameFrame(int w, int h, String t) {
        Level.gf = this;
        PlayerCharacter.gf = this;
        width = w;
        height = h;
        gc = new GameCanvas(width, height, Color.DARK_GRAY, this);
        title = t;
        gc.setPreferredSize(new Dimension(width, height));
        contentPane = (JPanel) getContentPane();
        contentPane.setFocusable(true);
        menuButton1 = gc.ms.playButton;
        menuButton2 = gc.ms.quitButton;

    }

    //sets up the frame for the program
    public void setUpFrame() {

        Container contentPane = getContentPane();
        setSize(width, height);
        setTitle(title);
        contentPane.add(gc, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    //add key Bindings for the menu
    public void addKeyBindings() {
        ActionMap am = contentPane.getActionMap();
        InputMap im = contentPane.getInputMap();
        AbstractAction switchButtons = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                menuButton1.switchSelection();
                menuButton2.switchSelection();
                if (menuButton1.isSelected()) {
                    gc.ms.changeCursor(460);
                } else {
                    gc.ms.changeCursor(550);
                }
                gc.repaint();
            }

        };

        AbstractAction buttonEvent = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (gc.ms.playButton.isSelected()) {

                    gc.screenStatus = "secondScreen";
                    menuButton1 = gc.ms.hostButton;
                    menuButton2 = gc.ms.joinButton;
                    gc.ms.playButton.switchSelection();

                } else if (gc.ms.quitButton.isSelected()) {
                    dispose();
                } else if (gc.ms.hostButton.isSelected()) {
                    gc.screenStatus = "hostScreen";
                    gc.ms.joinButton.switchSelection();
                } else if (gc.ms.joinButton.isSelected()) {
                    gc.screenStatus = "joinScreen";
                    gc.ms.joinButton.switchSelection();
                    menuButton1 = gc.ms.joinGameButton;
                    menuButton2 = gc.ms.joinGameButton;
                    gc.ms.joinGameButton.switchSelection();

                } else if (gc.ms.joinGameButton.isSelected()) {

                    gc.screenStatus = "joiningGame";

                }
                repaint();

            }
        };

        am.put("up", switchButtons);
        am.put("down", switchButtons);
        am.put("space", buttonEvent);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "down");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "space");

    }

    static boolean running = false;

    //iniates the timer for the animations
    public void initiateAnimation() {
        running = true;
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new AnimClock(gc.lis, this), 0, (long) AnimClock.frameRate);
        timer.scheduleAtFixedRate(new GameClock(gc.pc, gc.pc2, this, gc), 0, (long) GameClock.frameRate);
    }

    //stops the timer of the animations
    public void stopAnimation() {
        if (running) {
            running = false;
            timer.cancel();
            timer.purge();
            GameCanvas.gc.currentLevel++;
            Level.loadLevel(GameCanvas.gc.currentLevel);
        }
    }

    //The key strokes for the game. Override the menu key bindings
    public void InGameKeyStroke() {
        ActionMap am = contentPane.getActionMap();
        InputMap im = contentPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        AbstractAction goRight = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                gc.pc.right(true);
                ;
                gc.repaint();

            }
        };

        AbstractAction stopRight = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                gc.pc.right(false);

                gc.repaint();

            }
        };

        AbstractAction goLeft = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                gc.pc.left(true);

                gc.repaint();

            }
        };

        AbstractAction stopLeft = new AbstractAction() {

            @Override

            public void actionPerformed(ActionEvent ae) {
                gc.pc.left(false);

                gc.repaint();
            }
        };

        AbstractAction jump = new AbstractAction() {

            //jump = j
            @Override
            public void actionPerformed(ActionEvent ae) {

                gc.pc.jump();

                gc.repaint();
            }
        };
        am.clear();
        am.put("goRight", goRight);
        am.put("goLeft", goLeft);
        am.put("stopRight", stopRight);
        am.put("stopLeft", stopLeft);
        am.put("jump", jump);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "jump");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "jump");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "goRight");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "goLeft");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "stopRight");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "stopLeft");
    }

}
