package view;

import model.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Inbox extends JFrame implements ActionListener, MouseListener {

    private JLabel generalMessage1;
    private JTable inbox;
    
    private JButton back;
    private JButton reply;


    public Inbox() {
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
        generalMessage1 = new JLabel("Inbox");
        
 
        String data[][]= {{"John","jonh1@gmail.com","House1"},{"Bob","Bob2@gmail.com","House2"}};
        
        
        String[] columnNames = { "Name", "Email", "Message" };
        inbox=new JTable(data,columnNames);
        
        back = new JButton("Back");
        reply=new JButton("Reply");
        
        JPanel mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JScrollPane inboxPanel = new JScrollPane(inbox);
        headerPanel.add(generalMessage1);
        
        back.addActionListener(this);
        reply.addActionListener(this);
        
        buttonPanel.add(back);
        buttonPanel.add(reply);
        mainContainer.add(headerPanel);
        mainContainer.add(buttonPanel);
        mainContainer.add(inboxPanel);
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));

        
        //Add the main panel to the JFrame.
        this.add(mainContainer);
    }

    /**
     * actionPerformed function used to handle an action performed on the
     * Connect Button.
     * @param e ActionEvent passed on the function actionPerformed
     */
    public void actionPerformed(ActionEvent e) {
        //Attempt to create a databaseAccess object called database using the inputs provided by the user.
    	if(e.getSource().equals(back)) {
    		//Go back to calling GUI
         }
         if(e.getSource().equals(reply)) {
        	 
        	 Reply loginFrame = new Reply();
             EventQueue.invokeLater(() -> {
                 loginFrame.setVisible(true);
             });

         }
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
