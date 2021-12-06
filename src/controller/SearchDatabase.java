package controller;

import java.sql.*;
import java.util.ArrayList;
import model.Property;

public class SearchDatabase {
    private Connection dbConnect;

    SearchDatabase(Connection dbConnection){
        this.dbConnect= dbConnection;
    }


    //the argument table is the name of the table we are traversing through, which is the property table
    public ArrayList<Property> searchItem(String table, String typeofProperty, int noOfBed, int noOfBath, boolean isFurnished, String quadrant) throws IllegalArgumentException {
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
                       results.getString(6),  results.getString(7));
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

    //method returns all properties in property table
    public ArrayList<Property> searchItem(String table){
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
                        results.getString(6), results.getString(7));
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
}
