package controller;

import java.sql.*;
import java.util.ArrayList;

import model.*;
import model.Date;

import javax.swing.*;

public class SearchDatabase {
    private Connection dbConnect;

    public SearchDatabase(Connection dbConnection){
        this.dbConnect= dbConnection;
    }

    public void addProperty(Property prop, Landlord lord) {
        try (Statement stmt1 = dbConnect.createStatement()){
            PreparedStatement statement = dbConnect.
            prepareStatement("INSERT INTO property(propertyID,landlordID,price,address,propertyType,quadrant,state,noBedrooms,noBathrooms,isFurnished) VALUES(?,?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, prop.getPropertyID());
            statement.setInt(2, lord.getLandlordID());
            statement.setDouble(3, prop.getPropertyDetails().getPrice());
            statement.setString(4, prop.getPropertyLocation().getAddress());
            statement.setString(5, prop.getPropertyDetails().getPropertyType());
            statement.setString(6, prop.getPropertyLocation().getQuadrant());
            statement.setString(7, prop.getStatus());
            statement.setInt(8, prop.getPropertyDetails().getNoBedrooms());
            statement.setInt(9, prop.getPropertyDetails().getNoBathrooms());
            statement.setBoolean(10, prop.getPropertyDetails().isFurnished());

            statement.execute(); 
            //new function call check whether this property matches search criteria of renter
            ArrayList<Integer> notifyRenters= notifProperty(prop);
            if(notifyRenters.size()!=0){
                //of matches notify the specific user
                // nuha will continue this implementation
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new IllegalArgumentException("Unable to access to database");
        }
    }

    public void createReport(Date period){
        //nuha will continue this implementation
    }

    public ArrayList<Integer> notifProperty(Property prop){
        // nuha will continue this implementation
        ArrayList<Integer> notifyRenters= new ArrayList<Integer>();
        // here when adding property if meets a renters search criteria get notified
        // by observer
        //traversing through search criteria table and checking if property matches
        //return an arraylist of renters it mathched for an notify them
        //else return empty array list
        return notifyRenters;
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
            PreparedStatement statement = dbConnect.prepareStatement("INSERT INTO inbox(senderEmail,recieverEmail,message) VALUES(?,?,?)");
        	//statement.setInt(1, msg.getMessageID());
        	statement.setString(1, msg.getSenderEmail());
        	statement.setString(2, msg.getRecieverEmail());
        	statement.setString(3, msg.getMessage());

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
//    public int inboxMaxID() {
//    		//ArrayList<InboxMessages> messages = new ArrayList<>();
//        int max=-1;
//        try (Statement stmt = dbConnect.createStatement()) {
//            ResultSet results = stmt.executeQuery("SELECT *FROM inbox"); //+"WHERE recieverEmail="+"'"+reciever.getEmail()+"'");
//            int i = 0;
//            while (results.next()) {// takes into account number of rows that were returned by the query
//                ResultSetMetaData rsmd = results.getMetaData();
//   
//                if(results.getInt("inboxID")>max) {
//                	max=results.getInt("inboxID");
//                }                    
//                i++;
//            }
//            stmt.close();
//            results.close();
//
//            if (i == 0) {
//                throw new IllegalArgumentException("There are currently no messages in the system");
//            }
//
//        } catch (SQLException e) {
//            throw new IllegalArgumentException("Unable to access to database");
//        }
//        
//        return max;
//        
//    }
    
    public String getEmailFromID(int ID) {
    	
    	int landlordID=-1;
    	try (Statement stmt = dbConnect.createStatement()) {
            ResultSet results = stmt.executeQuery("SELECT * FROM property");
            int i = 0;
            while (results.next()) {// takes into account number of rows that were returned by the query
                ResultSetMetaData rsmd = results.getMetaData();
                // propertyID , propertyType, noBathrooms, noBedrooms, isFurnished, address,
                // quadrant, status
                int holder=Integer.valueOf(results.getString("propertyID"));
               if(holder==ID) {
            	   landlordID=Integer.valueOf(results.getString("landlordID"));
               }

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

    	SingletonDatabaseAccess access = SingletonDatabaseAccess.getOnlyInstance();
    	ArrayList<Landlord> arr=access.retrieveLandlords();
    	String email=null;
    	if(landlordID!=-1) {
	    	for(int i=0;i<arr.size();i++) {
	    		if(arr.get(i).getLandlordID()==landlordID) {
	    			email=arr.get(i).getEmail();
	    		}
	    	}
    	}
    	return email;
    }  
    
    public int propertyMaxID() {
		//ArrayList<InboxMessages> messages = new ArrayList<>();
	    int max=-10;
	    try (Statement stmt = dbConnect.createStatement()) {
	        ResultSet results = stmt.executeQuery("SELECT *FROM property"); //+"WHERE recieverEmail="+"'"+reciever.getEmail()+"'");
	        int i = 0;
	        while (results.next()) {// takes into account number of rows that were returned by the query
	            ResultSetMetaData rsmd = results.getMetaData();
	         
	            if(results.getInt("propertyID")>max) {
	            	max=results.getInt("propertyID");
	            }
	                                  
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
	    
	    return max;
    
    }
    
    public void addProperty(Property p, int landlordID) {
    	
    	try (Statement stmt1 = dbConnect.createStatement()) {
    		
    
            PreparedStatement statement = dbConnect.prepareStatement("INSERT INTO property(propertyID,landlordID,price,address,propertyType,quadrant,state,noBedrooms,noBathrooms,isFurnished) VALUES(?,?,?,?,?,?,?,?,?,?)");
        	statement.setInt(1, p.getPropertyID());
        	statement.setInt(2, landlordID);
        	statement.setDouble(3, p.getPropertyDetails().getPrice());
        	statement.setString(4, p.getPropertyLocation().getAddress());
        	statement.setString(5, p.getPropertyDetails().getPropertyType());
        	statement.setString(6, p.getPropertyLocation().getQuadrant());
        	statement.setString(7, p.getStatus());
        	statement.setInt(8, p.getPropertyDetails().getNoBedrooms());
        	statement.setInt(9,p.getPropertyDetails().getNoBathrooms());
        	int furnish=0;
        	if(p.getPropertyDetails().isFurnished()==true) {
        		furnish=1;
        	}        	
        	statement.setInt(10,furnish);
        	
        	System.out.println(statement);
        	statement.execute(); //+"WHERE recieverEmail="+"'"+reciever.getEmail()+"'");
        	 statement.close();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
    	//System.out.println(stmt+editValue);
    }
    public void updateProperty(Property p,int landlordID) {
    
    	
    			try (Statement stmt1 = dbConnect.createStatement()) {
    	    		
    			    
    	            PreparedStatement statement = dbConnect.prepareStatement("UPDATE property SET price=?,address=?,propertyType=?,quadrant=?,state=?,noBedrooms=?,noBathrooms=?,isFurnished=? WHERE propertyID = ?");
    	        	//statement.setInt(1, landlordID);
    	        	statement.setDouble(1, p.getPropertyDetails().getPrice());
    	        	statement.setString(2, p.getPropertyLocation().getAddress());
    	        	statement.setString(3, p.getPropertyDetails().getPropertyType());
    	        	statement.setString(4, p.getPropertyLocation().getQuadrant());
    	        	statement.setString(5, p.getStatus());
    	        	statement.setInt(6, p.getPropertyDetails().getNoBedrooms());
    	        	statement.setInt(7,p.getPropertyDetails().getNoBathrooms());

    	        	int furnish=0;
    	        	if(p.getPropertyDetails().isFurnished()==true) {
    	        		furnish=1;
    	        	}        	
    	        	statement.setInt(8,furnish);
    	        	statement.setInt(9, p.getPropertyID());

    	        	System.out.println(statement);
    	        	statement.executeUpdate(); //+"WHERE recieverEmail="+"'"+reciever.getEmail()+"'");
    	        	 statement.close();
    	        }
    	        catch (SQLException e) {
    	            throw new IllegalArgumentException("Unable to access to database");
    	        }	
    }
    public void updateLandlord(Landlord p) {
        
    	
		try (Statement stmt1 = dbConnect.createStatement()) {
    		
		    
            PreparedStatement statement = dbConnect.prepareStatement("UPDATE users SET fName=?,lName=?,email=?,pass=?,dob=? WHERE userID =?");
        	statement.setString(1, p.getFirstName());
        	statement.setString(2, p.getLastName());
        	statement.setString(3, p.getEmail());
        	statement.setString(4, p.getPassword());
        	statement.setString(5, p.getDob());
        	statement.setInt(6, p.getLandlordID());
        

        	System.out.println(statement);
        	statement.executeUpdate(); //+"WHERE recieverEmail="+"'"+reciever.getEmail()+"'");
        	 statement.close();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }	
    }
public void updateRenter(Renter r) {
        
    	
		try (Statement stmt1 = dbConnect.createStatement()) {
    		
		    
            PreparedStatement statement = dbConnect.prepareStatement("UPDATE users SET fName=?,lName=?,email=?,pass=?,dob=? WHERE userID =?");
        	statement.setString(1, r.getFirstName());
        	statement.setString(2, r.getLastName());
        	statement.setString(3, r.getEmail());
        	statement.setString(4, r.getPassword());
        	statement.setString(5, r.getDob());
        	statement.setInt(6, r.getRenterID());
        

        	System.out.println(statement);
        	statement.executeUpdate(); //+"WHERE recieverEmail="+"'"+reciever.getEmail()+"'");
        	 statement.close();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }	
    }
    
}

