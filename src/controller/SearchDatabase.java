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
                ResultSet results = stmt.executeQuery("SELECT *FROM " + table);
                /*+ " WHERE propertyType= '" + typeofProperty + "'"
                        + " WHERE noBedrooms= '" + noOfBed + "'" + " WHERE noBathrooms= '" + noOfBath + "'"
                        + " WHERE isFurnished= '" + isFurnished + "'" + " WHERE quadrant= '" + quadrant);*/
                int i=0;
                while (results.next()) {//takes into account number of rows that were returned by the query
                    ResultSetMetaData rsmd = results.getMetaData();
                                                //propertyID           , propertyType,            noBathrooms,     noBedrooms,       isFurnished,               address,          quadrant, status
//                       Property prop= new Property(results.getInt(1), results.getString(2), results.getInt(2), results.getInt(3), results.getBoolean(4), results.getString(5), 
//                       results.getString(6),  results.getString(7), 500);
//                       System.out.print(results.getString("propertyType")+" "+Integer.valueOf(results.getString("noBathrooms"))+" "+Integer.valueOf(results.getString("noBedrooms"))+" "+results.getBoolean("isFurnished")+
//                    		   " "+results.getString("quadrant")+" "+results.getString("state")=="Listed");
//                       System.out.println();
                   if(results.getString("propertyType").equals(typeofProperty) && Integer.valueOf(results.getString("noBathrooms")).equals(noOfBath)
                    		&&Integer.valueOf(results.getString("noBedrooms")).equals(noOfBed)&&results.getBoolean("isFurnished")==isFurnished&&results.getString("quadrant").equals(quadrant)
                    		&&results.getString("state").equals("Listed")) {
                       Property prop = new Property(Integer.valueOf(results.getString("propertyID")), results.getString("propertyType"), Integer.valueOf(results.getString("noBathrooms")),
                               Integer.valueOf(results.getString("noBedrooms")), results.getBoolean("isFurnished"), results.getString("address"),
                               results.getString("quadrant"), results.getString("state"), results.getInt("Price"));
                       properties.add(prop);
                		}
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
                Property prop = new Property(Integer.valueOf(results.getString("propertyID")), results.getString("propertyType"), Integer.valueOf(results.getString("noBathrooms")),
                        Integer.valueOf(results.getString("noBedrooms")), results.getBoolean("isFurnished"), results.getString("address"),
                        results.getString("quadrant"), results.getString("state"), results.getInt("Price"));
             
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
            ResultSet results = stmt.executeQuery("SELECT *FROM " + table); //+"WHERE recieverEmail="+"'"+reciever.getEmail()+"'");
            int i = 0;
            while (results.next()) {// takes into account number of rows that were returned by the query
                ResultSetMetaData rsmd = results.getMetaData();
             
                //										InboxMessageID     Message				                senderEmail				recieverEmail
               if(results.getString("recieverEmail").equals(reciever.getEmail())) {
                InboxMessages msg = new InboxMessages(results.getInt("inboxID"), results.getString("message"), 
                		results.getString("senderEmail"),results.getString("recieverEmail"));
                        
                System.out.println(results.getString("recieverEmail"));
                messages.add(msg);
               }
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
    
    public void sendEmailMessage(InboxMessages msg) {
    	//String stmt="INSERT INTO inbox (inboxID,senderEmail,recieverEmail,message) VALUES (?,?,?,?)";
    	//String editValue=" VALUES ('"+String.valueOf(msg.getMessageID())+"','"+msg.getSenderEmail()+"','"+msg.getRecieverEmail()+"','"+msg.getMessage()+"');";    	
    	   
    	//String finalStmt=stmt;
    	System.out.println(msg.getMessageID());
        try (Statement stmt1 = dbConnect.createStatement()) {
            PreparedStatement statement = dbConnect.prepareStatement("INSERT INTO inbox(inboxID,senderEmail,recieverEmail,message) VALUES(?,?,?,?)");
        	statement.setInt(1, msg.getMessageID());
        	statement.setString(2, msg.getSenderEmail());
        	statement.setString(3, msg.getRecieverEmail());
        	statement.setString(4, msg.getMessage());

        	System.out.println(statement);
        	statement.execute(); //+"WHERE recieverEmail="+"'"+reciever.getEmail()+"'");
        	 statement.close();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
    	//System.out.println(stmt+editValue);
    }
    public int inboxMaxID() {
    		//ArrayList<InboxMessages> messages = new ArrayList<>();
        int max=-10;
        try (Statement stmt = dbConnect.createStatement()) {
            ResultSet results = stmt.executeQuery("SELECT *FROM inbox"); //+"WHERE recieverEmail="+"'"+reciever.getEmail()+"'");
            int i = 0;
            while (results.next()) {// takes into account number of rows that were returned by the query
                ResultSetMetaData rsmd = results.getMetaData();
             
                if(results.getInt("inboxID")>max) {
                	max=results.getInt("inboxID");
                }
                                      
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
        
        return max;
        
    }
    
}
