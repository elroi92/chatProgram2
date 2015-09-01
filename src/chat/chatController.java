package chat;

import GUI.chatGUI;

import java.util.Vector;

/**
 * Created by elroi on 2015-09-01.
 */
public class chatController {
    private chatGUI GUI;

    private String userName;
    private Vector<Connection> connections;

    public chatController(Vector<Connection> connections, String name){
        GUI = new chatGUI(this, "Chat Window Name");
    }


    public void sendMessage(String text) {
        // receives message from chatGUI
    }
}
