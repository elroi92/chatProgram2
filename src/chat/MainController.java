package chat;

import GUI.ControllerGUI;

import java.util.Vector;

/**
 * Created by elroi on 2015-09-01.
 */
public class MainController {
    private ControllerGUI GUI;

    private String userName;
    private int listeningPort;

    private Vector<Connection> connectionsList;


    MainController(){
        // Create GUI
        GUI = new ControllerGUI(this);

        // Get name and port
        userName = GUI.getUserName();
        listeningPort = GUI.getListeningPort();

        // start listener/server
        new ConnectionListener(this, GUI.getListeningPort());
    }

    public static void main(String[] args){
        new MainController();
    }

    public void addNewConnection(){

    }

    public void startChat(Vector<Connection> chatConnections){

    }

    public void controllerGUIClosed() {
        // TODO send disconnect messages
        System.exit(0);
    }

    public void addNewConnection(Connection c){
        connectionsList.add(c);
        GUI.updateConnections(connectionsList);
    }
}


