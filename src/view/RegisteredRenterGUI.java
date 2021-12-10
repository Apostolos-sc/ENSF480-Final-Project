package view;

import model.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import controller.SearchDatabase;
import controller.SingletonDatabaseAccess;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class RegisteredRenterGUI extends JFrame implements ActionListener, MouseListener {
    private String subDescription;
    private JLabel generalMessage1;
    private JLabel generalMessage2;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JTextField usernameTextField;
    private JTextField passwordTextField;

    private JToggleButton subscribeMenuButton;
    private JButton viewSubscribedButton;
    private JButton viewPropertyButton;
    private JButton payButton;
    private JButton messageButton;
    private JButton inboxButton;
    private JButton logoutButton;
    private JButton backButton;
    
    private Renter renter;
    private Frame parentFrame;
    
    JPanel mainContainer;


    public RegisteredRenterGUI(Renter renter, Frame parentFrame) {
        super("Registered Renter System. Logged in as "+ renter.getFirstName() + " " + renter.getLastName() + ".");
        this.renter = renter;
        this.parentFrame = parentFrame;
        setupGUI();
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Sets gui.
     */
    public void setupGUI() {
        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        generalMessage1 = new JLabel("Property Management Software.");
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
        if(renter.isSubscribed()) {
           subDescription="Subscribed";
        }
        else {
            subDescription="Unsubscribed";
        }
        subscribeMenuButton = new JToggleButton(subDescription);
        viewSubscribedButton= new JButton("View Suggested Properties");
        viewPropertyButton = new JButton("View Property");
        payButton = new JButton("Pay");
        messageButton = new JButton("Email Message");
        inboxButton = new JButton("Inbox");
        logoutButton = new JButton("Logout");
        
        viewSubscribedButton.addActionListener(this);
        subscribeMenuButton.addActionListener(this);
        viewPropertyButton.addActionListener(this);
        payButton.addActionListener(this);
        messageButton.addActionListener(this);
        inboxButton.addActionListener(this);
        logoutButton.addActionListener(e -> {
            this.setVisible(false);
            parentFrame.setVisible(true);
            this.dispose();
        });
        //Create the JPanels.
        mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel subscribePanel = new JPanel();
        JPanel viewPropertyPanel = new JPanel();
        JPanel payPanel = new JPanel();
        JPanel messagePanel = new JPanel();
        JPanel inboxPanel = new JPanel();
        JPanel logoutPanel = new JPanel();
        
        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        subscribePanel.setLayout(new FlowLayout());
        viewPropertyPanel.setLayout(new FlowLayout());
        payPanel.setLayout(new FlowLayout());
        messagePanel.setLayout(new FlowLayout());
        inboxPanel.setLayout(new FlowLayout());
        logoutPanel.setLayout(new FlowLayout());
        //Add Components to the JPanels.

        headerPanel.add(generalMessage1);
        headerPanel.add(generalMessage2);
        subscribePanel.add(subscribeMenuButton);
        subscribePanel.add(viewSubscribedButton);
        viewPropertyPanel.add(viewPropertyButton);
        payPanel.add(payButton);
        messagePanel.add(messageButton);
        inboxPanel.add(inboxButton);
        logoutPanel.add(logoutButton);
        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(subscribePanel);
        mainContainer.add(viewPropertyPanel);
        mainContainer.add(payPanel);
        mainContainer.add(messagePanel);
        mainContainer.add(inboxPanel);
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
        if(e.getSource().equals(subscribeMenuButton)) {
        	if(subDescription=="Unsubscribed") {
        		subDescription="Subscribed";
        		subscribeMenuButton.setText(subDescription);
        		renter.setSubscribed(true);
        		
        		SearchDatabase search = new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
        		
        		search.updateRenter(renter);
        	}
        	else {
        		System.out.println("Test");

        		subDescription="Unsubscribed";
        		subscribeMenuButton.setText(subDescription);
        		renter.setSubscribed(false);

        		SearchDatabase search = new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
        		
        		search.updateRenter(renter);
        	}
        	
        }
        if(e.getSource().equals(messageButton)) {
        	 Reply loginFrame = new Reply();
             EventQueue.invokeLater(() -> {
                 loginFrame.setVisible(true);
             });

        }
        if(e.getSource().equals(payButton)) {
        	Desktop desk = Desktop.getDesktop();
            
            // now we enter our URL that we want to open in our
            // default browser
            try {
				desk.browse(new URI("https://www.paypal.com/ca/home"));
			} catch (IOException | URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        if(e.getSource().equals(viewPropertyButton)) {
        	/*
        	 *NEED TO RETRIEVE DATA FROM DATABASE AND CALL PROPERTYVIEWGUI this is just an example*
        	 
        	Property p1= new Property(1, "Apartment", 3, 2, true, "123 University Rd", "NW","Available");
        	User u1= new User("John","Bob", "JohnBob123@gmail.com","password", "June 5,1990");
        	PropertyViewGUI loginFrame = new PropertyViewGUI(p1,u1);
            EventQueue.invokeLater(() -> {
                loginFrame.setVisible(true);
            });
            */
        	
       	RegisteredRenterSearch loginFrame = new RegisteredRenterSearch(this,renter);
           EventQueue.invokeLater(() -> {
               loginFrame.setVisible(true);
           });
        }
        if(e.getSource().equals(logoutButton)){
            SearchDatabase search = new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
            search.checkPropertyPeriod();
        }
        if(e.getSource().equals(inboxButton)) {
        	Inbox loginFrame = new Inbox(renter);
            EventQueue.invokeLater(() -> {
                loginFrame.setVisible(true);
            });
        }
        //Attempt to create a databaseAccess object called database using the inputs provided by the user.
   	if(e.getSource().equals(viewSubscribedButton)) {
        	
//        	SingletonDatabaseAccess access=SingletonDatabaseAccess.getOnlyInstance();
//        	SearchDatabase search=new SearchDatabase(access.getDBConnect());
//            ArrayList<Property> arr=search.getAllProperties("property");
//            
//            showProperties(arr);
        	
        	SearchDatabase search = new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
        	ArrayList<Property> array =search.getNotifiedProperty(renter);
        	
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
    public void showTable(String[][] tableInfo, String[] columns, String tableHeader) {
        TableModel model = new DefaultTableModel(tableInfo,columns)
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
        JTable table = new JTable(model);
        table.setBackground(new Color(191, 191, 191));
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(128, 128, 128));
        JScrollPane pane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        mainContainer.removeAll();
      
        this.backButton=new JButton("Back");

        JPanel headerPanel = new JPanel();
        JPanel tablePanel = new JPanel();
        JPanel backPanel = new JPanel();
        
        headerPanel.setLayout(new FlowLayout());
        tablePanel.setLayout(new GridLayout(1,1)); /* little trick ;) and believe me that this step is important to the automatic all columns resize! A import is also needed for using GridLayout*/
        tablePanel.add(pane);
        backPanel.setLayout(new FlowLayout());
        
        backButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(backButton)) {

//                        mainContainer.removeAll();
//                        revalidate();
//                        repaint();
                    
                        setupGUI();
                }
            }
        }));
        backPanel.add(backButton);
        mainContainer.add(headerPanel);
        mainContainer.add(tablePanel);
        mainContainer.add(backPanel);
        this.revalidate();
        this.repaint();
    }
    
    public void showProperties(ArrayList<Property>arr) {
        String[][] tableInfo = new String[arr.size()][8];
        for(int i = 0; i < arr.size(); i++) {
        	
            tableInfo[i][0] = ""+arr.get(i).getPropertyID();
            tableInfo[i][1] = arr.get(i).getPropertyDetails().getPropertyType();
            tableInfo[i][2] = arr.get(i).getPropertyLocation().getAddress();
            tableInfo[i][3] = ""+arr.get(i).getPropertyDetails().getNoBedrooms();
            tableInfo[i][4] = ""+arr.get(i).getPropertyDetails().getNoBathrooms();
            tableInfo[i][5] = arr.get(i).getPropertyLocation().getQuadrant();
            tableInfo[i][6] = (arr.get(i).getPropertyDetails().isFurnished() ? "Yes" : "No");
            tableInfo[i][7] = arr.get(i).getStatus();
        }
        String columns[] = {"ID","Type","Address","NoBedrooms", "NoBathrooms","Quadrant","Furnished", "Status"};
        showTable(tableInfo,columns , "Viewing Properties :");
    }
}
