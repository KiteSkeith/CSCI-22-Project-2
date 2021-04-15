
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GameClient {

    GameCanvas gc;
    ClientSideConnection csc, csc2;
    public float x, y, xVel, yVel;
    private Socket socket;
    private String ipAddress;

    public GameClient(String s) {
        ipAddress = s;
    }

    public void connectToServer() {
        gc = GameCanvas.gc;
        try {
            System.out.println(ipAddress);
            socket = new Socket(ipAddress, 51734);
        } catch (IOException ie) {
            System.out.println("error in making socket");
        }
        
         x = GameCanvas.startCharX;
        y = GameCanvas.startCharY;
        xVel = 0;
        yVel = 0;
        csc = new ClientSideConnection(socket, true);
        csc2 = new ClientSideConnection(socket, false);
        Thread t = new Thread(csc);
        Thread t2 = new Thread(csc2);
        t.start();
        t2.start();
    }

    public class ClientSideConnection implements Runnable {

        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;
        private boolean isSender;

        public ClientSideConnection(Socket socket, boolean isSender) {
            System.out.println("----Client----");
            this.isSender = isSender;
            try {
                this.socket = socket;
                if (!isSender) {
                    dataIn = new DataInputStream(socket.getInputStream());
                } else {
                    dataOut = new DataOutputStream(socket.getOutputStream());
                }
                //writer = new PrintWriter(socket.getOutputStream(), true);
                System.out.println("Succesfully Connected");
            } catch (IOException ex) {
                System.out.println("IOException in ClientsSideConnection");
            }
        }

        @Override
        public void run() {

            while (true) {
                if (!isSender) {
                    receiveAction();
                } else {

                    sendAction();

                }
            }
        }

        public void sendAction() {
            try {
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
