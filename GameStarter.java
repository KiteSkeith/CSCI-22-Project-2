/**
 * Game Starter contains the main method. Creates the game for the user
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
public class GameStarter {

    public static int width = 1024;
    public static int height = 768;

    
    public static void main(String[] args) {

        GameFrame gf = new GameFrame(width, height, "Dungeon Runner - Linaac, Peñaflor");
        gf.addKeyBindings();
        gf.setUpFrame();

    }

}
