package controller;
import view.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import model.*;
import view.*;
/*
   class runProgram initiates our Program and manages all
   the GUI Stuff, initializes database connection.
 */
public class runProgram {
    private static LoginGUI loginFrame;
    private static PropertyViewGUI propertyViewFrame;
    ArrayList<Renter> renters = new ArrayList<Renter>();
    ArrayList<Property> properties = new ArrayList<Property>();
    ArrayList<Landlord> landlords = new ArrayList<Landlord>();
    ArrayList<Manager> managers = new ArrayList<Manager>();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        /* start with a LoginGui frame */
        /*loginFrame = new LoginGUI();
        EventQueue.invokeLater(() -> {
            loginFrame.setVisible(true);
        });*/
        // Test PropertyViewGUI
        propertyViewFrame = new PropertyViewGUI(new Property(500, "Detached", 4,5,true,"20 Bow Ridge Crescent", "SW","Listed"));
        EventQueue.invokeLater(() -> {
            propertyViewFrame.setVisible(true);
        });
    }
}
