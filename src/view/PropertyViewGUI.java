package view;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PropertyViewGUI extends JFrame implements ActionListener, MouseListener {
    private JLabel generalMessage1;
    private JLabel generalMessage2;
    private JLabel propertyTypeLabel;
    private JLabel propertyNoBathroomsLabel;
    private JLabel propertyNoBedroomsLabel;
    private JLabel propertyIsFurnishedLabel;
    private JLabel propertyAddressLabel;
    private JLabel propertyQuadrantLabel;
    private JLabel propertyStatusLabel;

    private JButton emailLandlordButton;
/*
    int propertyID, String propertyType, int noBathrooms, int noBedrooms,
    boolean isFurnished, String address, String quadrant,String status
 */
    public PropertyViewGUI(Property prop) {
        super("View Property with ID : " + prop.getPropertyID() + ".");
        setupGUI(prop);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setupGUI(Property prop) {
        generalMessage1 = new JLabel("Welcome to the View Property System.");
        generalMessage2 = new JLabel("You are now viewing Property : "+ prop.getPropertyID() +".");

        propertyTypeLabel = new JLabel("Type               : " + prop.getPropertyDetails().getPropertyType());
        propertyNoBedroomsLabel = new JLabel("Number of Bedrooms  : " + prop.getPropertyDetails().getNoBedrooms());
        propertyNoBathroomsLabel = new JLabel("Number of Bathrooms :" + prop.getPropertyDetails().getNoBathrooms());
        propertyIsFurnishedLabel = new JLabel("Furnished         :" + (prop.getPropertyDetails().isFurnished() ? "Yes" : "No"));
        propertyAddressLabel = new JLabel("Address           : " + prop.getPropertyLocation().getAddress());
        propertyQuadrantLabel = new JLabel("Quadrant          : "+ prop.getPropertyLocation().getQuadrant());
        propertyStatusLabel = new JLabel("Status          " + prop.getStatus());
        emailLandlordButton = new JButton("Email Landlord.");
        //add Mouse Listeners to the JTextFields and ActionListener to the JButton
        //Create the JPanels.
        JPanel mainContainer = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel propertyTypePanel = new JPanel();
        JPanel propertyNoOfBedroomsPanel = new JPanel();
        JPanel propertyNoOfBathroomsPanel = new JPanel();
        JPanel propertyIsFurnishedPanel = new JPanel();
        JPanel propertyAddressPanel = new JPanel();
        JPanel propertyQuadrantPanel = new JPanel();
        JPanel propertyStatusPanel = new JPanel();
        JPanel emailLandlordPanel = new JPanel();

        //Set the Layouts for the JPanels
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout());
        propertyTypePanel.setLayout(new FlowLayout());
        propertyNoOfBedroomsPanel.setLayout(new FlowLayout());
        propertyNoOfBathroomsPanel.setLayout(new FlowLayout());
        propertyIsFurnishedPanel.setLayout(new FlowLayout());
        propertyAddressPanel.setLayout(new FlowLayout());
        propertyQuadrantPanel.setLayout(new FlowLayout());
        propertyStatusPanel.setLayout(new FlowLayout());
        emailLandlordPanel.setLayout(new FlowLayout());

        //Add Components to the JPanels.

        headerPanel.add(generalMessage1);
        headerPanel.add(generalMessage2);
        propertyTypePanel.add(propertyTypeLabel);
        propertyNoOfBedroomsPanel.add(propertyNoBedroomsLabel);
        propertyNoOfBathroomsPanel.add(propertyNoBathroomsLabel);
        propertyIsFurnishedPanel.add(propertyIsFurnishedLabel);
        propertyAddressPanel.add(propertyAddressLabel);
        propertyQuadrantPanel.add(propertyQuadrantLabel);
        propertyStatusPanel.add(propertyStatusLabel);
        emailLandlordPanel.add(emailLandlordButton);

        //Add the JPanels to the main JPanel

        mainContainer.add(headerPanel);
        mainContainer.add(propertyTypePanel);
        mainContainer.add(propertyNoOfBedroomsPanel);
        mainContainer.add(propertyNoOfBathroomsPanel);
        mainContainer.add(propertyIsFurnishedPanel);
        mainContainer.add(propertyAddressPanel);
        mainContainer.add(propertyQuadrantPanel);
        mainContainer.add(propertyStatusPanel);
        mainContainer.add(emailLandlordPanel);

        //Add the main panel to the JFrame.
        this.add(mainContainer);
    }

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
