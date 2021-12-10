package view;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import model.*;
import controller.*;



public class ManagerGUI extends JFrame implements ActionListener, MouseListener {

    private JLabel headerLabel;
    private JPanel mainContainer;
    private JMenuBar menuBar;
    private JMenu edit;
    private JMenu view;
    private JMenu report;
    private JMenu settings;
    private JMenuItem editLandlord;
    private JMenuItem editProperty;
    private JMenuItem editRenter;
    private JMenuItem viewLandlord;
    private JMenuItem viewProperty;
    private JMenuItem viewRenter;
    private JMenuItem showReport;
    private JMenuItem logoutOption;
    private JButton editPropertyButton;
    private JButton editLandlordButton;
    private JButton editRenterButton;
    private JButton viewPropertyButton;
    private JButton viewLandlordButton;
    private JButton viewRenterButton;
    private JButton logoutButton;
    private JButton showReportButton;
    private JFrame parentFrame;
    private Manager mgr;
    private Data data;

    private double[] value;
    private String[] information;
    private String title;

    private int propertyID;
    private Property prop;
    private Landlord land;
    private Renter renter;
    
    public ManagerGUI(Manager mgr, JFrame parentFrame, Data data) {
        super("Manager System. Logged in as " + mgr.getFirstName() + " "+ mgr.getLastName() + ".");
        this.mgr= mgr;
        this.parentFrame = parentFrame;
        this.data = data;
        setupGUI();
        setSize(1800,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Sets gui.
     */
    public void setupGUI() {
        
    	String options[]= {"Edit","View"};
    	//Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        headerLabel = new JLabel();

        //create menu
        setupMenu();
        /*
        editPropertyButton = new JButton("Edit Property");
        editLandlordButton = new JButton("Edit Landlord");
        editRenterButton = new JButton("Edit Renter");
        
        viewPropertyButton = new JButton("View Property");
        viewLandlordButton = new JButton("View Landlord");
        viewRenterButton = new JButton("View Renter");
        
        logoutButton = new JButton("Logout");
        editPropertyButton.addActionListener(this);
        editLandlordButton.addActionListener(this);
        editRenterButton.addActionListener(this);
        
        viewPropertyButton.addActionListener(this);
        viewLandlordButton.addActionListener(this);
        viewRenterButton.addActionListener(this);
        logoutButton.addActionListener(e -> {
            this.setVisible(false);
            parentFrame.setVisible(true);
            this.dispose();
        });*/
        //Create the JPanels.
        mainContainer = new JPanel();
        /*JPanel headerPanel = new JPanel();
        JPanel editPanel = new JPanel();
        JPanel viewPanel = new JPanel();
        JPanel backPanel = new JPanel();
        */
        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        /*headerPanel.setLayout(new FlowLayout());
        
        editPanel.setLayout(new FlowLayout());
        viewPanel.setLayout(new FlowLayout());
        backPanel.setLayout(new FlowLayout());*/
        //Add Components to the JPanels.
/*
        headerPanel.add(generalMessage1);
        //headerPanel.add(generalMessage2);
        editPanel.add(editRenterButton);
        editPanel.add(editLandlordButton);
        editPanel.add(editPropertyButton);
        
        viewPanel.add(viewRenterButton);
        viewPanel.add(viewLandlordButton);
        viewPanel.add(viewPropertyButton);
        
        backPanel.add(logoutButton);
        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(editPanel);
        mainContainer.add(viewPanel);
        mainContainer.add(backPanel);*/
        //Add the main panel to the JFrame.
        this.add(mainContainer);
    }

    public void setupMenu() {
        //Initialize MenuBar,Menus and Menu Items.
        menuBar = new JMenuBar();
        view = new JMenu("View");
        edit = new JMenu("Edit");
        report = new JMenu("Report");
        settings = new JMenu("Options");

        viewLandlord = new JMenuItem("Landlord");
        viewProperty = new JMenuItem("Property");
        viewRenter = new JMenuItem("Renter");

        editLandlord = new JMenuItem("Landlord");
        editProperty = new JMenuItem("Property");
        editRenter = new JMenuItem("Renter");

        showReport = new JMenuItem("Show Report");
        
        logoutOption = new JMenuItem("Logout");
        logoutOption.addActionListener(this);
        viewProperty.addActionListener(this);
        viewLandlord.addActionListener(this);
        viewRenter.addActionListener(this);
        editProperty.addActionListener(this);
        editRenter.addActionListener(this);
        editLandlord.addActionListener(this);
        showReport.addActionListener(this);
        
        view.add(viewLandlord);
        view.add(viewProperty);
        view.add(viewRenter);

        edit.add(editLandlord);
        edit.add(editProperty);
        edit.add(editRenter);

        report.add(showReport);
        
        settings.add(logoutOption);

        menuBar.add(view);
        menuBar.add(edit);
        menuBar.add(report);
        menuBar.add(settings);

        this.add(menuBar);
        this.setJMenuBar(menuBar);
    }
    /**
     * actionPerformed function used to handle an action performed on the
     * Connect Button.
     * @param e ActionEvent passed on the function actionPerformed
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(logoutOption)) {
            this.setVisible(false);
            parentFrame.setVisible(true);
            this.dispose();
        }
    	if(e.getSource().equals(viewProperty)) {
            showProperties();
	    }
        if(e.getSource().equals(viewLandlord)) {
            showLandlords();
        }
	    if(e.getSource().equals(viewRenter)) {
	    	showRenters();
	    }
	    if(e.getSource().equals(editProperty)) {
	    	showEditProperty();
	    }
	    if(e.getSource().equals(editLandlord)) {
	    	showEditProfile();
	    }
	    if(e.getSource().equals(editRenter)) {
	    	showEditRenter();
	    }
	    if(e.getSource().equals(showReport)) {
	    	showReport();
	    }
	    if(e.getSource().equals(viewLandlordButton)) {
	    	
	    }
	    if(e.getSource().equals(viewPropertyButton)) {
	    	
//	    	PropertyViewGUI loginFrame = new PropertyViewGUI(Property prop, User user);
//            EventQueue.invokeLater(() -> {
//                loginFrame.setVisible(true);
//            });
	    }
    }

    public void mouseClicked(MouseEvent event) {

//        if(event.getSource().equals(usernameTextField)) {
//            usernameTextField.setText("");
//        }
//
//        if(event.getSource().equals(passwordTextField)) {
//            passwordTextField.setText("");
//        }
    }

    public void mouseExited(MouseEvent event) {

    }

    public void mousePressed(MouseEvent event) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent event) {

    }

    public void showProperties() {
        String[][] tableInfo = new String[data.getProperties().size()][8];
        for(int i = 0; i < data.getProperties().size(); i++) {
            tableInfo[i][0] = ""+data.getProperties().get(i).getPropertyID();
            tableInfo[i][1] = data.getProperties().get(i).getPropertyDetails().getPropertyType();
            tableInfo[i][2] = data.getProperties().get(i).getPropertyLocation().getAddress();
            tableInfo[i][3] = ""+data.getProperties().get(i).getPropertyDetails().getNoBedrooms();
            tableInfo[i][4] = ""+data.getProperties().get(i).getPropertyDetails().getNoBathrooms();
            tableInfo[i][5] = data.getProperties().get(i).getPropertyLocation().getQuadrant();
            tableInfo[i][6] = (data.getProperties().get(i).getPropertyDetails().isFurnished() ? "Yes" : "No");
            tableInfo[i][7] = data.getProperties().get(i).getStatus();
        }
        String columns[] = {"ID","Type","Address","NoBedrooms", "NoBathrooms","Quadrant","Furnished", "Status"};
        showTable(tableInfo,columns , "Viewing Properties :");
    }

    public void showLandlords() {
        String [][] tableInfo = new String[data.getLandlords().size()][5];
        for(int i = 0; i < data.getLandlords().size(); i++) {
            tableInfo[i][0] = ""+data.getLandlords().get(i).getLandlordID();
            tableInfo[i][1] = data.getLandlords().get(i).getFirstName();
            tableInfo[i][2] = data.getLandlords().get(i).getLastName();
            tableInfo[i][3] = data.getLandlords().get(i).getEmail();
            tableInfo[i][4] = data.getLandlords().get(i).getDob();
        }
        String columns[] = {"ID", "First Name", "Last Name", "Email", "DOB"};
        showTable(tableInfo, columns, "Viewing Landlords: ");
    }

    public void showRenters() {
        String [][] tableInfo = new String[data.getRenters().size()][5];
        for(int i = 0; i < data.getRenters().size(); i++) {
            tableInfo[i][0] = ""+data.getRenters().get(i).getRenterID();
            tableInfo[i][1] = data.getRenters().get(i).getFirstName();
            tableInfo[i][2] = data.getRenters().get(i).getLastName();
            tableInfo[i][3] = data.getRenters().get(i).getEmail();
            tableInfo[i][4] = data.getRenters().get(i).getDob();
        }
        String columns[] = {"ID", "First Name", "Last Name", "Email", "DOB"};
        showTable(tableInfo, columns, "Viewing Renters: ");
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
        JPanel headerPanel = new JPanel();
        JPanel tablePanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        tablePanel.setLayout(new GridLayout(1,1)); /* little trick ;) and believe me that this step is important to the automatic all columns resize! A import is also needed for using GridLayout*/
        tablePanel.add(pane);
        headerLabel.setText(tableHeader);
        headerPanel.add(headerLabel);
        mainContainer.add(headerPanel);
        mainContainer.add(tablePanel);
        this.revalidate();
        this.repaint();
    }
    
    public void showEditProperty() {
        mainContainer.removeAll();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        String propertyList[] = new String[data.getProperties().size()];
        for(int i = 0; i < data.getProperties().size(); i++) {
            propertyList[i] = data.getProperties().get(i).getPropertyID() + " - " + data.getProperties().get(i).getPropertyLocation().getAddress();
        }
        JPanel headerPanel = new JPanel();
        JPanel selectPropertyPanel = new JPanel();
        JPanel editPropertyPanel = new JPanel();

        JLabel generalMessage = new JLabel("Edit Property :");
        JLabel selectPropertyLabel = new JLabel("Select Property :");

        JComboBox selectPropertyComboBox = new JComboBox(propertyList);

        generalMessage.setPreferredSize(new Dimension(175,25));
        selectPropertyLabel.setPreferredSize(new Dimension(175, 25));
        selectPropertyComboBox.setPreferredSize(new Dimension(175, 25));

        headerPanel.setLayout(new FlowLayout());
        selectPropertyPanel.setLayout(new FlowLayout());
        //editPropertyPanel.setLayout(new FlowLayout());
        editPropertyPanel.setLayout(new BoxLayout(editPropertyPanel, BoxLayout.PAGE_AXIS));
        String options[] = {"Furnished","Not Furnished"};
        String propertyOptions[]= {"Apartment","Attached","Detached","Townhouse"};
        String quadrantOptions[] = {"SW", "NW", "NE", "SE"};

        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        JLabel bathroomsMessage = new JLabel("Number Bathrooms: ");
        JLabel bedroomsMessage = new JLabel("Number Bedrooms: ");
        JLabel furnishedMessage = new JLabel("Furnished: ");
        JLabel addressMessage = new JLabel("Address: ");
        JLabel quadrantMessage = new JLabel("Quadrant: ");
        JLabel priceMessage = new JLabel("Price:  ");
        JLabel propertyTypeLabel = new JLabel("Property Type:  ");

        bathroomsMessage.setPreferredSize(new Dimension(175, 25));
        bedroomsMessage.setPreferredSize(new Dimension(175, 25));
        furnishedMessage.setPreferredSize(new Dimension(175, 25));
        addressMessage.setPreferredSize(new Dimension(175, 25));
        quadrantMessage.setPreferredSize(new Dimension(175, 25));
        priceMessage.setPreferredSize(new Dimension(175, 25));
        propertyTypeLabel.setPreferredSize(new Dimension(175, 25));

        JTextField bathroomsTextField = new JTextField("Bathrooms");
        JTextField bedroomsTextField = new JTextField("Bedrooms");
        JTextField priceTextField = new JTextField("Price");
        JTextField addressTextField = new JTextField("Address");


        JComboBox furnishedComboField = new JComboBox<String>(options);
        JComboBox propertyTypeComboField =new JComboBox<String>(propertyOptions);
        JComboBox quadrantComboField = new JComboBox<String>(quadrantOptions);

        bedroomsTextField.setToolTipText("Set bedrooms Number to ..");
        bathroomsTextField.setToolTipText("Set bathrooms Number to ..");
        addressTextField.setToolTipText("Set address to...");
        furnishedComboField.setToolTipText("Select Furnished Option..");
        priceTextField.setToolTipText("Set Price to...");
        propertyTypeComboField.setToolTipText("Select Property Type..");
        quadrantComboField.setToolTipText("Select Quadrant..");

        bathroomsTextField.setPreferredSize(new Dimension(175, 25));
        bedroomsTextField.setPreferredSize(new Dimension(175, 25));
        priceTextField.setPreferredSize(new Dimension(175, 25));
        addressTextField.setPreferredSize(new Dimension(175, 25));


        furnishedComboField.setPreferredSize(new Dimension(175, 25));
        propertyTypeComboField.setPreferredSize(new Dimension(175, 25));
        quadrantComboField.setPreferredSize(new Dimension(175, 25));

        JButton updateButton = new JButton("Update");

        updateButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(updateButton)) {

                    if (isInteger(bathroomsTextField.getText())
                            && isInteger(bedroomsTextField.getText()) && addressTextField.getText() != "") {

                        int bathrooms = Integer.valueOf(bathroomsTextField.getText());
                        int bedrooms = Integer.valueOf(bedroomsTextField.getText());
                        String address = addressTextField.getText();
                        String quadrant = quadrantComboField.getSelectedItem().toString();
                        boolean furnished = false;
                        double price = Double.valueOf(priceTextField.getText());
                        if (furnishedComboField.getSelectedItem().toString() == "Furnished") {
                            furnished = true;
                        } else {
                            furnished = false;
                        }
                        String propertyTypeValue = propertyTypeComboField.getSelectedItem().toString();
                        
                        Property p1=new Property(0,propertyTypeValue,bathrooms,bedrooms,furnished,address,quadrant,prop.getStatus(),price);
                        p1.setPropertyID(propertyID);
                        SearchDatabase search =new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
                        
                        PropertyDetails details=new PropertyDetails(propertyTypeValue, bathrooms, bedrooms, furnished, price);
                        PropertyLocation location=new PropertyLocation(address,quadrant);
                       
                        
                        prop.setPropertyDetails(details);
                        prop.setPropertyLocation(location);
                        search.updateProperty(p1, 0);
                        
                        
                        //implement DB Action here.
                        JOptionPane.showMessageDialog(null, "Property was updated.");
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
        JPanel bedroomPanel = new JPanel();
        JPanel bathroomPanel = new JPanel();
        JPanel furnishedPanel = new JPanel();
        JPanel addressPanel = new JPanel();
        JPanel quadrantPanel = new JPanel();
        JPanel pricePanel = new JPanel();
        JPanel propertyTypePanel = new JPanel();
        JPanel registerPanel = new JPanel();

        //Set the Layouts for the JPanels
        bedroomPanel.setLayout(new FlowLayout());
        bathroomPanel.setLayout(new FlowLayout());
        furnishedPanel.setLayout(new FlowLayout());
        addressPanel.setLayout(new FlowLayout());
        pricePanel.setLayout(new FlowLayout());
        quadrantPanel.setLayout(new FlowLayout());
        pricePanel.setLayout(new FlowLayout());
        propertyTypePanel.setLayout(new FlowLayout());
        registerPanel.setLayout(new FlowLayout());

        //Add Components to the JPanels.

        bedroomPanel.add(bedroomsMessage);
        bedroomPanel.add(bedroomsTextField);
        bathroomPanel.add(bathroomsMessage);
        bathroomPanel.add(bathroomsTextField);
        addressPanel.add(addressMessage);
        addressPanel.add(addressTextField);
        quadrantPanel.add(quadrantMessage);
        quadrantPanel.add(quadrantComboField);
        furnishedPanel.add(furnishedMessage);
        furnishedPanel.add(furnishedComboField);
        pricePanel.add(priceMessage);
        pricePanel.add(priceTextField);
        propertyTypePanel.add(propertyTypeLabel);
        propertyTypePanel.add(propertyTypeComboField);
        registerPanel.add(updateButton);

        //Add the JPanels to the main JPanel
        editPropertyPanel.add(bedroomPanel);
        editPropertyPanel.add(bathroomPanel);
        editPropertyPanel.add(addressPanel);
        editPropertyPanel.add(quadrantPanel);
        editPropertyPanel.add(furnishedPanel);
        editPropertyPanel.add(pricePanel);
        editPropertyPanel.add(propertyTypePanel);
        editPropertyPanel.add(registerPanel);


        selectPropertyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selection = selectPropertyComboBox.getSelectedItem().toString();
                propertyID = Integer.valueOf(selection.substring(0, selection.indexOf("-")-1));
                for(int i = 0; i < data.getProperties().size(); i++) {
                    if(propertyID == data.getProperties().get(i).getPropertyID()) {
                    	
                        prop=data.getProperties().get(i);
                    	bathroomsTextField.setText(""+data.getProperties().get(i).getPropertyDetails().getNoBathrooms());
                        bedroomsTextField.setText(""+data.getProperties().get(i).getPropertyDetails().getNoBedrooms());
                        priceTextField.setText("" + data.getProperties().get(i).getPropertyDetails().getPrice());
                        addressTextField.setText(data.getProperties().get(i).getPropertyLocation().getAddress());
                        quadrantComboField.setSelectedItem(data.getProperties().get(i).getPropertyLocation().getQuadrant());
                        propertyTypeComboField.setSelectedItem(data.getProperties().get(i).getPropertyDetails().getPropertyType());
                        if(data.getProperties().get(i).getPropertyDetails().isFurnished()) {
                            furnishedComboField.setSelectedItem("Furnished");
                        } else {
                            furnishedComboField.setSelectedItem("Not Furnished");
                        }
                        mainContainer.add(editPropertyPanel);
                        revalidate();
                        repaint();
                        break;
                    }
                }
            }
        });

