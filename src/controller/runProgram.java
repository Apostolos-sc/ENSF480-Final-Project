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
        LoginGUI loginFrame;
        PropertyViewGUI propertyViewFrame;
        Data data = new Data();
        User user1 = new Renter(22, "Tony", "Montana", "t.montana@gmail.com", "testing", "17-02-2012");
        User user2 = new Manager(5,"John", "Smith", "j.smith@gmail.com", "tester", "15-06-2015");
        User user3 = new Landlord(12, "Jenny", "Lance", "j.lance@gmail.com", "tested", "12-12-2016");
        data.getRenters().add((Renter)user1);
        data.getManagers().add((Manager) user2);
        data.getLandlords().add((Landlord) user3);
        /* start with a LoginGui frame */
        loginFrame = new LoginGUI(data);
        EventQueue.invokeLater(() -> {
            loginFrame.setVisible(true);
        });
        // Test PropertyViewGUI
        //propertyViewFrame = new PropertyViewGUI(new Property(500, "Detached", 4,5,true,"20 Bow Ridge Crescent", "SW","Listed"), user1);
        /*EventQueue.invokeLater(() -> {
            propertyViewFrame.setVisible(true);
        });*/
    }
}
