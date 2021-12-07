package view;

import model.*;
import controller.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * LandlordEditProfile has been deprecated.
 */
public class LandlordEditProfileGUI extends JFrame implements ActionListener, MouseListener {
    private JLabel generalMessage1;
    private JLabel generalMessage2;
  
    private JLabel fNameLabel;
    private JLabel lNameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel dobLabel;

    private JTextField fNameTextField;
    private JTextField lNameTextField;
    private JTextField emailTextField;
    private JTextField passwordTextField;
    private JTextField dobTextField;
    
    private JTable information;
    
    private JButton backButton;
    private JButton changeButton;
    
    private Landlord landlord;
    
    public LandlordEditProfileGUI(Landlord landlord) {
        super("Connect to Server.");
    	this.landlord=landlord;
        setupGUI();
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Sets gui.
     */
    public void setupGUI() {
        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
    	String firstName="First Name: ";
    	firstName+=landlord.getFirstName();
    	String lastName="Last Name: ";
    	lastName+=landlord.getLastName();
    	String email="Email: ";
    	email+=landlord.getEmail();
    	String password="Password: ";
    	password+=landlord.getPassword();
    	String dob="Date of Birth: ";
    	dob+=landlord.getDob();
    	
    	generalMessage1 = new JLabel("Edit Property University of Calgary");
        generalMessage2 = new JLabel("Property Management Software.");
        
        fNameLabel = new JLabel(firstName);
        lNameLabel = new JLabel(lastName);
        emailLabel = new JLabel(email);
        passwordLabel = new JLabel(password);
        dobLabel = new JLabel(dob);
        
        fNameTextField = new JTextField("Change First Name to...");
        lNameTextField= new JTextField("Change Last Name to...");
        emailTextField= new JTextField("Change email to...");
        passwordTextField= new JTextField("Change password to...");
        dobTextField= new JTextField("Change Date of Birth to...");
        
        fNameTextField.addMouseListener(this);
        lNameTextField.addMouseListener(this);
        emailTextField.addMouseListener(this);
        passwordTextField.addMouseListener(this);
        dobTextField.addMouseListener(this);
        
        //usernameLabel = new JLabel("Username      :");
        //passwordLabel = new JLabel("Password      :");
        
       // usernameTextField = new JTextField("User's username", 18);
       // passwordTextField = new JTextField("User's password", 18);

        //connectButton = new JButton("Register.");
        //add Mouse Listeners to the JTextFields and ActionListener to the JButton
        //usernameTextField.addMouseListener(this);
        //passwordTextField.addMouseListener(this);
        //connectButton.addActionListener(this);
        backButton = new JButton("Back");
        changeButton= new JButton("Change");
        
        backButton.addActionListener(this);
        changeButton.addActionListener(this);
        //Create the JPanels.
        JPanel mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel fNamePanel = new JPanel();
        JPanel lNamePanel = new JPanel();
        JPanel emailPanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JPanel dobPanel = new JPanel();

        JPanel logoutPanel = new JPanel();
        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        fNamePanel.setLayout(new FlowLayout());
        lNamePanel.setLayout(new FlowLayout());
        emailPanel.setLayout(new FlowLayout());
        passwordPanel.setLayout(new FlowLayout());
        dobPanel.setLayout(new FlowLayout());
        logoutPanel.setLayout(new FlowLayout());
        //Add Components to the JPanels.

        headerPanel.add(generalMessage1);
        headerPanel.add(generalMessage2);
        
        fNamePanel.add(fNameLabel);
        fNamePanel.add(fNameTextField);
        
        lNamePanel.add(lNameLabel);
        lNamePanel.add(lNameTextField);
        
        emailPanel.add(emailLabel);
        emailPanel.add(emailTextField);
        
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);
        
        dobPanel.add(dobLabel);
        dobPanel.add(dobTextField);
        
        logoutPanel.add(backButton);
        logoutPanel.add(changeButton);
        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(fNamePanel);
        mainContainer.add(lNamePanel);
        mainContainer.add(emailPanel);
        mainContainer.add(passwordPanel);
        mainContainer.add(dobPanel);
        
        mainContainer.add(logoutPanel);
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
       
        if(e.getSource().equals(backButton)) {
        	super.dispose();
        }
        if(e.getSource().equals(changeButton)) {
        	String newFName= fNameTextField.getText();
        	String newlName= lNameTextField.getText();
        	String newEmail= emailTextField.getText();
        	String newPassword= passwordTextField.getText();
        	String newDob=dobTextField.getText();
        	
        	landlord.setDob(newDob);
        	landlord.setEmail(newEmail);
        	landlord.setFirstName(newFName);
        	landlord.setLastName(newlName);
        	landlord.setPassword(newPassword);
        	System.out.println(newFName+" "+newlName+" "+newEmail+" "+newPassword+" "+newDob);
        	//CALL CONTROLLER FUNCTION TO CHANGE landlord
        	JOptionPane.showMessageDialog(null, "Profile Information Changed!.");

        }
        //Attempt to create a databaseAccess object called database using the inputs provided by the user.
    }

    public void mouseClicked(MouseEvent event) {

        if(event.getSource().equals(fNameTextField)) {
        	fNameTextField.setText("");
        }

        if(event.getSource().equals(lNameTextField)) {
        	lNameTextField.setText("");
        }
        if(event.getSource().equals(emailTextField)) {
        	emailTextField.setText("");
        }
        if(event.getSource().equals(passwordTextField)) {
            passwordTextField.setText("");
        } 
        if(event.getSource().equals(dobTextField)) {
        	dobTextField.setText("");
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
