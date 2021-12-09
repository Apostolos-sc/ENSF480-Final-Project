package view;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

import controller.*;
import model.*;

import java.awt.*;

public class Reply extends JFrame implements ActionListener, MouseListener {
	private String username;
    private String password;
    private JLabel generalMessage1;
    private JLabel generalMessage2;
    private JLabel usernameLabel;
    private JLabel senderLabel;
    private JLabel messageLabel;
    private JLabel propertyIDLabel;

    private JTextField usernameTextField;
    private JTextField senderTextField;
    private JTextField propertyIDTextField;

    private JButton send;
    private JButton cancel;
    private JTextArea message;

    public Reply() {
        super("Connect to Server.");
        setupGUI();
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Sets gui.
     */
    public void setupGUI() {
        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        generalMessage1 = new JLabel("Message");
        generalMessage2 = new JLabel("Property Management Software.");
        usernameLabel = new JLabel("Email to     :");
        senderLabel = new JLabel("Email from     :");
        propertyIDLabel = new JLabel("OR Property ID:   ");
        
        messageLabel = new JLabel("Message    :");
        //passwordLabel = new JLabel("Password      :");
        
        usernameTextField = new JTextField("Email to...", 18);
        senderTextField = new JTextField("Email from", 18);
        propertyIDTextField = new JTextField("Property ID",18);
        //passwordTextField = new JTextField("User's password", 18);

        send = new JButton("Send");
        cancel = new JButton("Cancel");
        //add Mouse Listeners to the JTextFields and ActionListener to the JButton
        usernameTextField.addMouseListener(this);
        senderTextField.addMouseListener(this);
        propertyIDTextField.addMouseListener(this);
       // connectButton.addActionListener(this);
        //Create the JPanels.
        message=new JTextArea(5,50);
        JPanel mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel usernamePanel = new JPanel();
        JPanel messagePanel = new JPanel();
        JPanel sendPanel = new JPanel();
        
        sendPanel.add(send);
        sendPanel.add(cancel);
        send.addActionListener(this);
        cancel.addActionListener(e -> {
            super.dispose();
        });
        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        usernamePanel.setLayout(new FlowLayout());
        //messagePanel.setLayout(new FlowLayout());
        sendPanel.setLayout(new FlowLayout());
        messagePanel.add(messageLabel);
        messagePanel.add(message);
        //Add Components to the JPanels.

        headerPanel.add(generalMessage1);
        headerPanel.add(generalMessage2);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);
        usernamePanel.add(propertyIDLabel);
        usernamePanel.add(propertyIDTextField);
        usernamePanel.add(senderLabel);
        usernamePanel.add(senderTextField);
        //connectPanel.add(connectButton);

        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(usernamePanel);
        mainContainer.add(messagePanel);
        mainContainer.add(sendPanel);

        //Add the main panel to the JFrame.
        this.add(mainContainer);
    }

    /**
     * actionPerformed function used to handle an action performed on the
     * Connect Button.
     * @param e ActionEvent passed on the function actionPerformed
     */
    public void actionPerformed(ActionEvent e) {
        //Pull the data from the JTextFields username, password and url
    	if(e.getSource().equals(send)) {
    		String reciever=usernameTextField.getText();
    		String sender = senderTextField.getText();
    		String messageToSend = message.getText();
    		
    		SingletonDatabaseAccess access=SingletonDatabaseAccess.getOnlyInstance();
    		
    		if(this.isInteger(propertyIDTextField.getText())) {
    			reciever=propertyIDTextField.getText();
    			
    			//System.out.println(reciever);
        		
        		SearchDatabase search=new SearchDatabase(access.getDBConnect());
        		String recieverEmail=search.getEmailFromID(Integer.valueOf(propertyIDTextField.getText()));
        		
        		
//        		for(int i=0;i<landlordArray.size();i++) {
//    				System.out.println(landlordArray.get(i).getPropertySize());
//
//        			for(int j=0;j<landlordArray.get(i).getProperties().size();j++) {
//    					System.out.println(landlordArray.get(i).getEmail());
//
//        				if(landlordArray.get(i).getProperty(j).getPropertyID()==Integer.valueOf(propertyIDTextField.getText())) {
//        					
//        					recieverEmail=landlordArray.get(i).getEmail();
//        					//System.out.println(arr.get(i).getProperty(j).getPropertyID());
//        					//System.out.println(arr.get(i).getEmail());
//        					break;
//        				}
//        			}
//        		}
        		
        		if(recieverEmail==null) {
                	JOptionPane.showMessageDialog(null, "Recipient could not recieve message. Try Again!");

        		}
        		else {
                
        			SearchDatabase searchInbox=new SearchDatabase(access.getDBConnect());
        			
        			//int id=searchInbox.inboxMaxID(); 
        		InboxMessages tmp=new InboxMessages(0,messageToSend,sender,recieverEmail);
	    		/*Send to database to add to inbox*/
	    		search.sendEmailMessage(tmp);
	        	JOptionPane.showMessageDialog(null, "Message Sent!");
        		
        		}
    		}
    		else {
       		 	String recieverEmail=reciever;
    	
       		 	SearchDatabase search=new SearchDatabase(access.getDBConnect());
	    		//	int id=search.inboxMaxID();    		 	
       		 	//System.out.println(messageToSend+" "+sender+" "+recieverEmail);
	    		InboxMessages tmp=new InboxMessages(0,messageToSend,sender,recieverEmail);
	    		/*Send to database to add to inbox*/
	    		search.sendEmailMessage(tmp);
	        	JOptionPane.showMessageDialog(null, "Message Sent!");
	
	    		
    		}
    	}
        //Attempt to create a databaseAccess object called database using the inputs provided by the user.
    }

    public void mouseClicked(MouseEvent event) {

        if(event.getSource().equals(usernameTextField)) {
            usernameTextField.setText("");
        }

        if(event.getSource().equals(senderTextField)) {
            senderTextField.setText("");
        }
        if(event.getSource().equals(propertyIDTextField)) {
        	propertyIDTextField.setText("");
        }
    }

    public void mouseExited(MouseEvent event) {

    }

    public void mousePressed(MouseEvent event) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent event) {

    }
    public boolean isInteger(String tmp) {
    	for(int i=0;i<tmp.length();i++) {
    		if(tmp.charAt(i)>57 || tmp.charAt(i)<48) {
    			return false;
    		}
    	}
    	return true;
    }
}
