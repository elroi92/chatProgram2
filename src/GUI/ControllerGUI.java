package GUI;

import chat.Connection;
import chat.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

/**
 * Created by elroi on 2015-09-01.
 */
public class ControllerGUI extends JFrame {
    private static final String WINDOW_NAME = "SUPERCHAT";
    private static final String DEFAULT_NAME = "Default name";
    private static final int DEFAULT_PORT = 8888;

    private int listeningPort = 0;
    private String userName = "";

    private MainController controller;

    private JList<Connection> listOfConnections = new JList<>();

    public ControllerGUI(MainController c){
        super(WINDOW_NAME);
        controller = c;

        promptName();
        promptPort();

        buildMainWindow();
    }

    /**
     * Builds the main window by adding all fields and buttons as well as making the window visible. It also adds
     * listeners to all the buttons and a listener so that the default close operation becomes a method call.
     */
    private void buildMainWindow() {
        JPanel panel = new JPanel(new GridBagLayout());
        Dimension mainWindowDimension = new Dimension(600, 400);
        JButton newConnectionButton = new JButton("Add new connection");
        JButton newRegularChatButton = new JButton("Start a new chat");
        JButton newCaesarChatButton = new JButton("Start a new Ceasar encrypted chat");
        JButton newAESChat = new JButton("Start a new AES encrypted chat");

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);
        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(new JLabel("Name: "+ userName), c);
        c.gridx = 1;
        panel.add(new JLabel("Local port: " + listeningPort), c);
        c.insets = new Insets(0, 0, 0, 0);
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 2;    // just some space
        panel.add(new JPanel(), c); // just some space
        c.weightx = 1;
        c.weighty = 1;
        c.gridy = 3;
        panel.add(newConnectionButton, c);
        c.gridy = 4;
        panel.add(newRegularChatButton, c);
        c.gridy = 5;
        panel.add(newCaesarChatButton, c);
        c.gridy = 6;
        panel.add(newAESChat, c);
        c.weightx = 0;
        c.weighty = 0;
        c.gridy = 7;    // just some space
        panel.add(new JPanel(), c); // just some space
        c.insets = new Insets(10, 20, 0, 0);
        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(new JLabel("List of connections"), c);
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 20, 20, 10);
        c.gridy = 3;
        c.gridheight = 5;
        JScrollPane myScrollPane = new JScrollPane(listOfConnections);
        myScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        myScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(myScrollPane, c);
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0;
        c.gridheight = 1;
        c.gridwidth = 2;
        panel.add(new JSeparator(), c);

        setPreferredSize(mainWindowDimension);
        add(panel);
        pack();
        setVisible(true);

        newConnectionButton.addActionListener(e -> {
            controller.addNewConnection();
        });
        newRegularChatButton.addActionListener(e -> {
           // TODO start unencrypted chat
        });
        newCaesarChatButton.addActionListener(e -> {
            // TODO start Caesar chat, se the selected connections from the list

        });
        newAESChat.addActionListener(e -> {
            // TODO Start AES chat, se the selected connections from the list

        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                controller.controllerGUIClosed();
            }
        });
    }

    /**
     * Dialog that prompts the user to supply port to listen on for incoming connections
     */
    private void promptName() {
        userName = JOptionPane.showInputDialog(
                this,
                "Please enter desired name",
                "Customized Dialog",            // TODO change name
                JOptionPane.PLAIN_MESSAGE);
        if (userName == null) {
            userName = DEFAULT_NAME;
        } else if (userName == "") {
            userName = DEFAULT_NAME;
        }     // TODO check if name field was left empty
    }

    /**
     * Dialog that prompts the user to supply username
     */
    private void promptPort() {
        try {
            listeningPort = Integer.parseInt(JOptionPane.showInputDialog(
                    this,
                    "Please enter desired port to listen on",
                    "Customized Dialog",        // TODO change name
                    JOptionPane.PLAIN_MESSAGE));
        } catch (NumberFormatException nfe) {
            listeningPort = DEFAULT_PORT;
        }
    }

    public int getListeningPort() {
        return listeningPort;
    }

    public String getUserName() {
        return userName;
    }

    public void updateConnections(Vector<Connection> connections){
        listOfConnections.setListData(connections);
    }

}
