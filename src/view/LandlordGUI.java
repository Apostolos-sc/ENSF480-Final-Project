package view;

import model.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class LandlordGUI extends JFrame implements ActionListener, MouseListener {
    private JLabel generalMessage1;
    private JLabel generalMessage2;
   

    private JTextField usernameTextField;
    private JTextField passwordTextField;

    private JButton editProperty;
    private JButton editProfile;
    private JButton registerProperty;
    private JButton logout;

    public LandlordGUI() {
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
        
        editProperty = new JButton("Edit Property");
        editProfile = new JButton("Edit Profile");
        registerProperty = new JButton("Register Property");
        logout = new JButton("Logout");
        
        editProperty.addActionListener(this);
        editProfile.addActionListener(this);
        registerProperty.addActionListener(this);
        logout.addActionListener(this);
        //Create the JPanels.
        JPanel mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel editPropertyPanel = new JPanel();
        JPanel editProfilePanel = new JPanel();
        JPanel registerPropertyPanel = new JPanel();
        JPanel logoutPanel = new JPanel();
        
        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        editPropertyPanel.setLayout(new FlowLayout());
        editProfilePanel.setLayout(new FlowLayout());
        registerPropertyPanel.setLayout(new FlowLayout());
        logoutPanel.setLayout(new FlowLayout());
        //Add Components to the JPanels.

        headerPanel.add(generalMessage1);
        headerPanel.add(generalMessage2);
        editPropertyPanel.add(editProperty);
        editProfilePanel.add(editProfile);
        registerPropertyPanel.add(registerProperty);
        logoutPanel.add(logout);
        
        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(editPropertyPanel);
        mainContainer.add(editProfilePanel);
        mainContainer.add(registerPropertyPanel);
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
        if(e.getSource().equals(editProperty)) {
        	
        	
        }
        if(e.getSource().equals(editProfile)) {
        	 
        	
        }
        if(e.getSource().equals(registerProperty)) {
        	
        	
        }
        if(e.getSource().equals(logout)) {
        	super.dispose();
        	LoginGUI loginFrame = new LoginGUI();
            EventQueue.invokeLater(() -> {
                loginFrame.setVisible(true);
            });
        }
        //Attempt to create a databaseAccess object called database using the inputs provided by the user.
    }

    public void mouseClicked(MouseEvent event) {

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
