package controller;

import java.sql.*;
import java.util.ArrayList;
import model.Property;
import model.*;
import javax.swing.*;

public class SearchDatabase {
    private Connection dbConnect;

    public SearchDatabase(Connection dbConnection){
        this.dbConnect= dbConnection;
    }


    //the argument table is the name of the table we are traversing through, which is the property table
    public ArrayList<Property> searchItem(String table, String typeofProperty, int noOfBed, int noOfBath, boolean isFurnished, String quadrant, double price) throws IllegalArgumentException {
        ArrayList<Property> properties= new ArrayList<>();

        try(Statement stmt = dbConnect.createStatement()) {
                ResultSet results = stmt.executeQuery("SELECT *FROM " + table + " WHERE typeofProperty= '" + typeofProperty + "'"
                        + " WHERE noOfBed= '" + noOfBed + "'" + " WHERE noOfBath= '" + noOfBath + "'"
                        + " WHERE isFurnished= '" + isFurnished + "'" + " WHERE quadrant= '" + quadrant+ "'");
                int i=0;
                while (results.next()) {//takes into account number of rows that were returned by the query
                    ResultSetMetaData rsmd = results.getMetaData();
                                                //propertyID           , propertyType,            noBathrooms,     noBedrooms,       isFurnished,               address,          quadrant, status
                       Property prop= new Property(results.getInt(1), results.getString(2), results.getInt(2), results.getInt(3), results.getBoolean(4), results.getString(5), 
                       results.getString(6),  results.getString(7), 500);
                       properties.add(prop);
                       i++;
                }
                stmt.close();
                results.close();
     

            if (i==0) {
                throw new IllegalArgumentException("No such property exists");
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
        return properties;

    }

    //method returns all rows from property table
    public ArrayList<Property> getAllProperties(String table){
        ArrayList<Property> properties = new ArrayList<>();

        try (Statement stmt = dbConnect.createStatement()) {
            ResultSet results = stmt
                    .executeQuery("SELECT *FROM " + table);
            int i = 0;
            while (results.next()) {// takes into account number of rows that were returned by the query
                ResultSetMetaData rsmd = results.getMetaData();
                // propertyID , propertyType, noBathrooms, noBedrooms, isFurnished, address,
                // quadrant, status
                Property prop = new Property(results.getInt(1), results.getString(2), results.getInt(2),
                        results.getInt(3), results.getBoolean(4), results.getString(5),
                        results.getString(6), results.getString(7), 500.0);
                properties.add(prop);
                i++;
            }
            stmt.close();
            results.close();

            if (i == 0) {
                throw new IllegalArgumentException("There are currently no properties in the system");
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
        return properties;

    }
    public ArrayList<InboxMessages> getAllInboxMessages(String table,User reciever){
        ArrayList<InboxMessages> messages = new ArrayList<>();
        
        try (Statement stmt = dbConnect.createStatement()) {
            ResultSet results = stmt.executeQuery("SELECT *FROM " + table+" WHERE recieverEmail= '" + reciever + "'");
            int i = 0;
            while (results.next()) {// takes into account number of rows that were returned by the query
                ResultSetMetaData rsmd = results.getMetaData();
             
                //										InboxMessageID     Message				senderEmail				recieverEmail
                InboxMessages msg = new InboxMessages(results.getInt(1), results.getString(2), results.getString(3),results.getString(4));
                        
                messages.add(msg);
                i++;
            }
            stmt.close();
            results.close();

            if (i == 0) {
                throw new IllegalArgumentException("There are currently no messages in the system");
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
        return messages;
    }
}
