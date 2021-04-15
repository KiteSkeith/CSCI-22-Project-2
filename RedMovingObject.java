
/*
 * RedMovingObject extends movingObject.
 * Interacts with the Red Player
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

public class RedMovingObject extends MovingObject{
    
    private int prevX, prevY;
    private int yDir = -1;
    private int dist;
    
    
    public RedMovingObject(int x, int y, int width, int height, Color c, GameCanvas gc, int dist) {
        super(x, y, width, height,  gc, dist,true);
        prevY = y;
        this.dist = dist;
        
    }
    
    @Override
    public void anim(){
        if (c == Color.red)
            if (gc.pc.goRight || gc.pc.goLeft || gc.pc.yVel != 0)
                y += 5 * yDir;
                if (y <= prevY - dist){
                    y = prevY - dist;
                    yDir = yDir * -1;
                } else if (y >= prevY){
                    y = prevY;
                    yDir = yDir * -1;
                }
                
        
    }
    
}
