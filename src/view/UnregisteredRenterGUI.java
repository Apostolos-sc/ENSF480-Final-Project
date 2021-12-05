package view;

import model.*;
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

    private JTextField usernameTextField;
    private JTextField passwordTextField;

    private JComboBox<String> bedrooms;
    private JComboBox<String> bathrooms;
    private JComboBox<String> quadrant;
    private JComboBox<String> price;
    private JComboBox<String> furnishing;
    
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
        //usernameLabel = new JLabel("Username      :");
        //passwordLabel = new JLabel("Password      :");
        
       // usernameTextField = new JTextField("User's username", 18);
       // passwordTextField = new JTextField("User's password", 18);

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

        String columns[]= {"Price","Address","Bedroom","Bathroom","Quadrant","Furnishing"};
        
        bedrooms = new JComboBox<String>(bedroomOptions);
        bathrooms = new JComboBox<String>(bathroomOptions);
        quadrant = new JComboBox<String>(quadrantOptions);
        //price = new JComboBox<String>(priceOptions);
        furnishing = new JComboBox<String>(furnishingOptions);
        
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
        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        topPanel.setLayout(new FlowLayout());
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
        topPanel.add(searchButton);
        topPanel.add(exitButton);
        bottomPanel.add(searchAllButton);
        //tablePanel.add(exitButton);
        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(topPanel);
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
        String columns[]= {"Price","Address","Bedroom","Bathroom","Quadrant","Furnishing"};

        if(e.getSource().equals(searchButton)) {
	    		
        	
        }
        if(e.getSource().equals(searchAllButton)) {
            String tmp[][]={{"$1000","101 Uni","1","5","NW","Furnished"},{"$1500","101 Mall","2","1","SW","Unfurnished"}};
            SearchPropertyGUI loginFrame = new SearchPropertyGUI(tmp);
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