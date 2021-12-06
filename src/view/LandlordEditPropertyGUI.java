package view;

import model.*;
import controller.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class LandlordEditPropertyGUI extends JFrame implements ActionListener, MouseListener {
    private JLabel generalMessage1;
    private JLabel generalMessage2;
  

    private JTextField usernameTextField;
    private JTextField passwordTextField;
    
    private JTable information;
    
    private JButton backButton;

    private Landlord landlord;
    
    public LandlordEditPropertyGUI(Landlord landlord) {
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
        generalMessage1 = new JLabel("Edit Property University of Calgary");
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
        String properties [][]=new String[landlord.getPropertySize()][8];
    	
    	for(int i=0;i<landlord.getPropertySize();i++) {
    		properties[i][0]="";  //???? Price ??????
    		properties[i][1]=landlord.getProperty(i).getPropertyLocation().getAddress();
    		properties[i][2]=String.valueOf(landlord.getProperty(i).getPropertyDetails().getNoBedrooms());
    		properties[i][3]=String.valueOf(landlord.getProperty(i).getPropertyDetails().getNoBathrooms());
    		properties[i][4]=landlord.getProperty(i).getPropertyLocation().getQuadrant();
    		
    		boolean furnishedCheck=landlord.getProperty(i).getPropertyDetails().isFurnished();
    		String furnishToString = new String();
    		
    		if(furnishedCheck==true) {
    			furnishToString="Furnished";
    		}
    		else {
    			furnishToString="Unfurnished";
    		}
    		properties[i][5]=furnishToString;
    		properties[i][6]=landlord.getProperty(i).getPropertyDetails().getPropertyType();
    		properties[i][7]=String.valueOf(landlord.getProperty(i).getPropertyID());
    	}
        String columns[]= {"Price","Address","Bedroom","Bathroom","Quadrant","Furnishing","Property Type"};

    	information=new JTable(properties,columns);
    	
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        //Create the JPanels.
        JPanel mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JScrollPane informationPanel = new JScrollPane(information);
        JPanel logoutPanel = new JPanel();
        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
       
        logoutPanel.setLayout(new FlowLayout());
        //Add Components to the JPanels.

        headerPanel.add(generalMessage1);
        headerPanel.add(generalMessage2);
        
        logoutPanel.add(backButton);
        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(informationPanel);
        
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
