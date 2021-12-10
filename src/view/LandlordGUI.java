package view;
import model.*;
import controller.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;

public class LandlordGUI extends JFrame implements ActionListener, MouseListener {
    private Data data;
    private Landlord landlord;
    private Contract contract;
    private Frame parentFrame;

    //MenuBar members
    private JMenuBar menuBar;
    private JMenu propertyMenu;
    private JMenu profileMenu;
    private JMenu contractMenu;
    private JMenuItem editProperty;
    private JMenuItem registerProperty;
    private JMenuItem viewMyProperties;
    private JMenuItem editContract;
    private JMenuItem viewMyContracts;
    private JMenuItem createContract;
    private JMenuItem editProfile;
    private JMenuItem payItem;
    private JMenuItem inbox;
    private JMenuItem logoutOption;

    //Key components
    private JPanel mainContainer;
    private JButton changeButton;
    private Property holder;
    int propertyID;

    public LandlordGUI(Landlord landlord, JFrame parentFrame, Data data) {
        super("Landlord System. Logged in as " + landlord.getFirstName() + " " + landlord.getLastName() + ".");
        this.landlord = landlord;
        this.parentFrame = parentFrame;
        this.data = data;
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
        //Menu Events.
        if(e.getSource().equals(editProperty)) {
            showEditProperty();
        }

        if(e.getSource().equals(viewMyProperties)) {
            showMyProperties();
        }

        if(e.getSource().equals(viewMyContracts)) {
            showMyContracts();
        }

        if(e.getSource().equals(editContract)) {
            showEditContract();
        }
//
//        if(e.getSource().equals(createContract)) {
//            showCreateContracts();
//        }

        if(e.getSource().equals(registerProperty)) {
            showRegisterProperty();
        	
        }

        if(e.getSource().equals(editProfile)) {
            showEditProfile();
        }

        if(e.getSource().equals(inbox)) {
        	Inbox loginFrame = new Inbox(landlord);
            EventQueue.invokeLater(() -> {
                loginFrame.setVisible(true);
            });
        }
        if(e.getSource().equals(logoutOption)) {
            this.setVisible(false);
            parentFrame.setVisible(true);
            this.dispose();
        }
        if(e.getSource().equals(payItem)) {
        	showPayProperty();
        	
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

    public void setupMenu() {
        //Initialize MenuBar,Menus and Menu Items.

        menuBar = new JMenuBar();
        propertyMenu = new JMenu("Property");
        profileMenu = new JMenu("User Actions");
        contractMenu = new JMenu("Contract");

        editProperty = new JMenuItem("Edit Property");
        viewMyProperties= new JMenuItem("View my Properties");
        registerProperty = new JMenuItem("Register a Property");

        editContract = new JMenuItem("Edit Contracts");
        viewMyContracts= new JMenuItem("View my Contracts");
        createContract = new JMenuItem("Create a Contract");

        editProfile = new JMenuItem("Edit My Profile");
        inbox = new JMenuItem("My Inbox");
        logoutOption = new JMenuItem("Logout");

        payItem = new JMenuItem("Pay");

        editProperty.addActionListener(this);
        viewMyProperties.addActionListener(this);
        registerProperty.addActionListener(this);

        editContract.addActionListener(this);
        viewMyContracts.addActionListener(this);
        createContract.addActionListener(this);

        editProfile.addActionListener(this);
        inbox.addActionListener(this);
        logoutOption.addActionListener(this);
        payItem.addActionListener(this);

        propertyMenu.add(viewMyProperties);
        propertyMenu.add(editProperty);
        propertyMenu.add(registerProperty);

        contractMenu.add(viewMyContracts);
        contractMenu.add(editContract);
        contractMenu.add(createContract);

        profileMenu.add(editProfile);
        profileMenu.add(payItem);
        profileMenu.add(inbox);
        profileMenu.add(logoutOption);


        menuBar.add(propertyMenu);
        menuBar.add(contractMenu);
        menuBar.add(profileMenu);

        this.add(menuBar);
        this.setJMenuBar(menuBar);
    }

    public void showEditProfile() {
        mainContainer.removeAll();
        JLabel generalMessage;
        JLabel fNameLabel;
        JLabel lNameLabel;
        JLabel emailLabel;
        JLabel passwordLabel;
        JLabel dobLabel;

        JTextField fNameTextField;
        JTextField lNameTextField;
        JTextField emailTextField;
        JTextField passwordTextField;
        JTextField dobTextField;

        String firstName="First Name: ";
        firstName+=landlord.getFirstName();
        String lastName="Last Name: ";
        lastName+=landlord.getLastName();
        String email="Email: ";
        email+=landlord.getEmail();
        String password="Password: ";
        password+=landlord.getPassword();
        String dob="Date of Birth: ";
        dob+=landlord.getDob();

        generalMessage = new JLabel("Edit my Profile.");

        fNameLabel = new JLabel(firstName);
        lNameLabel = new JLabel(lastName);
        emailLabel = new JLabel(email);
        passwordLabel = new JLabel(password);
        dobLabel = new JLabel(dob);
        fNameLabel.setPreferredSize(new Dimension(175, 25));
        lNameLabel.setPreferredSize(new Dimension(175, 25));
        emailLabel.setPreferredSize(new Dimension(175, 25));
        passwordLabel.setPreferredSize(new Dimension(175, 25));
        dobLabel.setPreferredSize(new Dimension(175, 25));

        fNameTextField = new JTextField("");
        lNameTextField= new JTextField("");
        emailTextField= new JTextField("");
        passwordTextField= new JTextField("");
        dobTextField= new JTextField("");

        fNameTextField.setToolTipText("Change First Name to...");
        lNameTextField.setToolTipText("Change Last Name to...");
        emailTextField.setToolTipText("Change Email to...");
        passwordTextField.setToolTipText("Change password to...");
        dobTextField.setToolTipText("Change Date of Birth to...");


        fNameTextField.setPreferredSize(new Dimension(175, 25));
        lNameTextField.setPreferredSize(new Dimension(175, 25));
        emailTextField.setPreferredSize(new Dimension(175, 25));
        passwordTextField.setPreferredSize(new Dimension(175, 25));
        dobTextField.setPreferredSize(new Dimension(175, 25));

        changeButton = new JButton("Change");

        changeButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String firstName;
                String lastName;
                String email;
                String password;
                String dob;
                firstName = fNameTextField.getText();
                lastName = lNameTextField.getText();
                email = emailTextField.getText();
                password = passwordTextField.getText();
                dob = dobTextField.getText();
                if(!firstName.equals("")) {
                    landlord.setFirstName(firstName);
                }
                if(!lastName.equals("")) {
                    landlord.setLastName(lastName);
                }
                if(!email.equals("")) {
                    landlord.setEmail(email);
                }
                if(!dob.equals("")) {
                    landlord.setDob(dob);
                }
                if(!password.equals("")) {
                    landlord.setPassword(password);
                }
                //CALL CONTROLLER FUNCTION TO CHANGE landlord
                
                SearchDatabase search = new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
                search.updateLandlord(landlord);
                JOptionPane.showMessageDialog(null, "Profile Information Changed!.");
            }
        }));
        //Create the JPanels.

        JPanel headerPanel = new JPanel();
        JPanel fNamePanel = new JPanel();
        JPanel lNamePanel = new JPanel();
        JPanel emailPanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JPanel dobPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        fNamePanel.setLayout(new FlowLayout());
        lNamePanel.setLayout(new FlowLayout());
        emailPanel.setLayout(new FlowLayout());
        passwordPanel.setLayout(new FlowLayout());
        dobPanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new FlowLayout());
        //Add Components to the JPanels.

        headerPanel.add(generalMessage);

        fNamePanel.add(fNameLabel);
        fNamePanel.add(fNameTextField);

        lNamePanel.add(lNameLabel);
        lNamePanel.add(lNameTextField);

        emailPanel.add(emailLabel);
        emailPanel.add(emailTextField);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);

        dobPanel.add(dobLabel);
        dobPanel.add(dobTextField);

        bottomPanel.add(changeButton);
        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(fNamePanel);
        mainContainer.add(lNamePanel);
        mainContainer.add(emailPanel);
        mainContainer.add(passwordPanel);
        mainContainer.add(dobPanel);
        mainContainer.add(bottomPanel);

        //Add the main panel to the JFrame.
        this.revalidate();
        this.repaint();
    }

    public void showMyContracts() {
        ArrayList<Contract> landlordContracts = new ArrayList<Contract>();
        for (int i = 0; i < data.getContracts().size(); i++) {
            if(data.getContracts().get(i).getLandlord().getLandlordID() == landlord.getLandlordID()) {
                landlordContracts.add(data.getContracts().get(i));
            }
        }
        String[][] tableInfo = new String[landlordContracts.size()][7];
        for(int i = 0; i < landlordContracts.size(); i++) {
            tableInfo[i][0] = ""+landlordContracts.get(i).getContractID();
            tableInfo[i][1] = landlordContracts.get(i).getRenter().getFirstName() + " " + landlordContracts.get(i).getRenter().getLastName();
            tableInfo[i][2] = ""+landlordContracts.get(i).getProperty().getPropertyID();
            tableInfo[i][3] = landlordContracts.get(i).getStartDate();
            tableInfo[i][4] = landlordContracts.get(i).getEndDate();
            tableInfo[i][5] = ""+landlordContracts.get(i).getMonthlyRent();
            tableInfo[i][6] = landlordContracts.get(i).getContractStatus();

        }
        String columns[] = {"Contract ID", "Renter","Property","Start Date", "End Date","Monthly Rent", "Contract Status"};
        showTable(tableInfo,columns , "Viewing my Contracts :");
    }

    public void showEditContract() {
        mainContainer.removeAll();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        ArrayList<Contract> landlordContracts = new ArrayList<Contract>();
        for (int i = 0; i < data.getContracts().size(); i++) {
            if(data.getContracts().get(i).getLandlord().getLandlordID() == landlord.getLandlordID() &&
                    !data.getContracts().get(i).getContractStatus().equals("Signed")) {
                landlordContracts.add(data.getContracts().get(i));
            }
        }
        String contractList[] = new String[landlordContracts.size()];
        for(int i = 0; i < landlordContracts.size(); i++) {
            contractList[i] = "Contract ID: " +  landlordContracts.get(i).getContractID() + ", Property ID: " + landlordContracts.get(i).getProperty().getPropertyID() + ", Renter ID:  " + landlordContracts.get(i).getRenter().getRenterID();
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
        JLabel contractStatusMessage2 = new JLabel();
        JLabel propertyIDMessage = new JLabel("Property ID: ");
        JLabel startDateMessage = new JLabel("Start Date: ");
        JLabel endDateMessage = new JLabel("End Date: ");
        JLabel monthlyRentMessage = new JLabel("Monthly Rent: ");
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
        contractStatusMessage.setPreferredSize(new Dimension(175,25));
        contractStatusMessage2.setPreferredSize(new Dimension(175,25));

        JTextField startDateTextField = new JTextField("Start Date");
        JTextField endDateTextField = new JTextField("End Date");
        JTextField monthlyRentTextField = new JTextField("Monthly Rent");

        startDateTextField.setToolTipText("Set Start Date to ..");
        endDateTextField.setToolTipText("Set End Date to ..");
        monthlyRentTextField.setToolTipText("Set Monthly Rent to...");

        startDateTextField.setPreferredSize(new Dimension(175, 25));
        endDateTextField.setPreferredSize(new Dimension(175, 25));
        monthlyRentTextField.setPreferredSize(new Dimension(175, 25));
        JButton updateButton = new JButton("Update Contract");
//        JButton deleteButton = new JButton("Delete Contract");

        updateButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(updateButton)) {

                    if (startDateTextField.getText() != ""
                            && isDouble(monthlyRentTextField.getText()) && startDateTextField.getText() != "") {

                        String startDate = startDateTextField.getText();
                        String endDate = endDateTextField.getText();
                        Double monthlyRent = Double.valueOf(monthlyRentTextField.getText());

                        contract.setStartDate(startDate);
                        contract.setEndDate(endDate);
                        contract.getMonthlyRent();
                        SearchDatabase search=new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
                        search.updateContract(contract);

                        //implement DB Action here.
                        JOptionPane.showMessageDialog(null, "Contract was updated.");
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
        startDatePanel.add(startDateTextField);
        endDatePanel.add(endDateMessage);
        endDatePanel.add(endDateTextField);
        monthlyRentPanel.add(monthlyRentMessage);
        monthlyRentPanel.add(monthlyRentTextField);
        contractStatusPanel.add(contractStatusMessage);
        contractStatusPanel.add(contractStatusMessage2);
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
                for(int i = 0; i < landlordContracts.size(); i++) {
                    if(contractID == landlordContracts.get(i).getContractID()) {
                        contract = landlordContracts.get(i);
                        contractIDMessage2.setText(""+landlordContracts.get(i).getContractID());
                        landlordIDMessage2.setText(""+landlordContracts.get(i).getLandlord().getLandlordID());
                        renterIDMessage2.setText(""+landlordContracts.get(i).getRenter().getRenterID());
                        propertyIDMessage2.setText(""+landlordContracts.get(i).getProperty().getPropertyID());
                        startDateTextField.setText(""+landlordContracts.get(i).getStartDate());
                        endDateTextField.setText(""+landlordContracts.get(i).getEndDate());
                        monthlyRentTextField.setText(""+landlordContracts.get(i).getMonthlyRent());
                        contractStatusMessage2.setText(""+landlordContracts.get(i).getContractStatus());
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

    public void showMyProperties() {
        String[][] tableInfo = new String[landlord.getProperties().size()][8];
        for(int i = 0; i < landlord.getProperties().size(); i++) {
            tableInfo[i][0] = ""+landlord.getProperties().get(i).getPropertyID();
            tableInfo[i][1] = landlord.getProperties().get(i).getPropertyDetails().getPropertyType();
            tableInfo[i][2] = landlord.getProperties().get(i).getPropertyLocation().getAddress();
            tableInfo[i][3] = ""+landlord.getProperties().get(i).getPropertyDetails().getNoBedrooms();
            tableInfo[i][4] = ""+landlord.getProperties().get(i).getPropertyDetails().getNoBathrooms();
            tableInfo[i][5] = landlord.getProperties().get(i).getPropertyLocation().getQuadrant();
            tableInfo[i][6] = (landlord.getProperties().get(i).getPropertyDetails().isFurnished() ? "Yes" : "No");
            tableInfo[i][7] = landlord.getProperties().get(i).getStatus();
        }
        String columns[] = {"ID","Type","Address","NoBedrooms", "NoBathrooms","Quadrant","Furnished", "Status"};
        showTable(tableInfo,columns , "Viewing my Properties :");
    }

    public void showRegisterProperty() {
        String options[] = {"Furnished","Not Furnished"};
        String propertyOptions[]= {"Apartment", "Attached", "Detached", "Townhouse"};
        String quadrantOptions[] = {"SW", "NW", "NE", "SE"};
        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        JLabel generalMessage = new JLabel("Register Property");
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

        JTextField bathroomsTextField = new JTextField("");
        JTextField bedroomsTextField = new JTextField("");
        JTextField priceTextField = new JTextField("");
        JTextField addressTextField = new JTextField("");

        JComboBox furnishedComboField = new JComboBox<String>(options);
        JComboBox propertyTypeComboField = new JComboBox<String>(propertyOptions);
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

        JButton registerButton = new JButton("Register");

        registerButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(registerButton)) {

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
                        			//need iD and price
                        
                        SingletonDatabaseAccess access = SingletonDatabaseAccess.getOnlyInstance();
                        SearchDatabase search = new SearchDatabase(access.getDBConnect());
                        int id=search.propertyMaxID();
                        Property addProperty = new Property(id+1,propertyTypeValue,bathrooms, bedrooms,furnished,address,quadrant,"Not Listed",price);
                       
                        search.addProperty(addProperty,landlord.getLandlordID());
                        landlord.addProperty(addProperty);
                        //implement DB Action here.
                        JOptionPane.showMessageDialog(null, "Property was registered");
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
        JPanel bedroomPanel = new JPanel();
        JPanel bathroomPanel = new JPanel();
        JPanel furnishedPanel = new JPanel();
        JPanel addressPanel = new JPanel();
        JPanel quadrantPanel = new JPanel();
        JPanel pricePanel = new JPanel();
        JPanel propertyTypePanel = new JPanel();
        JPanel registerPanel = new JPanel();

        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
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

        headerPanel.add(generalMessage);
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
        registerPanel.add(registerButton);

        //Add the JPanels to the main JPanel
        mainContainer.add(headerPanel);
        mainContainer.add(bedroomPanel);
        mainContainer.add(bathroomPanel);
        mainContainer.add(addressPanel);
        mainContainer.add(quadrantPanel);
        mainContainer.add(furnishedPanel);
        mainContainer.add(pricePanel);
        mainContainer.add(propertyTypePanel);
        mainContainer.add(registerPanel);
        //Add the main panel to the JFrame.
        this.add(mainContainer);
        revalidate();
        repaint();
    }

    public void showEditProperty() {
        mainContainer.removeAll();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        String propertyList[] = new String[landlord.getProperties().size()];
        for(int i = 0; i < landlord.getProperties().size(); i++) {
            propertyList[i] = landlord.getProperties().get(i).getPropertyID() + " - " + landlord.getProperties().get(i).getPropertyLocation().getAddress();
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
        String statusOptions[] = {"Registered", "Listed", "Rented", "Cancelled"}; //Need to add logic for fees paid.
        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        JLabel bathroomsMessage = new JLabel("Number Bathrooms: ");
        JLabel bedroomsMessage = new JLabel("Number Bedrooms: ");
        JLabel furnishedMessage = new JLabel("Furnished: ");
        JLabel addressMessage = new JLabel("Address: ");
        JLabel quadrantMessage = new JLabel("Quadrant: ");
        JLabel priceMessage = new JLabel("Price:  ");
        JLabel propertyTypeLabel = new JLabel("Property Type:  ");
        JLabel statusLabel = new JLabel("Status :");

        bathroomsMessage.setPreferredSize(new Dimension(175, 25));
        bedroomsMessage.setPreferredSize(new Dimension(175, 25));
        furnishedMessage.setPreferredSize(new Dimension(175, 25));
        addressMessage.setPreferredSize(new Dimension(175, 25));
        quadrantMessage.setPreferredSize(new Dimension(175, 25));
        priceMessage.setPreferredSize(new Dimension(175, 25));
        propertyTypeLabel.setPreferredSize(new Dimension(175, 25));
        statusLabel.setPreferredSize(new Dimension(175,25));

        JTextField bathroomsTextField = new JTextField("Bathrooms");
        JTextField bedroomsTextField = new JTextField("Bedrooms");
        JTextField priceTextField = new JTextField("Price");
        JTextField addressTextField = new JTextField("Address");


        JComboBox furnishedComboField = new JComboBox<String>(options);
        JComboBox propertyTypeComboField =new JComboBox<String>(propertyOptions);
        JComboBox quadrantComboField = new JComboBox<String>(quadrantOptions);
        JComboBox statusComboField = new JComboBox<String>(statusOptions);

        bedroomsTextField.setToolTipText("Set bedrooms Number to ..");
        bathroomsTextField.setToolTipText("Set bathrooms Number to ..");
        addressTextField.setToolTipText("Set address to...");
        furnishedComboField.setToolTipText("Select Furnished Option..");
        priceTextField.setToolTipText("Set Price to...");
        propertyTypeComboField.setToolTipText("Select Property Type..");
        quadrantComboField.setToolTipText("Select Quadrant..");
        statusComboField.setToolTipText("Select Status ..");

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

                        Property p1=new Property(propertyID,propertyTypeValue,bathrooms,bedrooms,furnished,address,quadrant,statusComboField.getSelectedItem().toString(),price);
                        SearchDatabase search=new SearchDatabase(SingletonDatabaseAccess.getOnlyInstance().getDBConnect());
                        search.updateProperty(p1, landlord.getLandlordID());
                        
                        ArrayList<Property> arr = landlord.getProperties();
                        
                        for(int i=0;i<arr.size();i++) {
                        	if(arr.get(i).getPropertyID()==propertyID) {
                        		arr.remove(i);
                        		break;
                        	}
                        }
                        arr.add(p1);
                        
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
        statusPanel.add(statusLabel);
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
                for(int i = 0; i < landlord.getProperties().size(); i++) {
                    if(propertyID == landlord.getProperties().get(i).getPropertyID()) {
                        bathroomsTextField.setText(""+landlord.getProperties().get(i).getPropertyDetails().getNoBathrooms());
                        bedroomsTextField.setText(""+landlord.getProperties().get(i).getPropertyDetails().getNoBedrooms());
                        priceTextField.setText("" + landlord.getProperties().get(i).getPropertyDetails().getPrice());
                        addressTextField.setText(landlord.getProperties().get(i).getPropertyLocation().getAddress());
                        quadrantComboField.setSelectedItem(landlord.getProperties().get(i).getPropertyLocation().getQuadrant());
                        propertyTypeComboField.setSelectedItem(landlord.getProperties().get(i).getPropertyDetails().getPropertyType());
                        if(landlord.getProperties().get(i).getPropertyDetails().isFurnished()) {
                            furnishedComboField.setSelectedItem("Furnished");
                        } else {
                            furnishedComboField.setSelectedItem("Not Furnished");
                        }
                        statusComboField.setSelectedItem(landlord.getProperties().get(i).getStatus());
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
        JLabel headerLabel = new JLabel();
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
    public void showPayProperty() {
        mainContainer.removeAll();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        String propertyList[] = new String[landlord.getProperties().size()];
        for(int i = 0; i < landlord.getProperties().size(); i++) {
            propertyList[i] = landlord.getProperties().get(i).getPropertyID() + " - " + landlord.getProperties().get(i).getPropertyLocation().getAddress();
        }
        JPanel headerPanel = new JPanel();
        JPanel selectPropertyPanel = new JPanel();
        JPanel editPropertyPanel = new JPanel();

        JLabel generalMessage = new JLabel("Pay Property :");
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
        String statusOptions[] = {"Registered", "Listed", "Rented", "Cancelled"}; //Need to add logic for fees paid.
        //Let's set up the JLabels and the JTextFields and the JButton for our GUI.
        JLabel bathroomsMessage = new JLabel("Number Bathrooms: ");
        JLabel bedroomsMessage = new JLabel("Number Bedrooms: ");
        JLabel furnishedMessage = new JLabel("Furnished: ");
        JLabel addressMessage = new JLabel("Address: ");
        JLabel quadrantMessage = new JLabel("Quadrant: ");
        JLabel priceMessage = new JLabel("Price:  ");
        JLabel propertyTypeLabel = new JLabel("Property Type:  ");
        JLabel statusLabel = new JLabel("Status :");

        bathroomsMessage.setPreferredSize(new Dimension(175, 25));
        bedroomsMessage.setPreferredSize(new Dimension(175, 25));
        furnishedMessage.setPreferredSize(new Dimension(175, 25));
        addressMessage.setPreferredSize(new Dimension(175, 25));
        quadrantMessage.setPreferredSize(new Dimension(175, 25));
        priceMessage.setPreferredSize(new Dimension(175, 25));
        propertyTypeLabel.setPreferredSize(new Dimension(175, 25));
        statusLabel.setPreferredSize(new Dimension(175,25));

        JTextField bathroomsTextField = new JTextField("Bathrooms");
        JTextField bedroomsTextField = new JTextField("Bedrooms");
        JTextField priceTextField = new JTextField("Price");
        JTextField addressTextField = new JTextField("Address");


        JComboBox furnishedComboField = new JComboBox<String>(options);
        JComboBox propertyTypeComboField =new JComboBox<String>(propertyOptions);
        JComboBox quadrantComboField = new JComboBox<String>(quadrantOptions);
        JComboBox statusComboField = new JComboBox<String>(statusOptions);

        bedroomsTextField.setToolTipText("Set bedrooms Number to ..");
        bathroomsTextField.setToolTipText("Set bathrooms Number to ..");
        addressTextField.setToolTipText("Set address to...");
        furnishedComboField.setToolTipText("Select Furnished Option..");
        priceTextField.setToolTipText("Set Price to...");
        propertyTypeComboField.setToolTipText("Select Property Type..");
        quadrantComboField.setToolTipText("Select Quadrant..");
        statusComboField.setToolTipText("Select Status ..");

        bathroomsTextField.setPreferredSize(new Dimension(175, 25));
        bedroomsTextField.setPreferredSize(new Dimension(175, 25));
        priceTextField.setPreferredSize(new Dimension(175, 25));
        addressTextField.setPreferredSize(new Dimension(175, 25));


        furnishedComboField.setPreferredSize(new Dimension(175, 25));
        propertyTypeComboField.setPreferredSize(new Dimension(175, 25));
        quadrantComboField.setPreferredSize(new Dimension(175, 25));
        statusComboField.setPreferredSize(new Dimension(175, 25));

        JButton updateButton = new JButton("Pay");

        updateButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(updateButton)) {

                	
                	SingletonDatabaseAccess access=SingletonDatabaseAccess.getOnlyInstance();
                	SearchDatabase search = new SearchDatabase(access.getDBConnect());
                		holder.setStatus("Listed");

                		search.updateProperty(holder,landlord.getLandlordID());
                        
                        //implement DB Action here.
                        JOptionPane.showMessageDialog(null, "Property was payed!.");
                        mainContainer.removeAll();
                        revalidate();
                        repaint();
                                       
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
        statusPanel.add(statusLabel);
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
                for(int i = 0; i < landlord.getProperties().size(); i++) {
                    if(propertyID == landlord.getProperties().get(i).getPropertyID()) {
                        holder = landlord.getProperties().get(i);
                    	bathroomsTextField.setText(""+landlord.getProperties().get(i).getPropertyDetails().getNoBathrooms());
                        bedroomsTextField.setText(""+landlord.getProperties().get(i).getPropertyDetails().getNoBedrooms());
                        priceTextField.setText("" + landlord.getProperties().get(i).getPropertyDetails().getPrice());
                        addressTextField.setText(landlord.getProperties().get(i).getPropertyLocation().getAddress());
                        quadrantComboField.setSelectedItem(landlord.getProperties().get(i).getPropertyLocation().getQuadrant());
                        propertyTypeComboField.setSelectedItem(landlord.getProperties().get(i).getPropertyDetails().getPropertyType());
                        if(landlord.getProperties().get(i).getPropertyDetails().isFurnished()) {
                            furnishedComboField.setSelectedItem("Furnished");
                        } else {
                            furnishedComboField.setSelectedItem("Not Furnished");
                        }
                        statusComboField.setSelectedItem(landlord.getProperties().get(i).getStatus());
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

    public boolean isInteger(String tmp) {
        for(int i=0;i<tmp.length();i++) {
            if(tmp.charAt(i)>57 || tmp.charAt(i)<48) {
                return false;
            }
        }
        return true;
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
