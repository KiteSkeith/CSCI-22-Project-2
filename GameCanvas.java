import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class GameCanvas extends JComponent {
    
    private int width;
    private int height;
    private Color backgroundColor;
    
    public GameCanvas (int w, int h, Color c) {

        width = w;
        height = h;
        backgroundColor = c;
        setPreferredSize(new Dimension(width, height));
        
    }
    
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        //------------------BACKGROUND---------------------------
        Rectangle2D.Double background = new Rectangle2D.Double(0, 0, width, height);
        g2d.setColor(backgroundColor);
        g2d.fill(background);
        
    }
    
    public void setBackgroundColor(Color bgColor) {

        backgroundColor = bgColor;
    }
    
}