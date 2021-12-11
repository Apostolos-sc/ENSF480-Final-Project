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


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Data data = new Data();

        // jdbc:mysql://localhost:3306/sonoo","root","root"
        SingletonDatabaseAccess dbConnection = new SingletonDatabaseAccess("jdbc:mysql://localhost:3306/property_management", "root", "Minakshi1Devinder$");
        //dbConnection.saveData(data);
        if(dbConnection.isSuccessful()) {
            System.out.print("Successful connection.");
        } else {
            System.out.println("Unsuccessful connection.");
        }
        data = dbConnection.retrieveData();
        LoginGUI loginFrame;
        PropertyViewGUI propertyViewFrame;


        /* start with a LoginGui frame */
        loginFrame = new LoginGUI();
        EventQueue.invokeLater(() -> {
            loginFrame.setVisible(true);
        });
    }

}
