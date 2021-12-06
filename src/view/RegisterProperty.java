package view;

import model.*;
import controller.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class RegisterProperty extends JFrame implements ActionListener, MouseListener {
    private JLabel generalMessage1;
    private JLabel generalMessage2;
    private JLabel propertyIDMessage;
    private JLabel bathroomsMessage;
    private JLabel bedroomsMessage;
    private JLabel furnishedMessage;
    private JLabel addressMessage;
    private JLabel quadrantMessage;
    private JLabel priceMessage;
    private JLabel propertyTypeLabel;

    private JTextField propertyIDTextField;
    private JTextField bathroomsTextField;
    private JTextField bedroomsTextField;
    private JTextField priceTextField;
    
    private JComboBox<String> furnishedComboField;
    private JComboBox<String> propertyType;

    
    private JTextField addressTextField;
    private JTextField quadrantTextField;

    private JTable information;
    
    private JButton backButton;
    private JButton registerButton;
    
    public RegisterProperty() {
        super("Connect to Server.");
        setupGUI();
        setSize(650,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Sets gui.
     */
    public void setupGUI() {
        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        generalMessage1 = new JLabel("Edit Property University of Calgary");
        generalMessage2 = new JLabel("Property Management Software.");
        propertyIDMessage = new JLabel("Property ID: ");
        bathroomsMessage = new JLabel("Number Bathrooms: ");
        bedroomsMessage = new JLabel("Number Bedrooms: ");
        furnishedMessage = new JLabel("Furnished: ");
        addressMessage = new JLabel("Address: ");
        quadrantMessage = new JLabel("Quadrant: ");
        priceMessage = new JLabel("Price:  ");
        propertyTypeLabel = new JLabel("Property Type:  ");
        //usernameLabel = new JLabel("Username      :");
        //passwordLabel = new JLabel("Password      :");
        
        propertyIDTextField = new JTextField("Property ID", 18);
        bathroomsTextField = new JTextField("Bathrooms", 18);
        bedroomsTextField = new JTextField("Bedrooms", 18);
        priceTextField = new JTextField("Price", 18);
        
        String options[] = {"Furnished","Not Furnished"};
        furnishedComboField = new JComboBox<String>(options);
        
        String propertyOptions[]= {"Apartment","Attatched","Detatched","Townhouse"};
        propertyType =new JComboBox<String>(propertyOptions);
        
        addressTextField = new JTextField("Address", 18);
        quadrantTextField = new JTextField("Quadrant", 18);
       // usernameTextField = new JTextField("User's username", 18);
       // passwordTextField = new JTextField("User's password", 18);

        //connectButton = new JButton("Register.");
        //add Mouse Listeners to the JTextFields and ActionListener to the JButton
        //usernameTextField.addMouseListener(this);
        //passwordTextField.addMouseListener(this);
        //connectButton.addActionListener(this);
        backButton = new JButton("Back");
        registerButton = new JButton("Register");
        
        backButton.addActionListener(this);
        registerButton.addActionListener(this);
        propertyIDTextField.addMouseListener(this);
        bathroomsTextField.addMouseListener(this);
        bedroomsTextField.addMouseListener(this);
        priceTextField.addMouseListener(this);
        
        addressTextField.addMouseListener(this);
        quadrantTextField.addMouseListener(this);
        
        //furnishedComboField.addActionListener(this);
        //Create the JPanels.
        JPanel mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel propertyPanel = new JPanel();
        JPanel BBPanel = new JPanel();
        JPanel furnishing = new JPanel();
        JPanel AQPanel = new JPanel();
        JPanel pricePanel = new JPanel();
        JPanel logoutPanel = new JPanel();
        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        propertyPanel.setLayout(new FlowLayout());
        BBPanel.setLayout(new FlowLayout());
        furnishing.setLayout(new FlowLayout());
        AQPanel.setLayout(new FlowLayout());
        pricePanel.setLayout(new FlowLayout());
        logoutPanel.setLayout(new FlowLayout());
       
        //Add Components to the JPanels.

        headerPanel.add(generalMessage1);
        headerPanel.add(generalMessage2);
        propertyPanel.add(propertyIDMessage);
        propertyPanel.add(propertyIDTextField);

        BBPanel.add(bedroomsMessage);
        BBPanel.add(bedroomsTextField);
        BBPanel.add(bathroomsMessage);
        BBPanel.add(bathroomsTextField);
        
        AQPanel.add(addressMessage);
        AQPanel.add(addressTextField);
        AQPanel.add(quadrantMessage);
        AQPanel.add(quadrantTextField);
        furnishing.add(furnishedMessage);
        furnishing.add(furnishedComboField);
        
        pricePanel.add(priceMessage);
        pricePanel.add(priceTextField);
        pricePanel.add(propertyTypeLabel);
        pricePanel.add(propertyType);
        
        logoutPanel.add(backButton);
        logoutPanel.add(registerButton);
        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(propertyPanel);
        mainContainer.add(BBPanel);
        mainContainer.add(AQPanel);
        mainContainer.add(furnishing);
        mainContainer.add(pricePanel);
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
        if(e.getSource().equals(registerButton)) {
        	
            if(isInteger(propertyIDTextField.getText())&&isInteger(bathroomsTextField.getText())
            		&&isInteger(bedroomsTextField.getText())&&addressTextField.getText()!=""&&quadrantTextField.getText()!="") {
            	
            	int propertyID = Integer.valueOf(propertyIDTextField.getText());
            	int bathrooms = Integer.valueOf(bathroomsTextField.getText());
            	int bedrooms = Integer.valueOf(bedroomsTextField.getText());
            	String address = addressTextField.getText();
            	String quadrant = quadrantTextField.getText();
            	boolean furnished = false;

                	if(furnishedComboField.getSelectedItem().toString()=="Furnished") {
                		furnished=true;
                	}
                	else{
                		furnished=false;
                	} 
                	
               String propertyTypeValue=propertyType.getSelectedItem().toString();
               
                	JOptionPane.showMessageDialog(null, "Property was registered");
            }
            else {
            	JOptionPane.showMessageDialog(null, "There was an error with your input. Please re-enter info.");
            }
        }
        
        //Attempt to create a databaseAccess object called database using the inputs provided by the user.
    }

    public void mouseClicked(MouseEvent event) {

        if(event.getSource().equals(propertyIDTextField)) {
        	propertyIDTextField.setText("");
        }

        if(event.getSource().equals(bathroomsTextField)) {
        	bathroomsTextField.setText("");
        }

        if(event.getSource().equals(bedroomsTextField)) {
        	bedroomsTextField.setText("");
        }
        if(event.getSource().equals(addressTextField)) {
        	addressTextField.setText("");
        }

        if(event.getSource().equals(quadrantTextField)) {
        	quadrantTextField.setText("");
        }
        if(event.getSource().equals(priceTextField)) {
        	priceTextField.setText("");
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
