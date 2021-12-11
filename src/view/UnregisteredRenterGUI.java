/** 
@author Dave Sharma <a href="mailto:dave.sharma@ucalgary.ca">agampreet.aulakh@ucalgary.ca </a>
Nuha Shaikh <a href="mailto:nuha.shaikh1@ucalgary.ca">nuha.shaikh1@ucalgary.ca</a>
Apostolos Scondrianis <a href="mailto:apostolos.scondriani@ucalgary.ca">huda.abbas@ucalgary.ca</a>
Harsh Sharma <a href= "mailto:harshit.sharma@ucalgary.ca">melanie.nguyen@ucalgary.ca</a>
@version 2.2
@since  2.0
  * Data.java (insert explanation of class)
*/
package view;

import model.*;
import controller.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;


public class UnregisteredRenterGUI extends JFrame implements ActionListener, MouseListener {
    private JTable table;
	
    private String data[][];
	private JLabel generalMessage1;
    private JLabel generalMessage2;
    private JLabel bedroomLabel;
    private JLabel bathroomLabel;
    private JLabel quadrantLabel;
    private JLabel furnishingLabel;
    private JLabel propertyTypeLabel;
    private JLabel priceLabel;
    
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField priceTextField;
    
    private JComboBox<String> bedrooms;
    private JComboBox<String> bathrooms;
    private JComboBox<String> quadrant;
    private JComboBox<String> price;
    private JComboBox<String> furnishing;
    private JComboBox<String> propertyType;
    
    private JButton searchButton;
    private JButton exitButton;
    private JButton searchAllButton;
    
    private Renter renter;
    private Frame parentFrame;

