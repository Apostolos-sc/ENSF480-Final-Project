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

        test_initialization(data);

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

    public static void test_initialization(Data data) {
        User user2 = new Manager(5,"John", "Smith", "j.smith@gmail.com", "tester", "15-06-2015");

        Property prop = new Property(500, "Apartment", 4,1,true,"25 Bow Valley Crescent", "SW","Listed", 500);
        Property prop1 = new Property(501, "Attached", 2,5,false,"20 Landing Ridge Crescent", "NW","Listed", 700);
        Property prop2 = new Property(502, "Townhouse", 1,3,false,"20 Combo Ridgeside Crescent", "SW","Listed", 800);
        Property prop3 = new Property(503, "Detached", 14,25,true,"20 Bow Meadow Crescent", "SE","Listed", 1000);
        Property prop4 = new Property(504, "Detached", 4,5,true,"20 Bow Ridge Crescent", "NW","Listed", 1200);
        Property prop5 = new Property(505, "Detached", 4,5,true,"20 Bow Ridge Crescent", "SW","Listed", 1500);
        Property prop6 = new Property(506, "Detached", 4,5,true,"20 Bow Ridge Crescent", "SW","Listed", 1700);
        Property prop7 = new Property(507, "Detached", 4,5,true,"20 Bow Ridge Crescent", "SW","Listed", 1900);

        User user3 = new Landlord(12, "Jenny", "Lance", "j.lance@gmail.com", "tested", "12-12-2016");
        Landlord land1 = new Landlord(5, "Sandy", "Lance", "s.lance@gmail.com", "tested2", "12-12-2010");
        Landlord land2 = new Landlord(6, "Randy", "Lance", "r.lance@gmail.com", "tested3", "12-12-2009");
        Landlord land3 = new Landlord(7, "Bandy", "Lance", "b.lance@gmail.com", "tested4", "12-12-2008");
        Landlord land4 = new Landlord(8, "Xandy", "Lance", "x.lance@gmail.com", "tested5", "12-12-2001");
        Landlord land5 = new Landlord(9, "Landy", "Lance", "l.lance@gmail.com", "tested6", "12-12-2000");
        Landlord land6 = new Landlord(10, "Pandy", "Lance", "p.lance@gmail.com", "tested7", "12-12-1999");
        Landlord land7 = new Landlord(11, "Zandy", "Lance", "z.lance@gmail.com", "tested8", "12-12-1998");
        ((Landlord)user3).getProperties().add(prop);
        ((Landlord)user3).getProperties().add(prop2);
        ((Landlord)user3).getProperties().add(prop3);
        ((Landlord)user3).getProperties().add(prop4);

        User user1 = new Renter(22, "Tony", "Montana", "t.montana@gmail.com", "testing", "17-02-2012");
        Renter renter1 = new Renter(15, "Tony", "Montana", "t.montana@gmail.com", "testing", "17-02-2012");
        Renter renter2 = new Renter(16, "Al", "Montana", "a.montana@gmail.com", "testing", "17-02-2012");
        Renter renter3 = new Renter(17, "Tony", "Roma", "t.roma@gmail.com", "testing", "17-02-2012");
        Renter renter4 = new Renter(22, "Zs", "Montana", "z.montana@gmail.com", "testing", "17-02-2012");
        Renter renter5 = new Renter(33, "Angela", "Montana", "ang.montana@gmail.com", "testing", "17-02-2012");
        Renter renter6 = new Renter(44, "Mike", "Montana", "m.montana@gmail.com", "testing", "17-02-2012");
        Renter renter7 = new Renter(55, "John", "Montana", "j.montana@gmail.com", "testing", "17-02-2012");

        data.getRenters().add((Renter)user1);
        data.getRenters().add(renter1);
        data.getRenters().add(renter2);
        data.getRenters().add(renter3);
        data.getRenters().add(renter4);
        data.getRenters().add(renter5);
        data.getRenters().add(renter6);
        data.getRenters().add(renter7);

        data.getLandlords().add((Landlord) user3);
        data.getLandlords().add(land1);
        data.getLandlords().add(land2);
        data.getLandlords().add(land3);
        data.getLandlords().add(land4);
        data.getLandlords().add(land5);
        data.getLandlords().add(land6);
        data.getLandlords().add(land7);

        data.getProperties().add(prop);
        data.getProperties().add(prop1);
        data.getProperties().add(prop2);
        data.getProperties().add(prop3);
        data.getProperties().add(prop4);
        data.getProperties().add(prop5);
        data.getProperties().add(prop6);
        data.getProperties().add(prop7);

        data.getManagers().add((Manager) user2);
    }
}
