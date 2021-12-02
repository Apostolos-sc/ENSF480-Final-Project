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
    private static ArrayList<Renter> renters = new ArrayList<Renter>();
    private static ArrayList<Property> properties = new ArrayList<Property>();
    private static ArrayList<Landlord> landlords = new ArrayList<Landlord>();
    private static ArrayList<Manager> managers = new ArrayList<Manager>();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        User user = new Manager(5,"John", "Smith", "j.smith@gmail.com", "tester", "15-06-2015");
        /* start with a LoginGui frame */
        loginFrame = new LoginGUI(renters, landlords, managers, properties);
        EventQueue.invokeLater(() -> {
            loginFrame.setVisible(true);
        });
        // Test PropertyViewGUI
        propertyViewFrame = new PropertyViewGUI(new Property(500, "Detached", 4,5,true,"20 Bow Ridge Crescent", "SW","Listed"), user);
        EventQueue.invokeLater(() -> {
            propertyViewFrame.setVisible(true);
        });
    }
}
