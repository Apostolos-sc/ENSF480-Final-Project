package view;

import model.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;


public class SearchPropertyGUI extends JFrame implements ActionListener, MouseListener {
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

    private JButton exitButton;
    
    private Renter renter;

    public SearchPropertyGUI(String array[][]) {
        //super("Registered Renter System. Logged in as "+ renter.getFirstName() + " " + renter.getLastName() + ".");
       // this.parentFrame = parentFrame;
        this.data=array;
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
     
        //usernameLabel = new JLabel("Username      :");
        //passwordLabel = new JLabel("Password      :");
        
       // usernameTextField = new JTextField("User's username", 18);
       // passwordTextField = new JTextField("User's password", 18);

        //connectButton = new JButton("Register.");
        //add Mouse Listeners to the JTextFields and ActionListener to the JButton
        //usernameTextField.addMouseListener(this);
        //passwordTextField.addMouseListener(this);
        //connectButton.addActionListener(this);
        exitButton = new JButton("Exit");
       
        String columns[]= {"Price","Address","Bedroom","Bathroom","Quadrant","Furnishing"};
           
        
        table =new JTable(data,columns);
        
        
        exitButton.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
        });
        //Create the JPanels.
        JPanel mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JScrollPane tablePanel = new JScrollPane(table);
        JPanel bottomPanel = new JPanel();
        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new FlowLayout());
        //tablePanel.setLayout(new FlowLayout());
        //Add Components to the JPanels.

        headerPanel.add(generalMessage1);
        headerPanel.add(generalMessage2);
        bottomPanel.add(exitButton);
        //tablePanel.add(exitButton);
        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(tablePanel);
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