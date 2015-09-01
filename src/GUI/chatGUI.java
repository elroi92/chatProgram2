package GUI;

import chat.chatController;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by elroi on 2015-09-01.
 */
public class chatGUI extends JFrame {
    private chatController chat;
    private Document displayTextDocument;
    JTextPane displayArea;

    public chatGUI(chatController c, String windowName){
        super(windowName);
        chat = c;

        buildChatWindow();
    }

    public void messageReceived(String s){
        displayArea.setText(s);
    }

    /**
     * Builds the chat window by adding all fields and buttons as well as making the window visible. It also adds
     * listeners to all the buttons and a listener so that the default close operation becomes a method call.
     */
    private void buildChatWindow() {
        Dimension chatWindowDimension = new Dimension(500, 400);
        Dimension   writeAreDimensions  = new Dimension(0, 83);
        JPanel      mainPanel           = new JPanel(new GridBagLayout());
        JPanel      extrasPanel         = new JPanel();
        JScrollPane scrollDisplayArea   = new JScrollPane();
        displayArea         = new JTextPane();
        JEditorPane writeMessageArea    = new JEditorPane();
        JButton     sendTextButton      = new JButton("Send");
        JButton     sendFileButton      = new JButton("Send file");

        displayTextDocument = displayArea.getDocument();

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        scrollDisplayArea.add(displayArea);
        scrollDisplayArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollDisplayArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scrollDisplayArea, c);

        c.gridwidth = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 1;
        extrasPanel.setBackground(new Color(96, 92, 95));
        mainPanel.add(extrasPanel, c);

        c.gridx = 1;
        c.gridy = 1;
        mainPanel.add(sendFileButton, c);

        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 2;
        writeMessageArea.setPreferredSize(writeAreDimensions);
        mainPanel.add(writeMessageArea, c);

        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 2;
        mainPanel.add(sendTextButton, c);


        setPreferredSize(chatWindowDimension);
        add(mainPanel);
        pack();
        setVisible(true);

        sendTextButton.addActionListener(e -> {
            // TODO add XML colors and shit
            System.out.println("ChatGUI: Sending message: " + writeMessageArea.getText());
            chat.sendMessage(writeMessageArea.getText());
            writeMessageArea.setText("");
        });

        sendFileButton.addActionListener(e -> {
            // TODO send a file
            System.out.println("ChatGUI: Is supposed to send a file");
        });

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                closeChatWindowOperation();
            }
        });
    }

    private void closeChatWindowOperation() {
        // TODO close window and send terminating messages
    }

}
