package view;

import model.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class RegisteredRenterGUI extends JFrame implements ActionListener, MouseListener {
    private String subDescription;
    private JLabel generalMessage1;
    private JLabel generalMessage2;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JTextField usernameTextField;
    private JTextField passwordTextField;

    private JToggleButton subscribeMenuButton;
    private JButton viewPropertyButton;
    private JButton payButton;
    private JButton messageButton;
    private JButton inboxButton;

    public RegisteredRenterGUI() {
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
        generalMessage1 = new JLabel("Registered Renter University of Calgary");
        generalMessage2 = new JLabel("Property Management Software.");
        //usernameLabel = new JLabel("Username      :");
        //passwordLabel = new JLabel("Password      :");
        
       // usernameTextField = new JTextField("User's username", 18);
       // passwordTextField = new JTextField("User's password", 18);

        //connectButton = new JButton("Register.");
        //add Mouse Listeners to the JTextFields and ActionListener to the JButton
        //usernameTextField.addMouseListener(this);
        //passwordTextField.addMouseListener(this);
        //connectButton.addActionListener(this);
        subDescription="Unsubscribed";
        subscribeMenuButton = new JToggleButton(subDescription);
        viewPropertyButton = new JButton("View Property");
        payButton = new JButton("Pay");
        messageButton = new JButton("Email Message");
        inboxButton = new JButton("Inbox");
        
        subscribeMenuButton.addActionListener(this);
        viewPropertyButton.addActionListener(this);
        payButton.addActionListener(this);
        messageButton.addActionListener(this);
        inboxButton.addActionListener(this);
        //Create the JPanels.
        JPanel mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel subscribePanel = new JPanel();
        JPanel viewPropertyPanel = new JPanel();
        JPanel payPanel = new JPanel();
        JPanel messagePanel = new JPanel();
        JPanel inboxPanel = new JPanel();
        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        subscribePanel.setLayout(new FlowLayout());
        viewPropertyPanel.setLayout(new FlowLayout());
        payPanel.setLayout(new FlowLayout());
        messagePanel.setLayout(new FlowLayout());
        inboxPanel.setLayout(new FlowLayout());
        //Add Components to the JPanels.

        headerPanel.add(generalMessage1);
        headerPanel.add(generalMessage2);
        subscribePanel.add(subscribeMenuButton);
        viewPropertyPanel.add(viewPropertyButton);
        payPanel.add(payButton);
        messagePanel.add(messageButton);
        inboxPanel.add(inboxButton);
        
        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(subscribePanel);
        mainContainer.add(viewPropertyPanel);
        mainContainer.add(payPanel);
        mainContainer.add(messagePanel);
        mainContainer.add(inboxPanel);
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
        if(e.getSource().equals(subscribeMenuButton)) {
        	if(subDescription=="Unsubscribed") {
        		subDescription="Subscribed";
        		subscribeMenuButton.setText(subDescription);
        	}
        	else {
        		System.out.println("Test");

        		subDescription="Unsubscribed";
        		subscribeMenuButton.setText(subDescription);
        	}
        	
        }
        if(e.getSource().equals(messageButton)) {
        	 Reply loginFrame = new Reply();
             EventQueue.invokeLater(() -> {
                 loginFrame.setVisible(true);
             });

        }
        if(e.getSource().equals(payButton)) {
        	
        }
        if(e.getSource().equals(viewPropertyButton)) {
        	/*
        	 *NEED TO RETRIEVE DATA FROM DATABASE AND CALL PROPERTYVIEWGUI this is just an example*
        	 
        	Property p1= new Property(1, "Apartment", 3, 2, true, "123 University Rd", "NW","Available");
        	User u1= new User("John","Bob", "JohnBob123@gmail.com","password", "June 5,1990");

        	PropertyViewGUI loginFrame = new PropertyViewGUI(p1,u1);
            EventQueue.invokeLater(() -> {
                loginFrame.setVisible(true);
            });
            */
        }
        if(e.getSource().equals(inboxButton)) {
        	Inbox loginFrame = new Inbox();
            EventQueue.invokeLater(() -> {
                loginFrame.setVisible(true);
            });
        }
        //Attempt to create a databaseAccess object called database using the inputs provided by the user.
    }

    public void mouseClicked(MouseEvent event) {

        if(event.getSource().equals(usernameTextField)) {
            usernameTextField.setText("");
        }

        if(event.getSource().equals(passwordTextField)) {
            passwordTextField.setText("");
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
}