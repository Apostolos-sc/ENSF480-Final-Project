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
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

import controller.SearchDatabase;
import controller.SingletonDatabaseAccess;
import model.User;

import java.awt.*;

public class RegisterGUI extends JFrame implements ActionListener, MouseListener {
    private String username;
    private String password;
    private JLabel generalMessage1;
    private JLabel generalMessage2;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel dobLabel;
    private JLabel fNameLabel;
    private JLabel lNameLabel;

    private JComboBox<String> userType;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField dobTextField;
    private JTextField fNameTextField;
    private JTextField lNameTextField;

    private JButton connectButton;
    private JButton guestButton;
    private JButton backButton;

    private JFrame parentFrame;
    public RegisterGUI(JFrame frame) {
        super("Connect to Server.");
        this.parentFrame=frame;
        setupGUI();
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Sets gui.
     */
    public void setupGUI() {
        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        generalMessage1 = new JLabel("Register to the University of Calgary");
        generalMessage2 = new JLabel("Property Management Software.");
        usernameLabel = new JLabel("Username      :");
        passwordLabel = new JLabel("Password      :");
        dobLabel = new JLabel("Date of Birth    :");
        fNameLabel = new JLabel("First Name    :");
        lNameLabel = new JLabel("Last Name    :");
        

        usernameTextField = new JTextField("User's email", 18);
        passwordTextField = new JTextField("User's password", 18);
        dobTextField = new JTextField("User's Date of Birth", 18);
        fNameTextField = new JTextField("User's First Name",18);
        lNameTextField = new JTextField("User's Last Name",18);
        
        String options[] = {"Renter","Landlord"};
        connectButton = new JButton("Register.");
        backButton = new JButton("Back");
        //add Mouse Listeners to the JTextFields and ActionListener to the JButton
        usernameTextField.addMouseListener(this);
        passwordTextField.addMouseListener(this);
        dobTextField.addMouseListener(this);
        fNameTextField.addMouseListener(this);
        lNameTextField.addMouseListener(this);
        
        userType = new JComboBox<String>(options);
        userType.setToolTipText("Select type of user.");

        connectButton.addActionListener(this);
        backButton.addActionListener(e -> {
            this.setVisible(false);
            parentFrame.setVisible(true);
            this.dispose();
        });
        //Create the JPanels.
        JPanel mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel usernamePanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JPanel dobPanel = new JPanel();
        JPanel fNamePanel = new JPanel();
        JPanel lNamePanel = new JPanel();
        JPanel guestPanel = new JPanel();
        JPanel connectPanel = new JPanel();

        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        usernamePanel.setLayout(new FlowLayout());
        passwordPanel.setLayout(new FlowLayout());
        dobPanel.setLayout(new FlowLayout());
        fNamePanel.setLayout(new FlowLayout());
        lNamePanel.setLayout(new FlowLayout());
        guestPanel.setLayout(new FlowLayout());
        connectPanel.setLayout(new FlowLayout());

        //Add Components to the JPanels.

        headerPanel.add(generalMessage1);
        headerPanel.add(generalMessage2);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);
        dobPanel.add(dobLabel);
        dobPanel.add(dobTextField);
        fNamePanel.add(fNameLabel);
        fNamePanel.add(fNameTextField);
        lNamePanel.add(lNameLabel);
        lNamePanel.add(lNameTextField);
        guestPanel.add(userType);
        connectPanel.add(connectButton);
        connectPanel.add(backButton);
        
        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(fNamePanel);
        mainContainer.add(lNamePanel);
        mainContainer.add(usernamePanel);
        mainContainer.add(passwordPanel);
        mainContainer.add(dobPanel);
        mainContainer.add(guestPanel);
        mainContainer.add(connectPanel);

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
       if(e.getSource().equals(connectButton)) {
    	username = usernameTextField.getText();
        password = passwordTextField.getText();
        String dob=dobTextField.getText();
        String lName=lNameTextField.getText();
        String fName=fNameTextField.getText();
        String type=userType.getSelectedItem().toString();
        User u1=new User(fName,lName,username,password,dob);
        SearchDatabase search = new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
        int id=search.maxUserID();
        search.addUser(type, u1, id+1);
        JOptionPane.showMessageDialog(null,"Congratulations you have just registered!" );

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
        if(event.getSource().equals(dobTextField)) {
        	dobTextField.setText("");
        }
        if(event.getSource().equals(fNameTextField)) {
        	fNameTextField.setText("");
        }
        if(event.getSource().equals(lNameTextField)) {
        	lNameTextField.setText("");
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
