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
    private JMenu propertyTypeMenu;
    private JMenu settings;
    private JMenuItem editLandlord;
    private JMenuItem editProperty;
    private JMenuItem editRenter;
    private JMenuItem viewLandlord;
    private JMenuItem viewProperty;
    private JMenuItem viewRenter;
    private JMenuItem showReport;
    private JMenuItem showPropertyTypes;
    private JMenuItem editPropertyType;
    private JMenuItem addPropertyType;
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
	
    private JTextField periodTextField;
    private JTextField endTextField;

    private double[] value;
    private String[] information;
    private String title;

    private int propertyID;
    private Property prop;
    private Landlord land;
    private Renter renter;
    private PropertyType propertyType;
    
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
        //Create the JPanels.
        mainContainer = new JPanel();
        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        //Add the main panel to the JFrame.
        this.add(mainContainer);
    }

    public void setupMenu() {
        //Initialize MenuBar,Menus and Menu Items.
        menuBar = new JMenuBar();
        view = new JMenu("View");
        edit = new JMenu("Edit");
        report = new JMenu("Report");
        propertyTypeMenu = new JMenu("Property Type");
        settings = new JMenu("Options");

        viewLandlord = new JMenuItem("Landlord");
        viewProperty = new JMenuItem("Property");
        viewRenter = new JMenuItem("Renter");

        editLandlord = new JMenuItem("Landlord");
        editProperty = new JMenuItem("Property");
        editRenter = new JMenuItem("Renter");

        showPropertyTypes = new JMenuItem("Show Types");
        editPropertyType = new JMenuItem("Edit Type");
        addPropertyType = new JMenuItem("Create Type");

        showReport = new JMenuItem("Show Report");

        logoutOption = new JMenuItem("Logout");
        logoutOption.addActionListener(this);
        viewProperty.addActionListener(this);
        viewLandlord.addActionListener(this);
        viewRenter.addActionListener(this);
        editProperty.addActionListener(this);
        editRenter.addActionListener(this);
        editLandlord.addActionListener(this);
        showPropertyTypes.addActionListener(this);
        editPropertyType.addActionListener(this);
        addPropertyType.addActionListener(this);
        showReport.addActionListener(this);
        
        view.add(viewLandlord);
        view.add(viewProperty);
        view.add(viewRenter);

        edit.add(editLandlord);
        edit.add(editProperty);
        edit.add(editRenter);

        propertyTypeMenu.add(showPropertyTypes);
        propertyTypeMenu.add(editPropertyType);
        propertyTypeMenu.add(addPropertyType);

        report.add(showReport);
        
        settings.add(logoutOption);

        menuBar.add(view);
        menuBar.add(edit);
        menuBar.add(report);
        menuBar.add(propertyTypeMenu);
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

	    if(e.getSource().equals(showPropertyTypes)) {
	        showPropertyTypes();
        }
	    if(e.getSource().equals(editPropertyType)) {
	        showEditPropertyType();
        }

	    if(e.getSource().equals(addPropertyType)) {
	        showAddPropertyType();
        }
	    if(e.getSource().equals(viewLandlordButton)) {
	    	
	    }
	    if(e.getSource().equals(viewPropertyButton)) {

	    }
    }

    public void mouseClicked(MouseEvent event) {
        if(event.getSource().equals(periodTextField)) {
            periodTextField.setText("");
        }
    	if(event.getSource().equals(endTextField)) {
    		endTextField.setText("");
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
        String propertyStatus[] = {"Active", "Listed", "Cancelled", "Suspended"};

        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        JLabel bathroomsMessage = new JLabel("Number Bathrooms: ");
        JLabel bedroomsMessage = new JLabel("Number Bedrooms: ");
        JLabel furnishedMessage = new JLabel("Furnished: ");
        JLabel addressMessage = new JLabel("Address: ");
        JLabel quadrantMessage = new JLabel("Quadrant: ");
        JLabel priceMessage = new JLabel("Price:  ");
        JLabel propertyTypeLabel = new JLabel("Property Type:  ");
        JLabel propertyStatusLabel = new JLabel("Property Status:  ");

        bathroomsMessage.setPreferredSize(new Dimension(175, 25));
        bedroomsMessage.setPreferredSize(new Dimension(175, 25));
        furnishedMessage.setPreferredSize(new Dimension(175, 25));
        addressMessage.setPreferredSize(new Dimension(175, 25));
        quadrantMessage.setPreferredSize(new Dimension(175, 25));
        priceMessage.setPreferredSize(new Dimension(175, 25));
        propertyTypeLabel.setPreferredSize(new Dimension(175, 25));
        propertyStatusLabel.setPreferredSize(new Dimension(175, 25));

        JTextField bathroomsTextField = new JTextField("Bathrooms");
        JTextField bedroomsTextField = new JTextField("Bedrooms");
        JTextField priceTextField = new JTextField("Price");
        JTextField addressTextField = new JTextField("Address");


        JComboBox furnishedComboField = new JComboBox<String>(options);
        JComboBox propertyTypeComboField =new JComboBox<String>(propertyOptions);
        JComboBox quadrantComboField = new JComboBox<String>(quadrantOptions);
        JComboBox statusComboField = new JComboBox<String>(propertyStatus);

        bedroomsTextField.setToolTipText("Set bedrooms Number to ..");
        bathroomsTextField.setToolTipText("Set bathrooms Number to ..");
        addressTextField.setToolTipText("Set address to...");
        furnishedComboField.setToolTipText("Select Furnished Option..");
        priceTextField.setToolTipText("Set Price to...");
        propertyTypeComboField.setToolTipText("Select Property Type..");
        quadrantComboField.setToolTipText("Select Quadrant..");
        statusComboField.setToolTipText("Select Status..");

        bathroomsTextField.setPreferredSize(new Dimension(175, 25));
        bedroomsTextField.setPreferredSize(new Dimension(175, 25));
        priceTextField.setPreferredSize(new Dimension(175, 25));
        addressTextField.setPreferredSize(new Dimension(175, 25));


        furnishedComboField.setPreferredSize(new Dimension(175, 25));
        propertyTypeComboField.setPreferredSize(new Dimension(175, 25));
        quadrantComboField.setPreferredSize(new Dimension(175, 25));
        statusComboField.setPreferredSize(new Dimension(175, 25));

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
                        String propertyStatus = statusComboField.getSelectedItem().toString();
                        
                        Property p1=new Property(0,propertyTypeValue,bathrooms,bedrooms,furnished,address,quadrant,propertyStatus,price);
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
        JPanel statusPanel = new JPanel();
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
        statusPanel.setLayout(new FlowLayout());
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
        statusPanel.add(propertyStatusLabel);
        statusPanel.add(statusComboField);
        registerPanel.add(updateButton);

        //Add the JPanels to the main JPanel
        editPropertyPanel.add(bedroomPanel);
        editPropertyPanel.add(bathroomPanel);
        editPropertyPanel.add(addressPanel);
        editPropertyPanel.add(quadrantPanel);
        editPropertyPanel.add(furnishedPanel);
        editPropertyPanel.add(pricePanel);
        editPropertyPanel.add(propertyTypePanel);
        editPropertyPanel.add(statusPanel);
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
                        statusComboField.setSelectedItem(data.getProperties().get(i).getStatus());
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
        editLandlordPanel.setLayout(new BoxLayout(editLandlordPanel, BoxLayout.PAGE_AXIS));

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

        fNameTextField.setToolTipText("Set First Name to ..");
        lNameTextField.setToolTipText("Set Last Name to ..");
        emailTextField.setToolTipText("Set Email to...");
        dobTextField.setToolTipText("Set Date of Birth to..");
        

        fNameTextField.setPreferredSize(new Dimension(175, 25));
        lNameTextField.setPreferredSize(new Dimension(175, 25));
        emailTextField.setPreferredSize(new Dimension(175, 25));
        dobTextField.setPreferredSize(new Dimension(175, 25));

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
        editLandlordPanel.add(fNamePanel);
        editLandlordPanel.add(lNamePanel);
        editLandlordPanel.add(emailPanel);
        editLandlordPanel.add(dobPanel);
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
        editRenterPanel.setLayout(new BoxLayout(editRenterPanel, BoxLayout.PAGE_AXIS));

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

        fNameTextField.setToolTipText("Set First Name to ..");
        lNameTextField.setToolTipText("Set Last Name to ..");
        emailTextField.setToolTipText("Set Email to...");
        dobTextField.setToolTipText("Set Date of Birth to..");
        

        fNameTextField.setPreferredSize(new Dimension(175, 25));
        lNameTextField.setPreferredSize(new Dimension(175, 25));
        emailTextField.setPreferredSize(new Dimension(175, 25));
        dobTextField.setPreferredSize(new Dimension(175, 25));


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
    	JLabel periodLabel = new JLabel("Begenning Period:");
    	JLabel endLabel = new JLabel("    End Period: ");
    	JButton periodButton = new JButton("Enter");
        periodTextField= new JTextField("Begenning Period: ",18); 
        endTextField = new JTextField("End Period: ",18);
        
        periodTextField.addMouseListener(this);
        endTextField.addMouseListener(this);
        
        JPanel periodPanel = new JPanel();
        periodPanel.add(periodLabel);
        periodPanel.add(periodTextField);
        JPanel periodButtonPanel = new JPanel();
        periodButtonPanel.add(periodButton);
        periodPanel.add(endLabel);
        periodPanel.add(endTextField);
        
        periodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String columns[]= {"Price","Address","Bedroom","Bathroom","Quadrant","Furnishing","Property Type","House ID"};

            	SearchDatabase search = new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
//                ArrayList<Property> array2 = search.managerReportListing(Integer.valueOf(periodTextField.getText()));
//                ArrayList<Property> array = search.managerReportRented(Integer.valueOf(periodTextField.getText()));
//                ArrayList<Property> totalListing = search.managerReportTotalListing();

            	PeriodicalReport report = search.createReport(periodTextField.getText(), endTextField.getText());
                ArrayList<Property> array = report.getRentedInPeriod();
            	
                String properties [][]=new String[array.size()][9];
            	
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
            		properties[i][8]=String.valueOf(array.get(i).getPropertyID());
            	}
            	
            	//showTable(properties, columns, "Manager Report of Rented Houses");
            	TableModel model = new DefaultTableModel(properties,columns)
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
                headerLabel.setText("Manager Report of Rented Houses");
                headerPanel.add(headerLabel);
                mainContainer.add(headerPanel);
                mainContainer.add(tablePanel);
            	
            
            	JLabel totalLisited = new JLabel("Total Number of houses listed in past "+Integer.valueOf(periodTextField.getText())+" days: "+String.valueOf(report.getListedInPeriod().size()));
            	JLabel totalRented= new JLabel("        Total Number of houses rented in past "+Integer.valueOf(periodTextField.getText())+" days: "+String.valueOf(report.getNumberOfHousesRented()));
            	JLabel totalActive = new JLabel("       Total Number Active Listings: "+String.valueOf(report.getTotalActiveListings()));
            	JPanel listing = new JPanel();
            	listing.add(totalLisited);
            	listing.add(totalRented);
            	listing.add(totalActive);
            	mainContainer.add(listing);
            	revalidate();
                repaint();
            }
        });

        double[] value= new double[3];
    	  String[] information = new String[3];
    	  value[0] = data.getRenters().size();
    	  information[0] = "Renters";

    	  value[1] = data.getLandlords().size();
    	  information[1] = "Landlords";

    	  value[2] = data.getProperties().size();
    	  information[2] = "Properties";

    	  JLabel renters=new JLabel("Number of Renters: ");
    	  JLabel renterCount =new JLabel(String.valueOf(data.getRenters().size()),10); 
    	  
    	  JLabel landlord=new JLabel("     Number of Landlord: ");
    	  JLabel landlordCount =new JLabel(String.valueOf(data.getLandlords().size()),10); 
    	  
    	  JLabel property=new JLabel("     Number of Properties: ");
    	  JLabel propertyCount =new JLabel(String.valueOf(data.getProperties().size()),10); 
    	  
    	  JPanel topPanel = new JPanel();
    	  JPanel mainPanel=new JPanel();
    	  
    	  mainPanel.add(renters);
    	  mainPanel.add(renterCount);
    	  mainPanel.add(landlord);
    	  mainPanel.add(landlordCount);
    	  mainPanel.add(property);
    	  mainPanel.add(propertyCount);
    	  
    	  mainContainer.add(topPanel);
    	  mainContainer.add(mainPanel);

    	  mainContainer.add(periodPanel);
    	  mainContainer.add(periodButtonPanel);
    	  
    	  revalidate();
          repaint();
    }
    
   public void showPropertyTypes() {
       String[][] tableInfo = new String[data.getPropertyTypes().size()][8];
       for(int i = 0; i < data.getPropertyTypes().size(); i++) {
           tableInfo[i][0] = ""+data.getPropertyTypes().get(i).getPropertyTypeID();
           tableInfo[i][1] = data.getPropertyTypes().get(i).getPropertyType();
           tableInfo[i][2] = "" + data.getPropertyTypes().get(i).getValidDays();
           tableInfo[i][3] = ""+ data.getPropertyTypes().get(i).getFee();
       }
       String columns[] = {"ID","Type","Valid Days","Fee"};
       showTable(tableInfo,columns , "Viewing propertyTypes :");
   }

   public void showEditPropertyType() {
       mainContainer.removeAll();
       mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
       String propertyTypeList[] = new String[data.getPropertyTypes().size()];
       for(int i = 0; i < data.getPropertyTypes().size(); i++) {
           propertyTypeList[i] = data.getPropertyTypes().get(i).getPropertyTypeID() + " - " + data.getPropertyTypes().get(i).getPropertyType();
       }


       JPanel headerPanel = new JPanel();
       JPanel selectPropertyTypePanel = new JPanel();
       JPanel editPropertyTypePanel = new JPanel();

       JLabel generalMessage = new JLabel("Edit Property Type :");
       JLabel selectRenterLabel = new JLabel("Select Property Type :");

       JComboBox selectPropertyTypeComboBox = new JComboBox(propertyTypeList);

       generalMessage.setPreferredSize(new Dimension(175,25));
       selectRenterLabel.setPreferredSize(new Dimension(175, 25));
       selectPropertyTypeComboBox.setPreferredSize(new Dimension(175, 25));

       headerPanel.setLayout(new FlowLayout());
       selectPropertyTypePanel.setLayout(new FlowLayout());
       editPropertyTypePanel.setLayout(new BoxLayout(editPropertyTypePanel, BoxLayout.PAGE_AXIS));

       //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
       JLabel propertyTypeIDLabel = new JLabel("ID: ");
       JLabel propertyTypeLabel = new JLabel("Type: ");
       JLabel propertyTypeFeesLabel = new JLabel("Fee : ");
       JLabel propertyTypeValidDaysLabel = new JLabel("Valid Days: ");


       propertyTypeIDLabel.setPreferredSize(new Dimension(175, 25));
       propertyTypeLabel.setPreferredSize(new Dimension(175, 25));
       propertyTypeFeesLabel.setPreferredSize(new Dimension(175, 25));
       propertyTypeValidDaysLabel.setPreferredSize(new Dimension(175, 25));


       JLabel propertyTypeID2Label = new JLabel("");
       JTextField propertyTypeTextField = new JTextField("Type: ");
       JTextField propertyTypeFeesTextField = new JTextField("Fees:");
       JTextField propertyTypeValidDaysTextField = new JTextField("Valid Days: ");

       propertyTypeTextField.setToolTipText("Set Type to ..");
       propertyTypeFeesTextField.setToolTipText("Set Fees to...");
       propertyTypeValidDaysTextField.setToolTipText("Set Valid Days to..");


       propertyTypeID2Label.setPreferredSize(new Dimension(175, 25));
       propertyTypeTextField.setPreferredSize(new Dimension(175, 25));
       propertyTypeFeesTextField.setPreferredSize(new Dimension(175, 25));
       propertyTypeValidDaysTextField.setPreferredSize(new Dimension(175, 25));


       JButton updateButton = new JButton("Update");

       updateButton.addActionListener((new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               if (evt.getSource().equals(updateButton)) {

                   if (propertyTypeTextField.getText() != "" && propertyTypeFeesTextField.getText() != ""
                           && propertyTypeValidDaysTextField.getText() != "") {

                       String type = propertyTypeTextField.getText();
                       double fees = Double.valueOf(propertyTypeFeesTextField.getText());
                       int validDays = Integer.valueOf(propertyTypeValidDaysTextField.getText());

                       propertyType.setPropertyType(type);
                       propertyType.setFee(fees);
                       propertyType.setValidDays(validDays);

                       SearchDatabase search=new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
                       search.updatePropertyType(propertyType);

                       //implement DB Action here.
                       JOptionPane.showMessageDialog(null, "Property Type was updated.");
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
       JPanel propertyTypeIDPanel = new JPanel();
       JPanel propertyTypeTypePanel = new JPanel();
       JPanel propertyTypeFeesPanel = new JPanel();
       JPanel propertyTypeValidDaysPanel = new JPanel();
       JPanel updatePanel = new JPanel();

       //Set the Layouts for the JPanels
       propertyTypeIDPanel.setLayout(new FlowLayout());
       propertyTypeTypePanel.setLayout(new FlowLayout());
       propertyTypeFeesPanel.setLayout(new FlowLayout());
       propertyTypeValidDaysPanel.setLayout(new FlowLayout());
       updatePanel.setLayout(new FlowLayout());

       //Add Components to the JPanels.

       propertyTypeIDPanel.add(propertyTypeIDLabel);
       propertyTypeIDPanel.add(propertyTypeID2Label);
       propertyTypeTypePanel.add(propertyTypeLabel);
       propertyTypeTypePanel.add(propertyTypeTextField);
       propertyTypeFeesPanel.add(propertyTypeFeesLabel);
       propertyTypeFeesPanel.add(propertyTypeFeesTextField);
       propertyTypeValidDaysPanel.add(propertyTypeValidDaysLabel);
       propertyTypeValidDaysPanel.add(propertyTypeValidDaysTextField);
       updatePanel.add(updateButton);

       //Add the JPanels to the main JPanel
       editPropertyTypePanel.add(propertyTypeIDPanel);
       editPropertyTypePanel.add(propertyTypeTypePanel);
       editPropertyTypePanel.add(propertyTypeFeesPanel);
       editPropertyTypePanel.add(propertyTypeValidDaysPanel);
       editPropertyTypePanel.add(updatePanel);


       selectPropertyTypeComboBox.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               String selection = selectPropertyTypeComboBox.getSelectedItem().toString();
               int propertyTypeID = Integer.valueOf(selection.substring(0, selection.indexOf("-")-1));
               for(int i = 0; i < data.getRenters().size(); i++) {
                   if(propertyTypeID == data.getPropertyTypes().get(i).getPropertyTypeID()) {
                       propertyType = data.getPropertyTypes().get(i);
                       propertyTypeID2Label.setText(""+data.getPropertyTypes().get(i).getPropertyTypeID());
                       propertyTypeTextField.setText(data.getPropertyTypes().get(i).getPropertyType());
                       propertyTypeFeesTextField.setText(""+data.getPropertyTypes().get(i).getFee());
                       propertyTypeValidDaysTextField.setText("" + data.getPropertyTypes().get(i).getValidDays());

                       mainContainer.add(editPropertyTypePanel);
                       revalidate();
                       repaint();
                       break;
                   }
               }
           }
       });

       headerPanel.add(generalMessage);
       selectPropertyTypePanel.add(selectRenterLabel);
       selectPropertyTypePanel.add(selectPropertyTypeComboBox);

       mainContainer.add(headerPanel);
       mainContainer.add(selectPropertyTypePanel);

       revalidate();
       repaint();
   }

   public void showAddPropertyType() {
       String options[] = {"Furnished","Not Furnished"};
       String[] propertyTypeOptions = new String[data.getPropertyTypes().size()];
       for(int i = 0; i < data.getPropertyTypes().size(); i++) {

       }
       //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
       JLabel generalMessage = new JLabel("Create Property Type");
       JLabel propertyTypeMessage = new JLabel("Property Type: ");
       JLabel propertyTypeFeesMessage = new JLabel("Fee: ");
       JLabel propertyTypeValidDaysMessage = new JLabel("Valid Days: ");

       propertyTypeMessage.setPreferredSize(new Dimension(175, 25));
       propertyTypeFeesMessage.setPreferredSize(new Dimension(175, 25));
       propertyTypeValidDaysMessage.setPreferredSize(new Dimension(175, 25));

       JTextField propertyTypeTextField = new JTextField("");
       JTextField propertyTypeFeesTextField = new JTextField("");
       JTextField propertyTypeValidDaysTextField = new JTextField("");

       propertyTypeTextField.setToolTipText("Set bedrooms Number to ..");
       propertyTypeFeesTextField.setToolTipText("Set bathrooms Number to ..");
       propertyTypeValidDaysTextField.setToolTipText("Set address to...");

       propertyTypeTextField.setPreferredSize(new Dimension(175, 25));
       propertyTypeFeesTextField.setPreferredSize(new Dimension(175, 25));
       propertyTypeValidDaysTextField.setPreferredSize(new Dimension(175, 25));

       JButton createButton = new JButton("Register");

       createButton.addActionListener((new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               if (evt.getSource().equals(createButton)) {

                   if (propertyTypeTextField.getText() != ""
                           && isDouble(propertyTypeFeesTextField.getText()) == true && isInteger(propertyTypeValidDaysTextField.getText())) {

                       String type = propertyTypeTextField.getText();
                       double fee = Double.valueOf(propertyTypeFeesTextField.getText());
                       int validDays = Integer.valueOf(propertyTypeValidDaysTextField.getText());
                       SingletonDatabaseAccess access = SingletonDatabaseAccess.getOnlyInstance();
                       SearchDatabase search = new SearchDatabase(access.getDBConnect());
                       propertyType = new PropertyType(data.getPropertyTypes().size(), type, fee, validDays);
                       search.addPropertyType(propertyType);
                       data.getPropertyTypes().add(propertyType);
                       //implement DB Action here.
                       JOptionPane.showMessageDialog(null, "Property Type was created.");
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
       mainContainer.removeAll();
       JPanel headerPanel = new JPanel();
       JPanel propertyTypePanel = new JPanel();
       JPanel propertyTypeFeesPanel = new JPanel();
       JPanel propertyTypeValidDaysPanel = new JPanel();
       JPanel createPanel = new JPanel();

       //Set the Layouts for the JPanels
       mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
       headerPanel.setLayout(new FlowLayout());
       propertyTypePanel.setLayout(new FlowLayout());
       propertyTypeFeesPanel.setLayout(new FlowLayout());
       propertyTypeValidDaysPanel.setLayout(new FlowLayout());
       createPanel.setLayout(new FlowLayout());

       //Add Components to the JPanels.

       headerPanel.add(generalMessage);
       propertyTypePanel.add(propertyTypeMessage);
       propertyTypePanel.add(propertyTypeTextField);
       propertyTypeFeesPanel.add(propertyTypeFeesMessage);
       propertyTypeFeesPanel.add(propertyTypeFeesTextField);
       propertyTypeValidDaysPanel.add(propertyTypeValidDaysMessage);
       propertyTypeValidDaysPanel.add(propertyTypeValidDaysTextField);
       createPanel.add(createButton);

       //Add the JPanels to the main JPanel
       mainContainer.add(headerPanel);
       mainContainer.add(propertyTypePanel);
       mainContainer.add(propertyTypeFeesPanel);
       mainContainer.add(propertyTypeValidDaysPanel);
       mainContainer.add(createPanel);
       //Add the main panel to the JFrame.
       this.add(mainContainer);
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

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }

    }
}
