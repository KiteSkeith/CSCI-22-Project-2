import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
    
    private int width;
    private int height;
    private String title;
    private JPanel contentPane;
    GameCanvas gc;
    
    public GameFrame (int w, int h, String t, GameCanvas drawCan) {

        width = w;
        height = h;
        title = t;
        gc = drawCan;
        contentPane = (JPanel) getContentPane();
        contentPane.setFocusable(true);

    }
    
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
}
