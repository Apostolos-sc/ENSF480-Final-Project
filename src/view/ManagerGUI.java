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
    private JMenuItem logoutOption;
    private JButton editPropertyButton;
    private JButton editLandlordButton;
    private JButton editRenterButton;
    private JButton viewPropertyButton;
    private JButton viewLandlordButton;
    private JButton viewRenterButton;
    private JButton logoutButton;
    private JFrame parentFrame;
    private Manager mgr;
    private Data data;


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

        logoutOption = new JMenuItem("Logout");
        logoutOption.addActionListener(this);
        viewProperty.addActionListener(this);
        viewLandlord.addActionListener(this);
        viewRenter.addActionListener(this);
        view.add(viewLandlord);
        view.add(viewProperty);
        view.add(viewRenter);

        edit.add(editLandlord);
        edit.add(editProperty);
        edit.add(editRenter);

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
	    if(e.getSource().equals(editPropertyButton)) {
	    	
	    }
	    if(e.getSource().equals(viewRenterButton)) {
	    	
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
        for(int i = 0; i < data.getProperties().size(); i++) {
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
}
