package controller;

import java.sql.*;
import java.util.*;
import java.util.Date;

import model.*;

import javax.swing.*;

public class SearchDatabase {
    private Connection dbConnect;

    public SearchDatabase(Connection dbConnection){
        this.dbConnect= dbConnection;
    }

    public void addProperty(Property p, int landlordID) {
        try (Statement stmt1 = dbConnect.createStatement()) {

            PreparedStatement statement = dbConnect.prepareStatement(
                    "INSERT INTO property(propertyID,landlordID,price,address,propertyType,quadrant,state,noBedrooms,noBathrooms,isFurnished,validPeriod) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, p.getPropertyID());
            statement.setInt(2, landlordID);
            statement.setDouble(3, p.getPropertyDetails().getPrice());
            statement.setString(4, p.getPropertyLocation().getAddress());
            statement.setString(5, p.getPropertyDetails().getPropertyType());
            statement.setString(6, p.getPropertyLocation().getQuadrant());
            statement.setString(7, p.getStatus());
            statement.setInt(8, p.getPropertyDetails().getNoBedrooms());
            statement.setInt(9, p.getPropertyDetails().getNoBathrooms());
            int furnish = 0;
            if (p.getPropertyDetails().isFurnished() == true) {
                furnish = 1;
            }
            statement.setInt(10, furnish);
            statement.setInt(11,20);
            
            System.out.println(statement);
            statement.execute(); 
            statement.close();
            
            notifProperty(p);
            
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
    }

    public PeriodicalReport createReport(String startOfPer, String endOfPer){
        Date startDate = java.sql.Date.valueOf(startOfPer);
        Date endDate = java.sql.Date.valueOf(endOfPer);
        ArrayList<Property> listedInPeriod = new ArrayList<>();
        ArrayList<Property> rentedInPeriod = new ArrayList<>();
        int numberOfHousesRented = 0;
        int totalActiveListings = 0;
        try (Statement stmt = dbConnect.createStatement()) {
            ResultSet results = stmt.executeQuery("SELECT * FROM PROPERTY WHERE startDate >=" + startOfPer + "startDate < "+endOfPer);
            while (results.next()) {// takes into account number of rows that were returned by the query
                ResultSetMetaData rsmd = results.getMetaData();
                if (results.getString("state").equals("Listed")) {
                    Property prop= new Property(Integer.valueOf(results.getString("propertyID")), results.getString("propertyType"), Integer.valueOf(results.getString("noBathrooms")),
                        Integer.valueOf(results.getString("noBedrooms")), results.getBoolean("isFurnished"), results.getString("address"),
                        results.getString("quadrant"), results.getString("state"), results.getInt("Price"));
                    listedInPeriod.add(prop);
                }else if(results.getString("state").equals("Rented")){
                    Property prop = new Property(Integer.valueOf(results.getString("propertyID")),
                            results.getString("propertyType"), Integer.valueOf(results.getString("noBathrooms")),
                            Integer.valueOf(results.getString("noBedrooms")), results.getBoolean("isFurnished"),
                            results.getString("address"),
                            results.getString("quadrant"), results.getString("state"), results.getInt("Price"));
                    rentedInPeriod.add(prop);
                    numberOfHousesRented++;
                }
            }
            stmt.close();
            results.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
        try (Statement stmt = dbConnect.createStatement()) {
            ResultSet results = stmt
                    .executeQuery("SELECT * FROM PROPERTY WHERE state = 'Listed'");
            while (results.next()) {// takes into account number of rows that were returned by the query
                ResultSetMetaData rsmd = results.getMetaData();
                    totalActiveListings++;
            }
            stmt.close();
            results.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
        PeriodicalReport report= new PeriodicalReport(listedInPeriod, rentedInPeriod,numberOfHousesRented, totalActiveListings);
        return report;
    }

    public void notifProperty(Property prop){
        try (Statement stmt = dbConnect.createStatement()) {
            ResultSet results = stmt.executeQuery("SELECT * FROM NOTIFCRITERIA");
            while (results.next()) {// takes into account number of rows that were returned by the query
                ResultSetMetaData rsmd = results.getMetaData();
                if (results.getString("propertyType").equals(prop.getPropertyDetails().getPropertyType())
                        && Integer.valueOf(results.getString("noBathrooms")).equals(prop.getPropertyDetails().getNoBathrooms())
                        && Integer.valueOf(results.getString("noBedrooms")).equals(prop.getPropertyDetails().getNoBedrooms())
                        && results.getBoolean("isFurnished") == prop.getPropertyDetails().isFurnished()
                        && results.getString("quadrant").equals(prop.getPropertyLocation().getQuadrant())
                        && results.getString("state").equals("Listed")) 
                {
                    addToSuggestedProperties(results.getInt("renterID"), prop.getPropertyID());                          
                }
            }
            stmt.close();
            results.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
    }

    public void addToSuggestedProperties(int renterID,int propertyID){
        try (Statement stmt = dbConnect.createStatement()) {
            PreparedStatement statement = dbConnect.prepareStatement("INSERT INTO SUGGESTEDPROPERTY(renterID, propertyID) VALUES(?,?)");
            statement.setInt(1, renterID);
            statement.setInt(2, propertyID);
            statement.execute();
            statement.close();
            stmt.close();

        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
    }
    //the argument table is the name of the table we are traversing through, which is the property table
    public ArrayList<Property> searchItem(String table, String typeofProperty, int noOfBed, int noOfBath, boolean isFurnished, String quadrant, double price) throws IllegalArgumentException {
        ArrayList<Property> properties= new ArrayList<>();
        try(Statement stmt = dbConnect.createStatement()) {
                ResultSet results = stmt.executeQuery("SELECT *FROM " + table);
                int i=0;
                while (results.next()) {//takes into account number of rows that were returned by the query
                    ResultSetMetaData rsmd = results.getMetaData();
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
             
                prop.getPropertyDetails().setValidPeriods(Integer.valueOf(results.getString("validPeriod")));
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
    	System.out.println(msg.getMessageID());
        try (Statement stmt1 = dbConnect.createStatement()) {
            PreparedStatement statement = dbConnect.prepareStatement("INSERT INTO inbox(senderEmail,recieverEmail,message) VALUES(?,?,?)");
        	statement.setString(1, msg.getSenderEmail());
        	statement.setString(2, msg.getRecieverEmail());
        	statement.setString(3, msg.getMessage());

        	System.out.println(statement);
        	statement.execute(); 
        	 statement.close();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
    }

    public int inboxMaxID() {
        int max=-10;
        try (Statement stmt = dbConnect.createStatement()) {
            ResultSet results = stmt.executeQuery("SELECT *FROM inbox"); 
            int i = 0;
            while (results.next()) {
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
	    int max=-10;
	    try (Statement stmt = dbConnect.createStatement()) {
	        ResultSet results = stmt.executeQuery("SELECT *FROM property"); 
	        int i = 0;
	        while (results.next()) {
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
    

    //used by manager to set property fees and period the fees are valid for
    public void updatePropFees(Property p, int managerID, int validPeriod, double payment){
        try (Statement stmt1 = dbConnect.createStatement()) {
            PreparedStatement statement = dbConnect.prepareStatement(
                    "UPDATE property SET validPeriod=?, payment=? WHERE propertyID = "+ p.getPropertyID());
            statement.setInt(1, validPeriod );
            statement.setDouble(2, payment);
            statement.executeUpdate(); 
            statement.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
    }

    //can use this function when have to change the state of a specific property
    public void updatePropState(Property p, String state){
        try (Statement stmt1 = dbConnect.createStatement()) {
            PreparedStatement statement = dbConnect.prepareStatement(
                    "UPDATE property SET state=? WHERE propertyID = " + p.getPropertyID());
            statement.setString(1, state);
            statement.executeUpdate(); 
            statement.close();
            if(state=="listed"){
                setPropertyPeriod(p);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
    }


    //check validity of all periods and if valid period of properties is expired then it changes the
    //state from listed to registered
    public void checkPropertyPeriod(){
        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());;
        try (Statement stmt1 = dbConnect.createStatement()) {
            PreparedStatement statement = dbConnect.prepareStatement(
                    "UPDATE property SET state=? WHERE endDate >= "+ sqlDate);
            statement.setString(1, "Registered");
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
    }

     public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

    public static Date subtractDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -days);
        return new Date(c.getTimeInMillis());
    }

    public void setPropertyPeriod(Property p){
        try (Statement stmt1 = dbConnect.createStatement()) {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            PreparedStatement statement = dbConnect.prepareStatement(
                    "UPDATE property SET startDate=" + date + " WHERE propertyID = " + p.getPropertyID());
            PreparedStatement stat= dbConnect.prepareStatement(
                    "UPDATE property SET endDate=" + addDays(date, p.getPropertyDetails().getValidPeriod()) + " WHERE propertyID = " + p.getPropertyID());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
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
        	PreparedStatement statement2 = dbConnect.prepareStatement("UPDATE renter SET subscribe=? WHERE renterID =?");
        	 
            statement.setString(1, r.getFirstName());
        	statement.setString(2, r.getLastName());
        	statement.setString(3, r.getEmail());
        	statement.setString(4, r.getPassword());
        	statement.setString(5, r.getDob());
        	statement.setInt(6, r.getRenterID());
        
        	int sub=0;
        	if(r.isSubscribed()) {
        		sub=1;
        	}
        	statement2.setInt(1,sub);
        	statement2.setInt(2, r.getRenterID());
        	
        	System.out.println(statement);
        	statement.executeUpdate(); //+"WHERE recieverEmail="+"'"+reciever.getEmail()+"'");
        	statement2.executeUpdate();
        	statement.close();
        	statement2.close();

        }
        catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }	
    }

    public void updateContract(Contract c) {


        try (Statement stmt1 = dbConnect.createStatement()) {


            PreparedStatement statement = dbConnect.prepareStatement("UPDATE contract SET startDate=?,endDate=?,monthlyRent=? WHERE contractID =?");

            statement.setString(1, c.getStartDate());
            statement.setString(2, c.getEndDate());
            statement.setDouble(3, c.getMonthlyRent());
            statement.setInt(4, c.getContractID());

            System.out.println(statement);
            statement.executeUpdate(); //+"WHERE recieverEmail="+"'"+reciever.getEmail()+"'");
            statement.close();

        }
        catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access database");
        }
    }

    public void addUser(String type,User u1,int userID) {
    	
    	if(type.equals("Renter")) {
	    	try (Statement stmt1 = dbConnect.createStatement()) {
	    		
			    
	            PreparedStatement statement = dbConnect.prepareStatement("INSERT INTO users(userID,fName,lName,email,pass,dob) VALUES(?,?,?,?,?,?)");
	        	statement.setInt(1, userID);
	        	statement.setString(2, u1.getFirstName());
	        	statement.setString(3, u1.getLastName());
	        	statement.setString(4, u1.getEmail());
	        	statement.setString(5, u1.getPassword());
	        	statement.setString(6, u1.getDob());
	        
	        	PreparedStatement statement2 = dbConnect.prepareStatement("INSERT INTO renter(renterID) VALUES(?)");
	        	statement2.setInt(1, userID);
	        	
	        	
	        	System.out.println(statement);
	        	statement.execute(); //+"WHERE recieverEmail="+"'"+reciever.getEmail()+"'");
	        	statement2.execute();
	        	statement.close();
	        	statement2.close();
	        	
	        }
	        catch (SQLException e) {
	            throw new IllegalArgumentException("Unable to access to database");
	        }
    	}
    	else {
    		try (Statement stmt1 = dbConnect.createStatement()) {
	    		
			    
    			 PreparedStatement statement = dbConnect.prepareStatement("INSERT INTO users(userID,fName,lName,email,pass,dob) VALUES(?,?,?,?,?,?)");
 	        	statement.setInt(1, userID);
 	        	statement.setString(2, u1.getFirstName());
 	        	statement.setString(3, u1.getLastName());
 	        	statement.setString(4, u1.getEmail());
 	        	statement.setString(5, u1.getPassword());
 	        	statement.setString(6, u1.getDob());
 	        
 	        	PreparedStatement statement2 = dbConnect.prepareStatement("INSERT INTO landlord(landlordID) VALUES(?)");
 	        	statement2.setInt(1, userID);
 	        	
 	        	
 	        	System.out.println(statement);
 	        	statement.execute(); //+"WHERE recieverEmail="+"'"+reciever.getEmail()+"'");
 	        	statement2.execute();
 	        	statement.close();
 	        	statement2.close();
	        }
	        catch (SQLException e) {
	            throw new IllegalArgumentException("Unable to access to database");
	        }
    	}
    }
    public int maxUserID() {
    	int max=1;
    	try (Statement stmt = dbConnect.createStatement()) {
    		
    		ResultSet results = stmt.executeQuery("SELECT *FROM users"); //+"WHERE recieverEmail="+"'"+reciever.getEmail()+"'");
	        int i = 0;
	        while (results.next()) {// takes into account number of rows that were returned by the query
	            ResultSetMetaData rsmd = results.getMetaData();
	         
	            if(results.getInt("userID")>max) {
	            	max=results.getInt("userID");
	            }
	                                  
	            i++;
	        }
	        stmt.close();
	        results.close();

        }
        catch (SQLException e) {
            throw new IllegalArgumentException("Unable to access to database");
        }
    	return max;
    }
	 public void addSearchedProperty(ArrayList<Property> arr,Renter r) {
    	
    	for(int i=0;i<arr.size();i++) {
	    	try (Statement stmt1 = dbConnect.createStatement()) {
	
	            PreparedStatement statement = dbConnect.prepareStatement(
	                    "INSERT INTO notifCriteria(renterID,propertyType,noBedrooms,noBathrooms,quadrant,isFurnished,propertyID) VALUES(?,?,?,?,?,?,?)");
	            statement.setInt(1,r.getRenterID());
	            statement.setString(2, arr.get(i).getPropertyDetails().getPropertyType());
	            statement.setInt(3, arr.get(i).getPropertyDetails().getNoBedrooms());
	            statement.setInt(4, arr.get(i).getPropertyDetails().getNoBathrooms());
	            statement.setString(5, arr.get(i).getPropertyLocation().getQuadrant());
	        
	            int furnish = 0;
	            if (arr.get(i).getPropertyDetails().isFurnished() == true) {
	                furnish = 1;
	            }
	            statement.setInt(6, furnish);
	            statement.setInt(7, arr.get(i).getPropertyID());
	            
	            System.out.println(statement);
	            statement.execute(); 
	            statement.close();
	            	            
	        } catch (SQLException e) {
	            throw new IllegalArgumentException("Unable to access to database");
	        }
    	}
    }
	    public ArrayList<Property> getNotifiedProperty(Renter id){
    	   ArrayList<Property> properties= new ArrayList<>();
    	   ResultSet results;
    	   ResultSet result2;
           try {
        	   Statement stmt = dbConnect.createStatement();
        	   Statement stmt2 = dbConnect.createStatement();
                   result2 = stmt2.executeQuery("SELECT *FROM notifcriteria");
                  // ArrayList<Property> renterID=this.selectedProperty(id);
                   while(result2.next()) {
                       results = stmt.executeQuery("SELECT *FROM property");
                       
                	   if(result2.getInt("renterID")==id.getRenterID()) {
		                   while(results.next()) {
		                       ResultSetMetaData rsmd2 = results.getMetaData();
		                       if((Integer.valueOf(result2.getString("noBedrooms"))==Integer.valueOf(results.getString("noBedrooms")) ||Integer.valueOf(result2.getString("noBathrooms"))==Integer.valueOf(results.getString("noBathrooms")) || result2.getString("quadrant").equals(results.getString("quadrant")))) {
		                    	   Property prop = new Property(Integer.valueOf(results.getString("propertyID")), results.getString("propertyType"), Integer.valueOf(results.getString("noBathrooms")),
			                                  Integer.valueOf(results.getString("noBedrooms")), results.getBoolean("isFurnished"), results.getString("address"),
			                                  results.getString("quadrant"), results.getString("state"), results.getInt("Price"));
		                    	   properties.add(prop);
		                       }
		                   }
                	   }
                   }
                  
                   stmt.close();
                   //results.close();

           } catch (SQLException e) {
               throw new IllegalArgumentException("Unable to access to database");
           }
           return properties;
    }
}

