package view;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import model.*;
import controller.*;
public class LoginGUI extends JFrame implements ActionListener, MouseListener {
    private Data data;
    private String username;
    private String password;
    private String userType;
    private JLabel generalMessage1;
    private JLabel generalMessage2;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel selectUserLabel;

    private JTextField usernameTextField;
    private JTextField passwordTextField;

    private JComboBox<String> selectUserTypeComboBox;

    private JButton connectButton;
    private JButton guestButton;
    private JButton signUpButton;
    
    public LoginGUI() {
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

        //Let's set up the J Components for the LoginGUI.
        generalMessage1 = new JLabel("Welcome to the University of Calgary");
        generalMessage2 = new JLabel("Property Management Software.");
        usernameLabel = new JLabel("Username      :");
        passwordLabel = new JLabel("Password      :");
        selectUserLabel = new JLabel("User Type      :");
        usernameTextField = new JTextField("");
        passwordTextField = new JTextField("");
        selectUserTypeComboBox = new JComboBox<String>(typeOfUsers);
        guestButton = new JButton("Continue as a Guest");
        connectButton = new JButton("Login.");
        signUpButton = new JButton("Sign Up");
        
        //add Mouse Listeners to the JTextFields and ActionListener to the JButton
        usernameTextField.addMouseListener(this);
        passwordTextField.addMouseListener(this);
        connectButton.addActionListener(this);
        guestButton.addActionListener(this);
        signUpButton.addActionListener(this);
        //Create the JPanels.
        JPanel mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel usernamePanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JPanel selectUserTypePanel = new JPanel();
        JPanel guestPanel = new JPanel();
        JPanel connectPanel = new JPanel();

        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        usernamePanel.setLayout(new FlowLayout());
        passwordPanel.setLayout(new FlowLayout());
        selectUserTypePanel.setLayout(new FlowLayout());
        guestPanel.setLayout(new FlowLayout());
        connectPanel.setLayout(new FlowLayout());
        selectUserTypeComboBox.setPreferredSize(new Dimension(175, 25));
        usernameTextField.setPreferredSize(new Dimension(175, 25));
        passwordTextField.setPreferredSize(new Dimension(175, 25));

        usernameTextField.setToolTipText("User's Email.");
        passwordTextField.setToolTipText("User's Password.");
        selectUserTypeComboBox.setToolTipText("Select type of user.");
        //Add Components to the JPanels.

        headerPanel.add(generalMessage1);
        headerPanel.add(generalMessage2);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);
        selectUserTypePanel.add(selectUserLabel);
        selectUserTypePanel.add(selectUserTypeComboBox);
        guestPanel.add(guestButton);
        connectPanel.add(connectButton);
        connectPanel.add(signUpButton);

        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(usernamePanel);
        mainContainer.add(passwordPanel);
        mainContainer.add(selectUserTypePanel);
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
        if(e.getSource().equals(connectButton)) {
            //Pull the data from the JTextFields username, password and url
        	SingletonDatabaseAccess dbConnection=SingletonDatabaseAccess.getOnlyInstance(); 
        	data = dbConnection.retrieveData();
        	
            username = usernameTextField.getText();
            password = passwordTextField.getText();
            userType = selectUserTypeComboBox.getSelectedItem().toString();
            //Attempt to login using the information given :
            username = "j.lance@gmail.com";
            password = "tested";
            userType = "Landlord";
            User checkedUser = checkUser(username, password, userType);
            if(checkedUser != null) {
                JOptionPane.showMessageDialog(null, "You successfully connected to the database with username : "
                        + username + " and password : "+ password+" and userType : " + userType);
                
                if(userType.equals("Renter")) {
                    this.setVisible(false);
                	RegisteredRenterGUI frame = new RegisteredRenterGUI((Renter)checkedUser, this);
                    EventQueue.invokeLater(() -> {
                        frame.setVisible(true);
                    });
                }
                else if(userType.equals("Landlord")) {
                    this.setVisible(false);
                    System.out.println("# of contracts: " + data.getContracts().size());
                	LandlordGUI frame = new LandlordGUI((Landlord)checkedUser, this, data);
                    EventQueue.invokeLater(() -> {
                        frame.setVisible(true);
                    });
                }
                else{
                    this.setVisible(false);
                	ManagerGUI frame = new ManagerGUI((Manager)checkedUser, this, data);
                    EventQueue.invokeLater(() -> {
                        frame.setVisible(true);
                    });

                }
            } else {
                JOptionPane.showMessageDialog(null, "There was an error while connecting to the database with username : "
                        + username + " and password : "+ password+" and userType : " + userType);
            }
        }
        if(e.getSource().equals(guestButton)) {
        	this.setVisible(false);
        	UnregisteredRenterGUI frame = new UnregisteredRenterGUI(this);
            EventQueue.invokeLater(() -> {
                frame.setVisible(true);
            });
        }
        if(e.getSource().equals(signUpButton)) {
        	this.setVisible(false);
        	RegisterGUI frame = new RegisterGUI(this);
            EventQueue.invokeLater(() -> {
                frame.setVisible(true);
            });
        }
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
    public User checkUser(String email, String password, String userType) {
        if(userType.equals("Renter")) {
            for(int i = 0; i < data.getRenters().size(); i++) {
                if(data.getRenters().get(i).getEmail().equals(email)) {
                    if(data.getRenters().get(i).getPassword().equals(password)) {
                        return data.getRenters().get(i);
                    } else {
                        return null;
                    }
                }
            }
            return null;
        }else if(userType.equals("Landlord")) {
            for(int i = 0; i < data.getLandlords().size(); i++) {
                if(data.getLandlords().get(i).getEmail().equals(email)) {
                    if(data.getLandlords().get(i).getPassword().equals(password)) {
                        return data.getLandlords().get(i);
                    } else {
                        return null;
                    }
                }
            }
            return null;
        } else  {
            for(int i = 0; i < data.getManagers().size(); i++) {
                if(data.getManagers().get(i).getEmail().equals(email)) {
                    if(data.getManagers().get(i).getPassword().equals(password)) {
                        return data.getManagers().get(i);
                    } else {
                        return null;
                    }
                }
            }
            return null;
        }
    }
}
