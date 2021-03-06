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

import model.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import controller.Data;
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
    private JCheckBoxMenuItem subscribeMenuButton;   
    
    private JMenuItem viewSubscribedButton;
    private JMenuItem viewPropertyButton;
    private JMenuItem payButton;
    private JMenuItem messageButton;
    private JMenuItem inboxButton;
    private JMenuItem logoutButton;
    private JMenuItem editContract;
    private JMenuItem viewMyContracts;
    private JMenuBar menuBar;
    private JMenu propertyMenu;
    private JMenu profileMenu;
    private JMenu contractMenu;

    private Renter renter;
    private Data data;
    private Contract contract;
    private Frame parentFrame;
    
    JPanel mainContainer;
   

    public RegisteredRenterGUI(Renter renter, Frame parentFrame,Data d) {
        super("Registered Renter System. Logged in as "+ renter.getFirstName() + " " + renter.getLastName() + ".");
        this.renter = renter;
        this.parentFrame = parentFrame;
        this.data=d;
        setupGUI();
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Sets gui.
     */
    public void setupGUI() {
        setupMenu();
        mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        this.add(mainContainer);
    }
   
 
    /**
     * actionPerformed function used to handle an action performed on the
     * Connect Button.
     * @param e ActionEvent passed on the function actionPerformed
     */
    public void actionPerformed(ActionEvent e) {
        //Pull the data from the JTextFields username, password and url
        if(e.getSource().equals(logoutButton)) {
                this.setVisible(false);
                parentFrame.setVisible(true);
                this.dispose();
        }
    	
    	if(e.getSource().equals(viewMyContracts)) {
            showMyContracts();
        }

        if(e.getSource().equals(editContract)) {
            showEditContract();
        }
       // if(e.getSource().equals(subscribeMenuButton)) {
        	if(subscribeMenuButton.isSelected()) {
        		subDescription="Subscribed";
        		subscribeMenuButton.setText(subDescription);
        		renter.setSubscribed(true);
        		
        		SearchDatabase search = new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
        		
        		search.updateRenter(renter);
        	}
        	if(subscribeMenuButton.isSelected()==false) {
        		System.out.println("Test");

        		subDescription="Unsubscribed";
        		subscribeMenuButton.setText(subDescription);
        		renter.setSubscribed(false);

        		SearchDatabase search = new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
        		
        		search.updateRenter(renter);
        	}
        	
       // }
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
    public void setupMenu() {
    	menuBar = new JMenuBar();
        propertyMenu = new JMenu("Property");
        profileMenu = new JMenu("User Actions");
        contractMenu = new JMenu("Contract");
        
        viewSubscribedButton= new JMenuItem("View Suggested Properties");
        viewPropertyButton = new JMenuItem("View Property");
        payButton = new JMenuItem("Pay");
        messageButton = new JMenuItem("Email Message");
        inboxButton = new JMenuItem("Inbox");
        logoutButton = new JMenuItem("Logout");
        editContract = new JMenuItem("Edit Contract");
        viewMyContracts = new JMenuItem("View Contract");
        
        boolean sub=false;
        if(renter.isSubscribed()) {
        	sub=true;
        }
        subscribeMenuButton = new JCheckBoxMenuItem("Subscribe",sub);
        
        viewSubscribedButton.addActionListener(this);
        subscribeMenuButton.addActionListener(this);
        
        viewPropertyButton.addActionListener(this);
        payButton.addActionListener(this);
        messageButton.addActionListener(this);
        inboxButton.addActionListener(this);
        editContract.addActionListener(this);;
        viewMyContracts.addActionListener(this);
        logoutButton.addActionListener(this);

        
        propertyMenu.add(subscribeMenuButton);
        propertyMenu.add(viewSubscribedButton);
        propertyMenu.add(viewPropertyButton);
        propertyMenu.add(payButton);
        propertyMenu.add(viewPropertyButton);  

        profileMenu.add(messageButton);
        profileMenu.add(inboxButton);
        profileMenu.add(logoutButton);
        
        contractMenu.add(editContract);
        contractMenu.add(viewMyContracts);
        
        menuBar.add(propertyMenu);
        menuBar.add(contractMenu);
        menuBar.add(profileMenu);
        
        this.add(menuBar);
        this.setJMenuBar(menuBar);
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
      
       // this.backButton=new JButton("Back");

        JPanel headerPanel = new JPanel();
        JPanel tablePanel = new JPanel();
        JPanel backPanel = new JPanel();
        
        headerPanel.setLayout(new FlowLayout());
        tablePanel.setLayout(new GridLayout(1,1)); /* little trick ;) and believe me that this step is important to the automatic all columns resize! A import is also needed for using GridLayout*/
        tablePanel.add(pane);
        backPanel.setLayout(new FlowLayout());
        
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
    
    public void showMyContracts() {
        ArrayList<Contract> renterContracts = new ArrayList<Contract>();
        for (int i = 0; i < data.getContracts().size(); i++) {
            if(data.getContracts().get(i).getRenter().getRenterID() == renter.getRenterID()) {
                renterContracts.add(data.getContracts().get(i));
            }
        }
        String[][] tableInfo = new String[renterContracts.size()][7];
        for(int i = 0; i < renterContracts.size(); i++) {
            tableInfo[i][0] = ""+renterContracts.get(i).getContractID();
            tableInfo[i][1] = renterContracts.get(i).getRenter().getFirstName() + " " + renterContracts.get(i).getRenter().getLastName();
            tableInfo[i][2] = ""+renterContracts.get(i).getProperty().getPropertyID();
            tableInfo[i][3] = renterContracts.get(i).getStartDate();
            tableInfo[i][4] = renterContracts.get(i).getEndDate();
            tableInfo[i][5] = ""+renterContracts.get(i).getMonthlyRent();
            tableInfo[i][6] = renterContracts.get(i).getContractStatus();

        }
        String columns[] = {"Contract ID", "Renter","Property","Start Date", "End Date","Monthly Rent", "Contract Status"};
        showTable(tableInfo,columns , "Viewing my Contracts :");
    }

    public void showEditContract() {
        String[] contractStatusOptions = {"Accepted", "Declined", "Pending"};
        mainContainer.removeAll();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        ArrayList<Contract> renterContracts = new ArrayList<Contract>();
        for (int i = 0; i < data.getContracts().size(); i++) {
            if(data.getContracts().get(i).getRenter().getRenterID() == renter.getRenterID() &&
                    !data.getContracts().get(i).getContractStatus().equals("Signed")) {
            	renterContracts.add(data.getContracts().get(i));
            }
        }
        String contractList[] = new String[renterContracts.size()];
        for(int i = 0; i < renterContracts.size(); i++) {
            contractList[i] = "Contract ID: " +  renterContracts.get(i).getContractID() + ", Property ID: " + renterContracts.get(i).getProperty().getPropertyID();
        }
        JPanel headerPanel = new JPanel();
        JPanel selectPanel = new JPanel();
        JPanel editPanel = new JPanel();

        JLabel generalMessage = new JLabel("Edit Contract :");
        JLabel SelectLabel = new JLabel("Select Contract :");

        JComboBox selectComboBox = new JComboBox(contractList);

        generalMessage.setPreferredSize(new Dimension(175,25));
        SelectLabel.setPreferredSize(new Dimension(175, 25));
        selectComboBox.setPreferredSize(new Dimension(175, 25));

        headerPanel.setLayout(new FlowLayout());
        selectPanel.setLayout(new FlowLayout());
        //editPropertyPanel.setLayout(new FlowLayout());
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.PAGE_AXIS));
        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        JLabel contractIDMessage = new JLabel("Contract ID: ");
        JLabel landlordIDMessage = new JLabel("Landlord ID: ");
        JLabel renterIDMessage = new JLabel("Renter ID: ");
        JLabel contractIDMessage2 = new JLabel();
        JLabel landlordIDMessage2 = new JLabel();
        JLabel renterIDMessage2 = new JLabel();
        JLabel propertyIDMessage2 = new JLabel();
        JLabel propertyIDMessage = new JLabel("Property ID: ");
        JLabel startDateMessage = new JLabel("Start Date: ");
        JLabel endDateMessage = new JLabel("End Date: ");
        JLabel monthlyRentMessage = new JLabel("Monthly Rent: ");
        JLabel startDateMessage2 = new JLabel("Start Date: ");
        JLabel endDateMessage2 = new JLabel("End Date: ");
        JLabel monthlyRentMessage2 = new JLabel("Monthly Rent: ");
        JLabel contractStatusMessage = new JLabel("Contract Status: ");


        contractIDMessage.setPreferredSize(new Dimension(175, 25));
        landlordIDMessage.setPreferredSize(new Dimension(175, 25));
        renterIDMessage.setPreferredSize(new Dimension(175, 25));
        contractIDMessage2.setPreferredSize(new Dimension(175, 25));
        landlordIDMessage2.setPreferredSize(new Dimension(175, 25));
        renterIDMessage2.setPreferredSize(new Dimension(175,25));
        propertyIDMessage.setPreferredSize(new Dimension(175, 25));
        propertyIDMessage2.setPreferredSize(new Dimension(175, 25));
        startDateMessage.setPreferredSize(new Dimension(175, 25));
        endDateMessage.setPreferredSize(new Dimension(175, 25));
        monthlyRentMessage.setPreferredSize(new Dimension(175, 25));
        startDateMessage2.setPreferredSize(new Dimension(175, 25));
        endDateMessage2.setPreferredSize(new Dimension(175, 25));
        monthlyRentMessage2.setPreferredSize(new Dimension(175, 25));
        contractStatusMessage.setPreferredSize(new Dimension(175,25));

        JTextField startDateTextField = new JTextField("Start Date");
        JTextField endDateTextField = new JTextField("End Date");
        JTextField monthlyRentTextField = new JTextField("Monthly Rent");
        JComboBox contractStatusComboField = new JComboBox<String>(contractStatusOptions);

        startDateTextField.setToolTipText("Set Start Date to ..");
        endDateTextField.setToolTipText("Set End Date to ..");
        monthlyRentTextField.setToolTipText("Set Monthly Rent to...");
        contractStatusComboField.setToolTipText("Set Contract Status to ..");

        startDateTextField.setPreferredSize(new Dimension(175, 25));
        endDateTextField.setPreferredSize(new Dimension(175, 25));
        monthlyRentTextField.setPreferredSize(new Dimension(175, 25));

        contractStatusComboField.setPreferredSize(new Dimension(175, 25));

        JButton updateButton = new JButton("Update Contract");

        updateButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(updateButton)) {

                    if (contractStatusComboField.getSelectedItem().toString() == "Accepted"
                            || contractStatusComboField.getSelectedItem().toString() == "Declined") {


                        String contractStatus = contractStatusComboField.getSelectedItem().toString();

                        contract.setContractStatus(contractStatus);
                        SearchDatabase search=new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
                        search.updateContract(contract);

                        //implement DB Action here.
                        JOptionPane.showMessageDialog(null, "Contract was updated.");
                        data.setContracts(SingletonDatabaseAccess.getOnlyInstance().retrieveContracts(data.getProperties(), data.getRenters(), data.getLandlords()));
                        mainContainer.removeAll();
                        revalidate();
                        repaint();

                    } else {
                        JOptionPane.showMessageDialog(null, "There was an error with your input. Please re-enter info.");
                    }
                }
            }
        }));



        //Create the JPanels.
        JPanel contradIDPanel = new JPanel();
        JPanel landlordIDPanel = new JPanel();
        JPanel renterIDPanel = new JPanel();
        JPanel propertyIDPanel = new JPanel();
        JPanel startDatePanel = new JPanel();
        JPanel endDatePanel = new JPanel();
        JPanel monthlyRentPanel = new JPanel();
        JPanel contractStatusPanel = new JPanel();
        JPanel registerPanel = new JPanel();

        //Set the Layouts for the JPanels
        contradIDPanel.setLayout(new FlowLayout());
        landlordIDPanel.setLayout(new FlowLayout());
        renterIDPanel.setLayout(new FlowLayout());
        propertyIDPanel.setLayout(new FlowLayout());
        startDatePanel.setLayout(new FlowLayout());
        endDatePanel.setLayout(new FlowLayout());
        monthlyRentPanel.setLayout(new FlowLayout());
        contractStatusPanel.setLayout(new FlowLayout());
        registerPanel.setLayout(new FlowLayout());

        //Add Components to the JPanels.

        contradIDPanel.add(contractIDMessage);
        contradIDPanel.add(contractIDMessage2);
        landlordIDPanel.add(landlordIDMessage);
        landlordIDPanel.add(landlordIDMessage2);
        renterIDPanel.add(renterIDMessage);
        renterIDPanel.add(renterIDMessage2);
        propertyIDPanel.add(propertyIDMessage);
        propertyIDPanel.add(propertyIDMessage2);
        startDatePanel.add(startDateMessage);
        startDatePanel.add(startDateMessage2);
        endDatePanel.add(endDateMessage);
        endDatePanel.add(endDateMessage2);
        monthlyRentPanel.add(monthlyRentMessage);
        monthlyRentPanel.add(monthlyRentMessage2);
        contractStatusPanel.add(contractStatusMessage);
        contractStatusPanel.add(contractStatusComboField);
        registerPanel.add(updateButton);

        //Add the JPanels to the main JPanel
        editPanel.add(contradIDPanel);
        editPanel.add(landlordIDPanel);
        editPanel.add(renterIDPanel);
        editPanel.add(propertyIDPanel);
        editPanel.add(startDatePanel);
        editPanel.add(endDatePanel);
        editPanel.add(monthlyRentPanel);
        editPanel.add(contractStatusPanel);
        editPanel.add(registerPanel);


        selectComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selection = selectComboBox.getSelectedItem().toString();
                int contractID = Integer.valueOf(selection.substring(13, 14));
                for(int i = 0; i < renterContracts.size(); i++) {
                    if(contractID == renterContracts.get(i).getContractID()) {
                        contract = renterContracts.get(i);
                        contractIDMessage2.setText(""+renterContracts.get(i).getContractID());
                        landlordIDMessage2.setText(""+renterContracts.get(i).getLandlord().getLandlordID());
                        renterIDMessage2.setText(""+renterContracts.get(i).getRenter().getRenterID());
                        propertyIDMessage2.setText(""+renterContracts.get(i).getProperty().getPropertyID());
                        startDateTextField.setText(""+renterContracts.get(i).getStartDate());
                        endDateTextField.setText(""+renterContracts.get(i).getEndDate());
                        monthlyRentTextField.setText(""+renterContracts.get(i).getMonthlyRent());
                        contractStatusComboField.setSelectedItem(renterContracts.get(i).getContractStatus());
                        mainContainer.add(editPanel);
                        revalidate();
                        repaint();
                        break;
                    }
                }
            }
        });

        headerPanel.add(generalMessage);
        selectPanel.add(headerPanel);
        selectPanel.add(selectComboBox);

        mainContainer.add(headerPanel);
        mainContainer.add(selectPanel);

        revalidate();
        repaint();

    }
    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
