package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PropertyViewGUI extends JFrame implements ActionListener, MouseListener {

    generalMessage1 = new JLabel("Welcome to the University of Calgary");
    generalMessage2 = new JLabel("Property Management Software.");
    usernameLabel = new JLabel("Username      :");
    passwordLabel = new JLabel("Password      :");

    usernameTextField = new JTextField("User's username", 18);
    passwordTextField = new JTextField("User's password", 18);
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
    JPanel guestPanel = new JPanel();
    JPanel connectPanel = new JPanel();

    //Set the Layouts for the JPanels
    mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
    headerPanel.setLayout(new FlowLayout());
    usernamePanel.setLayout(new FlowLayout());
    passwordPanel.setLayout(new FlowLayout());
    guestPanel.setLayout(new FlowLayout());
    connectPanel.setLayout(new FlowLayout());

    //Add Components to the JPanels.

    headerPanel.add(generalMessage1);
    headerPanel.add(generalMessage2);
    usernamePanel.add(usernameLabel);
    usernamePanel.add(usernameTextField);
    passwordPanel.add(passwordLabel);
    passwordPanel.add(passwordTextField);
    guestPanel.add(guestButton);
    connectPanel.add(connectButton);

    public void actionPerformed(ActionEvent e) {
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
