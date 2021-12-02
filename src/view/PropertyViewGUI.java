package view;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PropertyViewGUI extends JFrame implements ActionListener, MouseListener {
    private JLabel generalMessage;
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
    public PropertyViewGUI(Property prop, User user) {
        super("View Property with ID : " + prop.getPropertyID() + ".");
        setupGUI(prop, user);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setupGUI(Property prop, User user) {
        generalMessage = new JLabel("<html><div style='text-align: center;'>Welcome to the View Property System.<br />You are now viewing Property : "+ prop.getPropertyID() +".</div></html");
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.getAllFonts();

        Font font = new Font("Arial", Font.PLAIN, 12);
        propertyTypeLabel = new JLabel(String.format("%-60s","Type                             : " + prop.getPropertyDetails().getPropertyType()));
        propertyNoBedroomsLabel = new JLabel(String.format("%-60s","Number of Bedrooms  : " + prop.getPropertyDetails().getNoBedrooms()));
        propertyNoBathroomsLabel = new JLabel(String.format("%-60s","Number of Bathrooms : " + prop.getPropertyDetails().getNoBathrooms()));
        propertyIsFurnishedLabel = new JLabel(String.format("%-60s","Furnished                     : " + (prop.getPropertyDetails().isFurnished() ? "Yes" : "No")));
        propertyAddressLabel = new JLabel(String.format("%-60s","Address                        : " + prop.getPropertyLocation().getAddress()));
        propertyQuadrantLabel = new JLabel(String.format("%-60s","Quadrant                      : "+ prop.getPropertyLocation().getQuadrant()));
        propertyStatusLabel = new JLabel(String.format("%-60s","Status                           : " + prop.getStatus()));
        emailLandlordButton = new JButton("Email Landlord."+ user.getClass().getSimpleName());

        propertyTypeLabel.setFont(font);
        propertyNoBedroomsLabel.setFont(font);
        propertyNoBathroomsLabel.setFont(font);
        propertyIsFurnishedLabel.setFont(font);
        propertyAddressLabel.setFont(font);
        propertyQuadrantLabel.setFont(font);
        propertyStatusLabel.setFont(font);
        emailLandlordButton.setFont(font);
        generalMessage.setPreferredSize(new Dimension(300, 50));
        propertyTypeLabel.setPreferredSize(new Dimension(300, 25));
        propertyNoBedroomsLabel.setPreferredSize(new Dimension(300, 25));
        propertyNoBathroomsLabel.setPreferredSize(new Dimension(300, 25));
        propertyIsFurnishedLabel.setPreferredSize(new Dimension(300, 25));
        propertyAddressLabel.setPreferredSize(new Dimension(300, 25));
        propertyQuadrantLabel.setPreferredSize(new Dimension(300, 25));
        propertyStatusLabel.setPreferredSize(new Dimension(300, 25));
        emailLandlordButton.setPreferredSize(new Dimension(300, 25));

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

        headerPanel.add(generalMessage);
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
