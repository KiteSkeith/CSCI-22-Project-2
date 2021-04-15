
import java.awt.Graphics;
import java.awt.Graphics2D;

//OtherPlayer follows the controls through the network of the other player
public class OtherPlayer extends PlayerCharacter {

    GameCanvas gc;
    boolean isHost;
    private float newXVel, newYVel;

    public OtherPlayer(float x, float y, boolean isHost) {
  
        super(x, y, isHost);
              x = GameCanvas.startCharX;
        y = GameCanvas.startCharY;
        gc = GameCanvas.gc;
        this.isHost = isHost;
        newXVel = 0;
        newYVel = 0;

    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        //Player2Controls();

    }

    @Override
    public void move() {
        boolean isFalling = fall();
        //jumping initiates
        player2Velocity();
        if (newXVel != xVel) {
            xVel = newXVel;
        }
        yVel = newYVel;
        if (xVel > 0) {
            goRight = true;
            goLeft = false;
            direction = 1;
        } else if (xVel < 0) {
            goLeft = true;
            goRight = false;
            direction = -1;
        } else {
            goLeft = false;
            goRight = false;
        }
        prevX = x;

        // x value affected by xVelocity
        x += xVel;
        Corrector();
        //System.out.println(isFalling);

    }

    public void player2Velocity() {
        if (!isHost) {
            newYVel = gc.gs.yVel;
            newXVel = gc.gs.xVel;

        } else {
            newYVel = gc.gcl.yVel;
            newXVel = gc.gcl.xVel;
        }
    }

    public void Corrector() {
        if (!isHost) {
            if (Math.abs(gc.gs.x - x) > 60 || Math.abs(gc.gs.y - y) > 60) {
                changeX(gc.gs.x);
                changeY(gc.gs.y);
            } else if (Math.abs(gc.gs.x - x) > 30 || Math.abs(gc.gs.y - y) > 30) {
                float adjustX = gc.gs.x - x;
                float adjustY = gc.gs.y - y;
                while (x != gc.gs.x || y != gc.gs.y) {
                    x += adjustX / 2;
                    y += adjustY / 2;
                    if (Math.abs(gc.gs.x - x) > 60 || Math.abs(gc.gs.y - y) > 60) {
                        changeX(gc.gs.x);
                        changeY(gc.gs.y);
                    }
                }
                
            }
            } else {
            
                if (Math.abs(gc.gcl.x - x) > 60 || Math.abs(gc.gcl.y - y) > 60) {
                    changeX(gc.gcl.x);
                    changeY(gc.gcl.y);
                } else if (Math.abs(gc.gcl.x - x) > 30 || Math.abs(gc.gcl.y - y) > 30) {
                    float adjustX = gc.gcl.x - x;
                    float adjustY = gc.gcl.y - y;
                    while (x != gc.gcl.x || y != gc.gcl.y) {
                        x += adjustX / 2;
                        y += adjustY / 2;
                        if (Math.abs(gc.gcl.x - x) > 60 || Math.abs(gc.gcl.y - y) > 60) {
                            changeX(gc.gcl.x);
                            changeY(gc.gcl.y);

                        }
                    }

                }
            }

        }
    }

