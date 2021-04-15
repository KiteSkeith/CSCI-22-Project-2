
/**
 * The Game server of the game. The Host Player creates the server
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
import java.io.*;
import java.net.*;

public class GameServer {

    private ServerSocket ss;
    private int numPlayers;
    public float x, y, xVel, yVel;
    public ServerSideConnection scc, scc2;
    GameCanvas gc;

    //Creates the server
    public GameServer(GameCanvas gc) {
        this.gc = gc;
        x = GameCanvas.startCharX;
        y = GameCanvas.startCharY;
        xVel = 0;
        yVel = 0;
        System.out.println("The Server has been Established");
        numPlayers = 1;
        try {
            ss = new ServerSocket(51734);

        } catch (IOException ex) {
            System.out.println("IOExcepgtion from GameServer Constructor");
        }

    }

    //accepts connection and assigns server host as host player
    public void acceptConnections() {
        try {
            System.out.println("Waiting for connections...");
            while (numPlayers < 2) {
                Socket s = ss.accept();
                numPlayers++;
                System.out.println("A client has connected");

                scc = new ServerSideConnection(s, true);
                scc2 = new ServerSideConnection(s, false);
                Thread t = new Thread(scc);
                Thread t2 = new Thread(scc2);

                t.start();
                t2.start();

            }
            System.out.println("Player" + numPlayers + " has connected");
        } catch (IOException ex) {
            System.out.println("IOExcepgtion from acceptConnections()");
        }
    }

    //Server side connection
    private class ServerSideConnection implements Runnable {

        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;
        private boolean isSender;

        //PrintWriter writer;
        public ServerSideConnection(Socket s, boolean isSender) {
            socket = s;
            this.isSender = isSender;
            try {
                if (!isSender) {
                    dataIn = new DataInputStream(socket.getInputStream());
                } else {
                    dataOut = new DataOutputStream(socket.getOutputStream());
                }
                //writer = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException ex) {
                System.out.println("IOException from ServersSideConnection Constructor");
            }
        }

        @Override
        public void run() {
            while (true) {
                if (isSender) {
                    sendAction();
                } else {
                    receiveAction();
                }

            }
        }

        //sends action of the other player
        public void sendAction() {
            try {
                // writer.println(gc.pc.isGoingRight());
                // writer.println(gc.pc.isGoingLeft());
                //writer.println(gc.pc.isJumping());
                dataOut.writeInt(1);
                dataOut.writeFloat(gc.pc.getX());
                dataOut.flush();
                dataOut.writeInt(2);
                dataOut.writeFloat(gc.pc.getY());
                dataOut.flush();
                dataOut.writeInt(3);
                dataOut.writeFloat(gc.pc.getXVel());
                dataOut.flush();
                dataOut.writeInt(4);
                dataOut.writeFloat(gc.pc.getYVel());
                dataOut.flush();

            } catch (IOException ex) {
                System.out.println("IOException from sendAction");

            }

        }

        //receive Action of the other player
        public void receiveAction() {
            try {
                switch (dataIn.readInt()) {
                    case 1:
                        x = dataIn.readFloat();
                        break;

                    case 2:
                        y = dataIn.readFloat();
                        break;
                    case 3:
                        xVel = dataIn.readFloat();
                        break;
                    case 4:
                        yVel = dataIn.readFloat();
                        break;

                }

            } catch (IOException ex) {
                System.out.println("IOException from run in recieveACtion");

            }
        }

    }

}
