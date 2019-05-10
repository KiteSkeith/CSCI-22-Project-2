import java.awt.*;

public class GameStarter {
    
    public static void main(String[] args) {
        
        GameCanvas gc = new GameCanvas(1024,768, Color.GRAY);
        GameFrame gf = new GameFrame(1024,768,"Greeting Card Project - Linaac", gc);
        gf.setUpFrame();
        
    }
    
}
