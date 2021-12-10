package view;

import model.*;
import controller.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

import controller.SingletonDatabaseAccess;

import java.awt.*;

public class Inbox extends JFrame implements ActionListener, MouseListener {

    private JLabel generalMessage1;
    private JTable inbox;
    
    private JButton back;
    private JButton reply;
    private User myInfo;
    
    private ArrayList<InboxMessages> arr;
    
    public Inbox(User u1) {
        super("Connect to Server.");
        myInfo=u1;
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
        
        SingletonDatabaseAccess access=SingletonDatabaseAccess.getOnlyInstance();
        SearchDatabase search=new SearchDatabase(access.getDBConnect());
        
        arr=search.getAllInboxMessages("inbox",myInfo);//Gets all the messages belonging to specific reciever email
 
        
        String data[][]=this.getMyMessages(arr);
       // String data[][]= {{"John","jonh1@gmail.com","House1"},{"Bob","Bob2@gmail.com","House2"}};
        
        
        String[] columnNames = { "Sender", "Receiver", "Message" };
        inbox = new JTable(data,columnNames);
        
        back = new JButton("Back");
        reply = new JButton("Reply");
        
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
    		super.dispose();
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
   String[][] getMyMessages(ArrayList<InboxMessages> array){
	   String holder[][]=new String[array.size()][3];
	   for(int i=0;i<array.size();i++) {
		   //System.out.println(array.get(i).getRecieverEmail()+myInfo.getEmail()+"K");
		   
			   holder[i][0]=array.get(i).getSenderEmail();
			   holder[i][1]=array.get(i).getRecieverEmail();
			   holder[i][2]=array.get(i).getMessage();
		   
	   }
	   return holder;
   }
}
