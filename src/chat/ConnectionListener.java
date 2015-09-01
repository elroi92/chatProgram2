package chat;

import GUI.ControllerGUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by elroi on 2015-09-01.
 */
public class ConnectionListener implements Runnable {
    private MainController  controller;
    private int             port;
    private ServerSocket    socket;

    public ConnectionListener(MainController c, int p) {
        controller = c;
        port = p;

        try{
            socket = new ServerSocket(port);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        (new Thread(this)).start();
    }


    @Override
    public void run() {
        while(true){
            try{
                System.out.println("Server: Listening for incoming connections at: IP: " + socket.getLocalSocketAddress()
                        + ", Port: " + socket.getLocalPort());
                Socket clientSocket = socket.accept();
                System.out.println("ConnectionListener.run: New connection accepted");
                controller.addNewConnection(new Connection(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
