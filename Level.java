
/*
 * This class is in charge of creating the levels for the game.
 * Level reads text files and adds details from the text file to the constructors for
 * various objects for easier level building.
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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;

public class Level {

    public static ArrayList<DrawingObject> lis = new ArrayList<DrawingObject>();
    public static GameFrame gf;

    /*
     * The loadLevel() method loads the objects unto the level as well as
     * add the objects to the colliders ArrayList.
     */
    public static void loadLevel(int level) {
        System.out.println("Loading level " + level);
        GameCanvas.lis.clear();
        lis.clear();
        GameCanvas.colliders.clear();
        try {
            Scanner sc = new Scanner(new File("src/levels/level" + level + ".txt"));
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                loadObject(s);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Level does not exist");
        } catch (Exception e) {
            System.out.println("Error loading level, make sure level is written properly");
        }
        for (DrawingObject d : lis) {
            try {
                GameCanvas.colliders.add((Collider) d);
            } catch (Exception e) {

            }
        }
        MovingObject.setPlayer();
        GameCanvas.gc.pc2.x = GameCanvas.gc.startCharX;
        GameCanvas.gc.pc2.y = GameCanvas.gc.startCharY;
        lis.add(GameCanvas.gc.pc2);
        GameCanvas.gc.pc.x = GameCanvas.gc.startCharX;
        GameCanvas.gc.pc.y = GameCanvas.gc.startCharY;
        lis.add(GameCanvas.gc.pc);
        GameCanvas.lis.addAll(lis);
        gf.initiateAnimation();
    }

    /*
     * This method gets the details from the text files and loads them.
     */
    public static void loadObject(String object) {
        String[] details = object.split(" ");
        switch (details[0]) {
            case "//":
                break;
                
            case "Collider":
                try {
                    loadCollider(details[1], details[2], details[3], details[4], details[5], details[8]);
                } catch (Exception e) {
                    loadCollider(details[1], details[2], details[3], details[4], details[5], "false");
                }
                break;

            case "Goal":
                loadGoal(details[1], details[2], details[3], details[4]);
                break;

            case "Hazards":
                loadHazard(details[1], details[2], details[3], details[4]);
                break;

            case "Trampoline":
                loadTrampoline(details[1], details[2], details[3], details[4]);
                break;

            case "MovingObject":
                loadMovingObject(details[1], details[2], details[3], details[4], details[5], details[6]);
                break;

        }
    }

    /*
     * The loadCollider() method places the Collider objects into the Collider ArrayList.
     */
    public static void loadCollider(String xs, String ys, String ws, String hs, String cs, String ts) {
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        int w = Integer.parseInt(ws);
        int h = Integer.parseInt(hs);
        Color c = Color.BLACK;
        switch (cs) {
            case "red":
                c = Color.red;
                break;
            case "blue":
                c = Color.blue;
                break;
            case "purple":
                c = new Color(102, 45, 145);
                break;
            case "gray":
                c = Color.gray;
                break;
        }
        boolean t = ts.equals("true");
        lis.add(new Collider(x, y, w, h, GameCanvas.gc, c, t));
    }
    /*
     * This method loads the goal.
     */
    public static void loadGoal(String xs, String ys, String ws, String hs) {
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        int w = Integer.parseInt(ws);
        int h = Integer.parseInt(hs);
        GameCanvas.gc.g1 = new Goal(x, y, w, h, GameCanvas.gc, Color.black);
        lis.add(GameCanvas.gc.g1);

    }
    /*
     * This method loads Hazards.
     */

    public static void loadHazard(String xs, String ys, String ws, String hs) {
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        int w = Integer.parseInt(ws);
        int h = Integer.parseInt(hs);
        lis.add(new Hazards(x, y, w, h, GameCanvas.gc, Color.black));

    }
    
    /*
     * This method loads Trampolines.
     */

    public static void loadTrampoline(String xs, String ys, String ws, String hs) {
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        int w = Integer.parseInt(ws);
        int h = Integer.parseInt(hs);
        lis.add(new Trampoline(x, y, w, h, GameCanvas.gc, Color.black));

    }
    /*
     * This method loads MovingObjects.
     */

    public static void loadMovingObject(String xs, String ys, String ws, String hs, String dt, String p1s) {
        int x = Integer.parseInt(xs);
        int y = Integer.parseInt(ys);
        int w = Integer.parseInt(ws);
        int h = Integer.parseInt(hs);
        int dist = Integer.parseInt(dt);
        boolean b = p1s.equals("true");
        lis.add(new MovingObject(x, y, w, h, GameCanvas.gc, dist, b));

    }

}