        headerPanel.add(generalMessage);
        selectPropertyPanel.add(selectPropertyLabel);
        selectPropertyPanel.add(selectPropertyComboBox);

        mainContainer.add(headerPanel);
        mainContainer.add(selectPropertyPanel);

        revalidate();
        repaint();

    }
    
    public void showEditProfile() {
    	mainContainer.removeAll();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        String landlordList[] = new String[data.getLandlords().size()];
        for(int i = 0; i < data.getLandlords().size(); i++) {
            landlordList[i] = String.valueOf(data.getLandlords().get(i).getLandlordID()) + " - " + data.getLandlords().get(i).getFirstName()+" "+data.getLandlords().get(i).getLastName();
        }
    	
    	
        JPanel headerPanel = new JPanel();
        JPanel selectLandlordPanel = new JPanel();
        JPanel editLandlordPanel = new JPanel();

        JLabel generalMessage = new JLabel("Edit Landlords :");
        JLabel selectLandlordLabel = new JLabel("Select Landlord :");

        JComboBox selectLandlordComboBox = new JComboBox(landlordList);

        generalMessage.setPreferredSize(new Dimension(175,25));
        selectLandlordLabel.setPreferredSize(new Dimension(175, 25));
        selectLandlordComboBox.setPreferredSize(new Dimension(175, 25));

        headerPanel.setLayout(new FlowLayout());
        selectLandlordPanel.setLayout(new FlowLayout());
        //editPropertyPanel.setLayout(new FlowLayout());
        editLandlordPanel.setLayout(new BoxLayout(editLandlordPanel, BoxLayout.PAGE_AXIS));
//        String options[] = {"Furnished","Not Furnished"};
//        String propertyOptions[]= {"Apartment","Attached","Detached","Townhouse"};
//        String quadrantOptions[] = {"SW", "NW", "NE", "SE"};

        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        JLabel fNameMessage = new JLabel("First Name: ");
        JLabel lNameMessage = new JLabel("Last Name: ");
        JLabel emailMessage = new JLabel("Email: ");
        JLabel dobMessage = new JLabel("Date of Birth: ");
      

        fNameMessage.setPreferredSize(new Dimension(175, 25));
        lNameMessage.setPreferredSize(new Dimension(175, 25));
        emailMessage.setPreferredSize(new Dimension(175, 25));
        dobMessage.setPreferredSize(new Dimension(175, 25));
        

        JTextField fNameTextField = new JTextField("First Name: ");
        JTextField lNameTextField = new JTextField("Last Name: ");
        JTextField emailTextField = new JTextField("Email:");
        JTextField dobTextField = new JTextField("Date of Birth: ");


//        JComboBox furnishedComboField = new JComboBox<String>(options);
//        JComboBox propertyTypeComboField =new JComboBox<String>(propertyOptions);
//        JComboBox quadrantComboField = new JComboBox<String>(quadrantOptions);

        fNameTextField.setToolTipText("Set First Name to ..");
        lNameTextField.setToolTipText("Set Last Name to ..");
        emailTextField.setToolTipText("Set Email to...");
        dobTextField.setToolTipText("Set Date of Birth to..");
        

        fNameTextField.setPreferredSize(new Dimension(175, 25));
        lNameTextField.setPreferredSize(new Dimension(175, 25));
        emailTextField.setPreferredSize(new Dimension(175, 25));
        dobTextField.setPreferredSize(new Dimension(175, 25));


//        furnishedComboField.setPreferredSize(new Dimension(175, 25));
//        propertyTypeComboField.setPreferredSize(new Dimension(175, 25));
//        quadrantComboField.setPreferredSize(new Dimension(175, 25));

        JButton updateButton = new JButton("Update");

        updateButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(updateButton)) {

                    if (lNameTextField.getText() != "" && dobTextField.getText() != ""
                            && emailTextField.getText() != "" && fNameTextField.getText() != "") {

                        String fName = fNameTextField.getText();
                        String lName = lNameTextField.getText();
                        String email = emailTextField.getText();
                        String dob = dobTextField.getText();
 
                        land.setDob(dob);
                        land.setEmail(email);
                        land.setFirstName(fName);
                        land.setLastName(lName);
                        
                        SearchDatabase search = new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
                        search.updateLandlord(land);
                        
                        
                        //implement DB Action here.
                        JOptionPane.showMessageDialog(null, "Landlord was updated.");
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
        JPanel fNamePanel = new JPanel();
        JPanel lNamePanel = new JPanel();
        JPanel emailPanel = new JPanel();
        JPanel dobPanel = new JPanel();
//        JPanel quadrantPanel = new JPanel();
//        JPanel pricePanel = new JPanel();
//        JPanel propertyTypePanel = new JPanel();
        JPanel registerPanel = new JPanel();

        //Set the Layouts for the JPanels
        fNamePanel.setLayout(new FlowLayout());
        lNamePanel.setLayout(new FlowLayout());
        emailPanel.setLayout(new FlowLayout());
        dobPanel.setLayout(new FlowLayout());
//        pricePanel.setLayout(new FlowLayout());
//        quadrantPanel.setLayout(new FlowLayout());
//        pricePanel.setLayout(new FlowLayout());
//        propertyTypePanel.setLayout(new FlowLayout());
        registerPanel.setLayout(new FlowLayout());

        //Add Components to the JPanels.

        fNamePanel.add(fNameMessage);
        fNamePanel.add(fNameTextField);
        lNamePanel.add(lNameMessage);
        lNamePanel.add(lNameTextField);
        emailPanel.add(emailMessage);
        emailPanel.add(emailTextField);
        dobPanel.add(dobMessage);
        dobPanel.add(dobTextField);
//        furnishedPanel.add(furnishedMessage);
//        furnishedPanel.add(furnishedComboField);
//        pricePanel.add(priceMessage);
//        pricePanel.add(priceTextField);
//        propertyTypePanel.add(propertyTypeLabel);
//        propertyTypePanel.add(propertyTypeComboField);
        registerPanel.add(updateButton);

        //Add the JPanels to the main JPanel
        editLandlordPanel.add(fNamePanel);
        editLandlordPanel.add(lNamePanel);
        editLandlordPanel.add(emailPanel);
        editLandlordPanel.add(dobPanel);
//        editLandlordPanel.add(furnishedPanel);
//        editPropertyPanel.add(pricePanel);
//        editPropertyPanel.add(propertyTypePanel);
        editLandlordPanel.add(registerPanel);


        selectLandlordComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selection = selectLandlordComboBox.getSelectedItem().toString();
                int propertyID = Integer.valueOf(selection.substring(0, selection.indexOf("-")-1));
                for(int i = 0; i < data.getLandlords().size(); i++) {
                    if(propertyID == data.getLandlords().get(i).getLandlordID()) {
                        land=data.getLandlords().get(i);
                    	fNameTextField.setText(""+data.getLandlords().get(i).getFirstName());
                        lNameTextField.setText(""+data.getLandlords().get(i).getLastName());
                        emailTextField.setText("" + data.getLandlords().get(i).getEmail());
                        dobTextField.setText(data.getLandlords().get(i).getDob());
                        
                        
                        mainContainer.add(editLandlordPanel);
                        revalidate();
                        repaint();
                        break;
                    }
                }
            }
        });

        headerPanel.add(generalMessage);
        selectLandlordPanel.add(selectLandlordLabel);
        selectLandlordPanel.add(selectLandlordComboBox);

        mainContainer.add(headerPanel);
        mainContainer.add(selectLandlordPanel);

        revalidate();
        repaint();

    }
    public void showEditRenter() {
    	mainContainer.removeAll();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        String renterList[] = new String[data.getRenters().size()];
        for(int i = 0; i < data.getRenters().size(); i++) {
            renterList[i] = data.getRenters().get(i).getRenterID() + " - " + data.getRenters().get(i).getFirstName()+" "+data.getRenters().get(i).getLastName();
        }
    	
    	
        JPanel headerPanel = new JPanel();
        JPanel selectRenterPanel = new JPanel();
        JPanel editRenterPanel = new JPanel();

        JLabel generalMessage = new JLabel("Edit Renters :");
        JLabel selectRenterLabel = new JLabel("Select Renter :");

        JComboBox selectRenterComboBox = new JComboBox(renterList);

        generalMessage.setPreferredSize(new Dimension(175,25));
        selectRenterLabel.setPreferredSize(new Dimension(175, 25));
        selectRenterComboBox.setPreferredSize(new Dimension(175, 25));

        headerPanel.setLayout(new FlowLayout());
        selectRenterPanel.setLayout(new FlowLayout());
        //editPropertyPanel.setLayout(new FlowLayout());
        editRenterPanel.setLayout(new BoxLayout(editRenterPanel, BoxLayout.PAGE_AXIS));
//        String options[] = {"Furnished","Not Furnished"};
//        String propertyOptions[]= {"Apartment","Attached","Detached","Townhouse"};
//        String quadrantOptions[] = {"SW", "NW", "NE", "SE"};

        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        JLabel fNameMessage = new JLabel("First Name: ");
        JLabel lNameMessage = new JLabel("Last Name: ");
        JLabel emailMessage = new JLabel("Email: ");
        JLabel dobMessage = new JLabel("Date of Birth: ");
      

        fNameMessage.setPreferredSize(new Dimension(175, 25));
        lNameMessage.setPreferredSize(new Dimension(175, 25));
        emailMessage.setPreferredSize(new Dimension(175, 25));
        dobMessage.setPreferredSize(new Dimension(175, 25));
        

        JTextField fNameTextField = new JTextField("First Name: ");
        JTextField lNameTextField = new JTextField("Last Name: ");
        JTextField emailTextField = new JTextField("Email:");
        JTextField dobTextField = new JTextField("Date of Birth: ");


//        JComboBox furnishedComboField = new JComboBox<String>(options);
//        JComboBox propertyTypeComboField =new JComboBox<String>(propertyOptions);
//        JComboBox quadrantComboField = new JComboBox<String>(quadrantOptions);

        fNameTextField.setToolTipText("Set First Name to ..");
        lNameTextField.setToolTipText("Set Last Name to ..");
        emailTextField.setToolTipText("Set Email to...");
        dobTextField.setToolTipText("Set Date of Birth to..");
        

        fNameTextField.setPreferredSize(new Dimension(175, 25));
        lNameTextField.setPreferredSize(new Dimension(175, 25));
        emailTextField.setPreferredSize(new Dimension(175, 25));
        dobTextField.setPreferredSize(new Dimension(175, 25));


//        furnishedComboField.setPreferredSize(new Dimension(175, 25));
//        propertyTypeComboField.setPreferredSize(new Dimension(175, 25));
//        quadrantComboField.setPreferredSize(new Dimension(175, 25));

        JButton updateButton = new JButton("Update");

        updateButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(updateButton)) {

                    if (lNameTextField.getText() != "" && dobTextField.getText() != ""
                            && emailTextField.getText() != "" && fNameTextField.getText() != "") {

                        String fName = fNameTextField.getText();
                        String lName = lNameTextField.getText();
                        String email = emailTextField.getText();
                        String dob = dobTextField.getText();
 
                        renter.setDob(dob);
                        renter.setEmail(email);
                        renter.setFirstName(fName);
                        renter.setLastName(lName);
                        
                        SearchDatabase search=new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
                        search.updateRenter(renter);
                        
                        //implement DB Action here.
                        JOptionPane.showMessageDialog(null, "Renter was updated.");
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
        JPanel fNamePanel = new JPanel();
        JPanel lNamePanel = new JPanel();
        JPanel emailPanel = new JPanel();
        JPanel dobPanel = new JPanel();

        JPanel registerPanel = new JPanel();

        //Set the Layouts for the JPanels
        fNamePanel.setLayout(new FlowLayout());
        lNamePanel.setLayout(new FlowLayout());
        emailPanel.setLayout(new FlowLayout());
        dobPanel.setLayout(new FlowLayout());

        registerPanel.setLayout(new FlowLayout());

        //Add Components to the JPanels.

        fNamePanel.add(fNameMessage);
        fNamePanel.add(fNameTextField);
        lNamePanel.add(lNameMessage);
        lNamePanel.add(lNameTextField);
        emailPanel.add(emailMessage);
        emailPanel.add(emailTextField);
        dobPanel.add(dobMessage);
        dobPanel.add(dobTextField);

        registerPanel.add(updateButton);

        //Add the JPanels to the main JPanel
        editRenterPanel.add(fNamePanel);
        editRenterPanel.add(lNamePanel);
        editRenterPanel.add(emailPanel);
        editRenterPanel.add(dobPanel);

        editRenterPanel.add(registerPanel);


        selectRenterComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selection = selectRenterComboBox.getSelectedItem().toString();
                int propertyID = Integer.valueOf(selection.substring(0, selection.indexOf("-")-1));
                for(int i = 0; i < data.getRenters().size(); i++) {
                    if(propertyID == data.getRenters().get(i).getRenterID()) {
                        renter=data.getRenters().get(i);
                    	fNameTextField.setText(""+data.getRenters().get(i).getFirstName());
                        lNameTextField.setText(""+data.getRenters().get(i).getLastName());
                        emailTextField.setText("" + data.getRenters().get(i).getEmail());
                        dobTextField.setText(data.getRenters().get(i).getDob());
                        
                        
                        mainContainer.add(editRenterPanel);
                        revalidate();
                        repaint();
                        break;
                    }
                }
            }
        });

        headerPanel.add(generalMessage);
        selectRenterPanel.add(selectRenterLabel);
        selectRenterPanel.add(selectRenterComboBox);

        mainContainer.add(headerPanel);
        mainContainer.add(selectRenterPanel);

        revalidate();
        repaint();
    }  
    
    void showReport() {
    	System.out.println("Test");
    	mainContainer.removeAll();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
    	 
        double[] value= new double[3];
    	  String[] information = new String[3];
    	  value[0] = data.getRenters().size();
    	  information[0] = "Renters";

    	  value[1] = data.getLandlords().size();
    	  information[1] = "Landlords";

    	  value[2] = data.getProperties().size();
    	  information[2] = "Properties";

    	  JLabel renters=new JLabel("Number of Renters: ");
    	  JLabel renterCount =new JLabel(String.valueOf(data.getRenters().size())); 
    	  
    	  JLabel landlord=new JLabel("Number of Landlord: ");
    	  JLabel landlordCount =new JLabel(String.valueOf(data.getLandlords().size())); 
    	  
    	  JLabel property=new JLabel("Number of Properties: ");
    	  JLabel propertyCount =new JLabel(String.valueOf(data.getProperties().size())); 
    	  
    	  JPanel renterPanel=new JPanel();
    	  JPanel landlordPanel=new JPanel();
    	  JPanel propertyPanel = new JPanel();
    	  
    	  renterPanel.add(renters);
    	  renterPanel.add(renterCount);
    	  landlordPanel.add(landlord);
    	  landlordPanel.add(landlordCount);
    	  propertyPanel.add(property);
    	  propertyPanel.add(propertyCount);
    	  
    	  mainContainer.add(renterPanel);
    	  mainContainer.add(landlordPanel);
    	  mainContainer.add(propertyPanel);
    	  revalidate();
          repaint();
    	  }
    
   
    	  
    public boolean isInteger(String tmp) {
        for(int i=0;i<tmp.length();i++) {
            if(tmp.charAt(i)>57 || tmp.charAt(i)<48) {
                return false;
            }
        }
        return true;
    }
}
