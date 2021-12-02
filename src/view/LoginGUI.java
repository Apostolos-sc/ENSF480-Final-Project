package view;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import model.*;
import controller.*;
public class LoginGUI extends JFrame implements ActionListener, MouseListener {
    private String username;
    private String password;
    private JLabel generalMessage1;
    private JLabel generalMessage2;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel selectUserLabel;

    private JTextField usernameTextField;
    private JTextField passwordTextField;

    private JComboBox<String> selectUserComboBox;

    private JButton connectButton;
    private JButton guestButton;

    public LoginGUI(ArrayList<Renter> renters, ArrayList<Landlord> landlords, ArrayList<Manager> managers, ArrayList<Property> properties) {
        super("Connect to Server.");
        setupGUI();
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Sets gui.
     */
    public void setupGUI() {
        String[] typeOfUsers = { "Renter","Landlord", "Manager"};
        selectUserComboBox = new JComboBox<String>(typeOfUsers);
        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        generalMessage1 = new JLabel("Welcome to the University of Calgary");
        generalMessage2 = new JLabel("Property Management Software.");
        usernameLabel = new JLabel("Username      :");
        passwordLabel = new JLabel("Password      :");
        selectUserLabel = new JLabel("User Type      :");
        usernameTextField = new JTextField("User's username");
        passwordTextField = new JTextField("User's password");
        guestButton = new JButton("Continue as a Guest");
        connectButton = new JButton("Login.");
        //add Mouse Listeners to the JTextFields and ActionListener to the JButton
        usernameTextField.addMouseListener(this);
        passwordTextField.addMouseListener(this);
        connectButton.addActionListener(this);
        guestButton.addActionListener(this);
        //Create the JPanels.
        JPanel mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel usernamePanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JPanel selectUserPanel = new JPanel();
        JPanel guestPanel = new JPanel();
        JPanel connectPanel = new JPanel();

        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        usernamePanel.setLayout(new FlowLayout());
        passwordPanel.setLayout(new FlowLayout());
        selectUserPanel.setLayout(new FlowLayout());
        guestPanel.setLayout(new FlowLayout());
        connectPanel.setLayout(new FlowLayout());
        selectUserComboBox.setPreferredSize(new Dimension(175, 25));
        usernameTextField.setPreferredSize(new Dimension(175, 25));
        passwordTextField.setPreferredSize(new Dimension(175, 25));
        //Add Components to the JPanels.

        headerPanel.add(generalMessage1);
        headerPanel.add(generalMessage2);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);
        selectUserPanel.add(selectUserLabel);
        selectUserPanel.add(selectUserComboBox);
        guestPanel.add(guestButton);
        connectPanel.add(connectButton);

        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(usernamePanel);
        mainContainer.add(passwordPanel);
        mainContainer.add(selectUserPanel);
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
        username = usernameTextField.getText();
        password = passwordTextField.getText();
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
    /*
        Will check accordingly our users to see if the user inputted appropriate information
        The boolean return value indicates if the user was found or not.
     */
    public boolean checkUser(String email, String password, String userType) {
        return true;
    }
}
