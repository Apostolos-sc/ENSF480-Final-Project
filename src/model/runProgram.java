package model;
import view.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

/* class runProgram initiates our Program
   and manages all the GUI Stuff.
 */
public class runProgram {
    private static LoginGUI loginFrame;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        loginFrame = new LoginGUI();
        EventQueue.invokeLater(() -> {
            loginFrame.setVisible(true);
        });
    }
}
