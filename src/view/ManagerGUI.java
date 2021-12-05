package view;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import model.*;

public class ManagerGUI extends JFrame implements ActionListener, MouseListener {

    private JLabel generalMessage1;
    
    private JButton editPropertyButton;
    private JButton editLandlordButton;
    private JButton editRenterButton;
    private JButton viewPropertyButton;
    private JButton viewLandlordButton;
    private JButton viewRenterButton;
    private JButton logoutButton;
    private JFrame parentFrame;
    private Manager mgr;


    public ManagerGUI(Manager mgr, JFrame parentFrame) {
        super("Manager System. Logged in as " + mgr.getFirstName() + " "+ mgr.getLastName() + ".");
        this.mgr= mgr;
        this.parentFrame = parentFrame;
        setupGUI();
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Sets gui.
     */
    public void setupGUI() {
        
    	String options[]= {"Edit","View"};
    	//Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        generalMessage1 = new JLabel("Choose one of the Options : ");

//        usernameTextField = new JTextField("User's username", 18);
//        passwordTextField = new JTextField("User's password", 18);

        editPropertyButton = new JButton("Edit Property");
        editLandlordButton = new JButton("Edit Landlord");
        editRenterButton = new JButton("Edit Renter");
        
        viewPropertyButton = new JButton("View Property");
        viewLandlordButton = new JButton("View Landlord");
        viewRenterButton = new JButton("View Renter");
        
        logoutButton = new JButton("Logout");

        //add Mouse Listeners to the JTextFields and ActionListener to the JButton

        editPropertyButton.addActionListener(this);
        editLandlordButton.addActionListener(this);
        editRenterButton.addActionListener(this);
        
        viewPropertyButton.addActionListener(this);
        viewLandlordButton.addActionListener(this);
        viewRenterButton.addActionListener(this);
        
        logoutButton.addActionListener(e -> {
            this.setVisible(false);
            parentFrame.setVisible(true);
            this.dispose();
        });
        //Create the JPanels.
        JPanel mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel editPanel = new JPanel();
        JPanel viewPanel = new JPanel();
        JPanel backPanel = new JPanel();

        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        
        editPanel.setLayout(new FlowLayout());
        viewPanel.setLayout(new FlowLayout());
        backPanel.setLayout(new FlowLayout());
        //Add Components to the JPanels.

        headerPanel.add(generalMessage1);
        //headerPanel.add(generalMessage2);
        editPanel.add(editRenterButton);
        editPanel.add(editLandlordButton);
        editPanel.add(editPropertyButton);
        
        viewPanel.add(viewRenterButton);
        viewPanel.add(viewLandlordButton);
        viewPanel.add(viewPropertyButton);
        
        backPanel.add(logoutButton);
        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(editPanel);
        mainContainer.add(viewPanel);
        mainContainer.add(backPanel);
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
//        username = usernameTextField.getText();
//        password = passwordTextField.getText();
        //Attempt to create a databaseAccess object called database using the inputs provided by the user.
	    
    	if(e.getSource().equals(editRenterButton)) {
	    	
	    }
	    if(e.getSource().equals(editLandlordButton)) {
	    	
	    }
	    if(e.getSource().equals(editPropertyButton)) {
	    	
	    }
	    if(e.getSource().equals(viewRenterButton)) {
	    	
	    }
	    if(e.getSource().equals(viewLandlordButton)) {
	    	
	    }
	    if(e.getSource().equals(viewPropertyButton)) {
	    	
//	    	PropertyViewGUI loginFrame = new PropertyViewGUI(Property prop, User user);
//            EventQueue.invokeLater(() -> {
//                loginFrame.setVisible(true);
//            });
	    }
    }

    public void mouseClicked(MouseEvent event) {

//        if(event.getSource().equals(usernameTextField)) {
//            usernameTextField.setText("");
//        }
//
//        if(event.getSource().equals(passwordTextField)) {
//            passwordTextField.setText("");
//        }
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
