package com.liwux.io.V2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientFrame extends Frame {

    private static final ClientFrame INSTANCE = new ClientFrame();

    public static ClientFrame getInstance(){
        return INSTANCE;
    }

    TextArea textArea = new TextArea();
    TextField textField = new TextField();
    Client client =null;

    private ClientFrame(){
        this.setSize(600,400);
        this.setLocation(100,20);
        this.add(textArea,BorderLayout.CENTER);
        this.add(textField,BorderLayout.SOUTH);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send(textField.getText());
                //textArea.setText(textArea.getText()+textField.getText());
                textField.setText("");
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.closeConnect();
                System.exit(0);
            }
        });


//        this.setVisible(true);
//        this.connectToServer();

    }

    private void connectToServer(){
        client = new Client();
        client.connect();

    }

    public static void main(String[] args) {
        ClientFrame clientFrame = ClientFrame.INSTANCE;
        clientFrame.setVisible(true);
        clientFrame.connectToServer();
    }

    public void updateText(String msgAccepted) {
        this.textArea.setText(textArea.getText()+System.getProperty("line.separator")+msgAccepted);
    }
}