    public UnregisteredRenterGUI(Frame parentFrame) {
        //super("Registered Renter System. Logged in as "+ renter.getFirstName() + " " + renter.getLastName() + ".");
        this.parentFrame = parentFrame;
        setupGUI();
        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Sets gui.
     */
    public void setupGUI() {
        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        generalMessage1 = new JLabel("Property Management Software.Guest User");
        generalMessage2 = new JLabel("Please select one of the options.");
        bedroomLabel = new JLabel("Bedroom:");
        bathroomLabel = new JLabel("Bathroom:");
        quadrantLabel = new JLabel("Quadrant:");
        furnishingLabel = new JLabel("Furnishing:");
        propertyTypeLabel = new JLabel("Property Type:");
        priceLabel = new JLabel("Price:");
        //usernameLabel = new JLabel("Username      :");
        //passwordLabel = new JLabel("Password      :");
        
       // usernameTextField = new JTextField("User's username", 18);
       // passwordTextField = new JTextField("User's password", 18);
        
        priceTextField = new JTextField("Price...");
        priceTextField.addMouseListener(this);
        //connectButton = new JButton("Register.");
        //add Mouse Listeners to the JTextFields and ActionListener to the JButton
        //usernameTextField.addMouseListener(this);
        //passwordTextField.addMouseListener(this);
        //connectButton.addActionListener(this);
        searchButton = new JButton("Search");
        exitButton = new JButton("Exit");
        searchAllButton = new JButton("Search All");
        
        String bedroomOptions[]= {"1","2","3","4","5"};
        String bathroomOptions[]= {"1","2","3","4","5"};
        String quadrantOptions[]= {"NW","NE","SW","SE"};
        String furnishingOptions[]= {"Furnished","Unfurnished"};
        String propertyOptions[]= {"Apartment","Attached","Detached","Townhouse"};
        
        String columns[]= {"Price","Address","Bedroom","Bathroom","Quadrant","Furnishing"};
        
        bedrooms = new JComboBox<String>(bedroomOptions);
        bathrooms = new JComboBox<String>(bathroomOptions);
        quadrant = new JComboBox<String>(quadrantOptions);
        //price = new JComboBox<String>(priceOptions);
        furnishing = new JComboBox<String>(furnishingOptions);
        propertyType = new JComboBox<String>(propertyOptions);
        
        String tmp[][]={{"$1000","101 Uni","1","5","NW","Furnished"},{"$1500","101 Mall","2","1","SW","Unfurnished"}};
        data=tmp;
        
        table =new JTable(data,columns);
        
        searchButton.addActionListener(this);
        exitButton.addActionListener(this);
        exitButton.addActionListener(e -> {
            this.setVisible(false);
            parentFrame.setVisible(true);
            this.dispose();
        });
        searchAllButton.addActionListener(this);
        //Create the JPanels.
        JPanel mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        topPanel.setLayout(new FlowLayout());
        middlePanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new FlowLayout());
        //tablePanel.setLayout(new FlowLayout());
        //Add Components to the JPanels.

        headerPanel.add(generalMessage1);
        headerPanel.add(generalMessage2);
        topPanel.add(bedroomLabel);
        topPanel.add(bedrooms);
        topPanel.add(bathroomLabel);
        topPanel.add(bathrooms);
        topPanel.add(quadrantLabel);
        topPanel.add(quadrant);
        topPanel.add(furnishingLabel);
        topPanel.add(furnishing);
        topPanel.add(propertyTypeLabel);
        topPanel.add(propertyType);
        middlePanel.add(priceLabel);
        middlePanel.add(priceTextField);
        bottomPanel.add(searchButton);
        bottomPanel.add(exitButton);
        bottomPanel.add(searchAllButton);
        //tablePanel.add(exitButton);
        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(topPanel);
        mainContainer.add(middlePanel);
        mainContainer.add(bottomPanel);
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
        String columns[]= {"Price","Address","Bedroom","Bathroom","Quadrant","Furnishing","Property Type"};
        
        if(e.getSource().equals(searchButton)) {
        	int noOfBed = Integer.valueOf(bedrooms.getSelectedItem().toString());
        	int noOfBath = Integer.valueOf(bathrooms.getSelectedItem().toString());
        	String furnish=furnishing.getSelectedItem().toString();
        	boolean furnishingValue;
        	if(furnish=="Furnished") {
        		furnishingValue=true;
        	}
        	else {
        		furnishingValue=false;
        	}
        	String quadrantValue=quadrant.getSelectedItem().toString();
        	String typeOfProperty= propertyType.getSelectedItem().toString();
        	int price = Integer.valueOf(priceTextField.getText());
//        	System.out.println(noOfBed+" "+noOfBath+" "+furnish+" "+quadrantValue+" "+typeOfProperty);
        	SingletonDatabaseAccess access=SingletonDatabaseAccess.getOnlyInstance();
        	SearchDatabase searchingDatabase=new SearchDatabase(access.getDBConnect());
        	
        	ArrayList<Property> array= searchingDatabase.searchItem("property",typeOfProperty , noOfBed, noOfBath, furnishingValue, quadrantValue, price);
        
        	
        	
        	String properties [][]=new String[array.size()][8];
        	
        	for(int i=0;i<array.size();i++) {
        		properties[i][0]= String.valueOf(array.get(i).getPropertyDetails().getPrice());  //???? Price ??????
        		properties[i][1]=array.get(i).getPropertyLocation().getAddress();
        		properties[i][2]=String.valueOf(array.get(i).getPropertyDetails().getNoBedrooms());
        		properties[i][3]=String.valueOf(array.get(i).getPropertyDetails().getNoBathrooms());
        		properties[i][4]=array.get(i).getPropertyLocation().getQuadrant();
        		boolean furnishedCheck=array.get(i).getPropertyDetails().isFurnished();
        		String furnishToString = new String();
        		if(furnishedCheck==true) {
        			furnishToString="Furnished";
        		}
        		else {
        			furnishToString="Unfurnished";
        		}
        		properties[i][5]=furnishToString;
        		properties[i][6]=array.get(i).getPropertyDetails().getPropertyType();
        		properties[i][7]=String.valueOf(array.get(i).getPropertyID());
        	}
        	
        	if(array.size()==0) {
            	JOptionPane.showMessageDialog(null, "Could not find any such property. Try Again!");
        	}
        	else {
        	 SearchPropertyGUI loginFrame = new SearchPropertyGUI(properties);
	          EventQueue.invokeLater(() -> {
	              loginFrame.setVisible(true);
	          });
        	}
        }
       
        if(e.getSource().equals(searchAllButton)) {
            //String tmp[][]={{"$1000","101 Uni","1","5","NW","Furnished"},{"$1500","101 Mall","2","1","SW","Unfurnished"}};
//            SearchPropertyGUI loginFrame = new SearchPropertyGUI(tmp);
//	          EventQueue.invokeLater(() -> {
//	              loginFrame.setVisible(true);
//	          });
        	SingletonDatabaseAccess access=SingletonDatabaseAccess.getOnlyInstance();
        	SearchDatabase searchingDatabase=new SearchDatabase(access.getDBConnect());
			
        	ArrayList<Property> array= searchingDatabase.getAllProperties("Property");
        	
			String properties [][]=new String[array.size()][8];
        	
        	for(int i=0;i<array.size();i++) {
        		properties[i][0]= String.valueOf(array.get(i).getPropertyDetails().getPrice());  //???? Price ??????
        		properties[i][1]=array.get(i).getPropertyLocation().getAddress();
        		properties[i][2]=String.valueOf(array.get(i).getPropertyDetails().getNoBedrooms());
        		properties[i][3]=String.valueOf(array.get(i).getPropertyDetails().getNoBathrooms());
        		properties[i][4]=array.get(i).getPropertyLocation().getQuadrant();
        		
        		boolean furnishedCheck=array.get(i).getPropertyDetails().isFurnished();
        		String furnishToString = new String();
        		
        		if(furnishedCheck==true) {
        			furnishToString="Furnished";
        		}
        		else {
        			furnishToString="Unfurnished";
        		}
        		properties[i][5]=furnishToString;
        		properties[i][6]=array.get(i).getPropertyDetails().getPropertyType();
        		properties[i][7]=String.valueOf(array.get(i).getPropertyID());
        	}
        	
        	if(array.size()==0) {
            	JOptionPane.showMessageDialog(null, "Could not find any such property. Try Again!");
        	}
        	else {
        	 SearchPropertyGUI loginFrame = new SearchPropertyGUI(properties);
	          EventQueue.invokeLater(() -> {
	              loginFrame.setVisible(true);
	          });
        	}
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

}
