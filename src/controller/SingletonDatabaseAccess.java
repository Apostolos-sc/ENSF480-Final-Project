/** 
@author Dave Sharma <a href="mailto:dave.sharma@ucalgary.ca">agampreet.aulakh@ucalgary.ca </a>
Nuha Shaikh <a href="mailto:nuha.shaikh1@ucalgary.ca">nuha.shaikh1@ucalgary.ca</a>
Apostolos Scondrianis <a href="mailto:apostolos.scondriani@ucalgary.ca">huda.abbas@ucalgary.ca</a>
Harsh Sharma <a href= "mailto:harshit.sharma@ucalgary.ca">melanie.nguyen@ucalgary.ca</a>
@version 2.2
@since  2.0
  * Data.java (insert explanation of class)
*/
package controller;

import model.*;
import java.sql.*;
import java.util.ArrayList;

public class SingletonDatabaseAccess{
    private static SingletonDatabaseAccess onlyInstance;
    private String DBURL; //store the database url information, no setters
    private String USERNAME; //store the user's account username -> have to initialize it to one of ours
    private String PASSWORD; //store the user's account password -> have to initialize it to one of ours
    private static Connection dbConnect; //connection data member to establish connection to interact with database
    private boolean isSuccessful;

    public SingletonDatabaseAccess() {
    }

    /**
     * Constructor for DatabaseAccess, sets username, dburl and password
     * @params the database url for database connection
     * @params the username for the database connection
     * @params the password for the database connection
     *
     */
    public SingletonDatabaseAccess (String DBURL, String USERNAME, String PASSWORD) {
        //dont have to take user input once set to default value for password, username,
        // and DBURL
        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        initializeConnection();
    }

    /**
     * Getter method for data member database url
     * @params nothing
     * @return String value of DBURL
     */
    public String getDburl() {
        return this.DBURL;
    }

    /**
     * Getter method for data member USERNAME
     * @params nothing
     * @return String value of USERNAME
     */
    public String getUsername() {
        return this.USERNAME;
    }

    /**
     * Getter method for data member PASSWORD
     * @params nothing
     * @return String value of PASSWORD
     */
    public String getPassword() {
        return this.PASSWORD;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    //design pattern Singleton requirement
    public static SingletonDatabaseAccess getOnlyInstance(){
		if(onlyInstance == null){
			onlyInstance = new SingletonDatabaseAccess();
		}
		return onlyInstance;
	}

    /**
     * Getter method for database connection
     * @params nothing
     * @return Connection value of dbConnect
     */
    public Connection getDBConnect(){
        return dbConnect;
    }

    /**
     * initializeConnection creates a connection with the SQL database inventory.
     * @params nothing
     * @return true if connection succesful, false if database connection fails
     */
    public void initializeConnection() {
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
            isSuccessful = true;
        } catch (SQLException e) {
            isSuccessful = false;
            e.printStackTrace();
        }
    }

    /**
     * close is a method that closes the Connection when all methods are run and executed.
     * @params nothing
     * @return nothing
     */
    public void closeConnection() {
        try {
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Data retrieveData() {
        ArrayList<Renter> renters = retrieveRenters();
        ArrayList<Landlord> landlords = retrieveLandlords();
        ArrayList<Property> properties = retrieveProperties(landlords);
        ArrayList<Manager> managers = retrieveManagers();
        ArrayList<PropertyType> propertyTypes = retrievePropertyTypes();
        ArrayList<Contract> contracts = retrieveContracts(properties, renters, landlords);
        Data data = new Data(renters, properties, landlords, managers, contracts, propertyTypes);
        data.setRenters(retrieveRenters());
        return data;
    }
//     public ArrayList<Renter> retrieveRenters() {
//         ArrayList<Renter> renters = new ArrayList<Renter>();
//         String Query = "SELECT * FROM USERS INNER JOIN RENTER ON userID=renterID";
//         ResultSet results;
//         try {
//             Statement selectRenters = dbConnect.createStatement();
//             results = selectRenters.executeQuery(Query);
//             while(results.next()) {
//                 renters.add(new Renter(Integer.valueOf(results.getString("userID")), results.getString("fname"),
//                             results.getString("lname"), results.getString("email"), results.getString("pass"),
//                         results.getString("dob")));
//             }
//         } catch(SQLException ex) {
//             ex.printStackTrace();
//         }
//         return renters;
//     }
	public ArrayList<Renter> retrieveRenters() {
        ArrayList<Renter> renters = new ArrayList<Renter>();
        String Query = "SELECT * FROM USERS INNER JOIN RENTER ON userID=renterID";
        String query2 = "SELECT * FROM RENTER";
        ResultSet results;
        ResultSet result2;
        try {
            Statement selectRenters = dbConnect.createStatement();
            Statement selectSubscribe = dbConnect.createStatement();
            results = selectRenters.executeQuery(Query);
            result2 = selectSubscribe.executeQuery(query2);
            
            while(results.next() &&result2.next()) {
            	
	               Renter r1= (new Renter(Integer.valueOf(results.getString("userID")), results.getString("fname"),
	                            results.getString("lname"), results.getString("email"), results.getString("pass"),
	                        results.getString("dob")));
	               
	               boolean sub=false;
	               if(result2.getInt("subscribe")==1){
	            	   sub=true;
	               }
	               r1.setSubscribed(sub);
	               renters.add(r1);
            	}
            
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return renters;
    }

    public ArrayList<Manager> retrieveManagers() {
        ArrayList<Manager> managers = new ArrayList<Manager>();
        String Query = "SELECT * FROM USERS INNER JOIN MANAGER ON userID=managerID";
        ResultSet results;
        try {
            Statement selectRenters = dbConnect.createStatement();
            results = selectRenters.executeQuery(Query);
            while(results.next()) {
                managers.add(new Manager(Integer.valueOf(results.getString("userID")), results.getString("fname"),
                        results.getString("lname"), results.getString("email"), results.getString("pass"),
                        results.getString("dob")));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return managers;
    }

    public ArrayList<Landlord> retrieveLandlords() {
        ArrayList<Landlord> landlords = new ArrayList<Landlord>();
        String Query = "SELECT * FROM USERS INNER JOIN LANDLORD ON userID=landlordID";
        ResultSet results;
        try {
            Statement selectRenters = dbConnect.createStatement();
            results = selectRenters.executeQuery(Query);
            while(results.next()) {
                landlords.add(new Landlord(Integer.valueOf(results.getString("userID")), results.getString("fname"),
                        results.getString("lname"), results.getString("email"), results.getString("pass"),
                        results.getString("dob")));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return landlords;
    }
    //Call after you call retrieveLandlords..
    public ArrayList<Property> retrieveProperties(ArrayList<Landlord> landlords) {
        ArrayList<Property> properties = new ArrayList<Property>();
        String Query = "SELECT * FROM PROPERTY";
        ResultSet results;
        try {
            Statement selectRenters = dbConnect.createStatement();
            results = selectRenters.executeQuery(Query);
            int landlordID = 1;
            while(results.next()) {
                properties.add(new Property(Integer.valueOf(results.getString("propertyID")), results.getString("propertyType"),
                        Integer.valueOf(results.getString("noBathrooms")), Integer.valueOf(results.getString("noBedrooms")),
                        (results.getString("isFurnished").equals(""+1) ? true: false), results.getString("address"),
                        results.getString("quadrant"),results.getString("state"), Double.valueOf(results.getString("price"))));
                landlordID = Integer.valueOf(results.getString("landlordID"));
                for(int i = 0; i < landlords.size(); i++) {
                    if(landlords.get(i).getLandlordID() == landlordID) {
                        landlords.get(i).getProperties().add(properties.get(properties.size()-1));
                        break;
                    }
                }

            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
    public ArrayList<PropertyType> retrievePropertyTypes() {
        ArrayList<PropertyType> propertyTypes = new ArrayList<PropertyType>();
        String Query = "SELECT * FROM PROPERTY_TYPES";
        ResultSet results;
        try {
            Statement selectRenters = dbConnect.createStatement();
            results = selectRenters.executeQuery(Query);
            while(results.next()) {
                propertyTypes.add(new PropertyType(Integer.valueOf(results.getString("propertyTypeID")), results.getString("propertyType"),
                        Double.valueOf(results.getString("fees")), Integer.valueOf(results.getString("validDays"))));

            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return propertyTypes;
    }
    public ArrayList<Contract> retrieveContracts(ArrayList<Property> properties, ArrayList<Renter> renters, ArrayList<Landlord> landlords) {
        ArrayList<Contract> contracts = new ArrayList<Contract>();
        String Query = "SELECT * FROM CONTRACT";
        ResultSet results;
        try {
            Statement selectRenters = dbConnect.createStatement();
            results = selectRenters.executeQuery(Query);
            int landlordID = 0;
            int renterID = 0;
            int propertyID = 0;
            while(results.next()) {
                //Get the IDs from the Contract DB entry.
                landlordID = Integer.valueOf(results.getString("landlordID"));
                renterID = Integer.valueOf(results.getString("renterID"));
                propertyID = Integer.valueOf(results.getString("propertyID"));
                //Find the position in the corresponding ArrayList of landlords, renters, properties
                for(int i = 0; i < landlords.size(); i++) {
                    if(landlords.get(i).getLandlordID() == landlordID) {
                        landlordID = i;
                        break;
                    }
                }
                for(int i = 0; i < renters.size(); i++) {
                    if(renters.get(i).getRenterID() == renterID) {
                        renterID = i;
                        break;
                    }
                }
                for(int i = 0; i < properties.size(); i++) {
                    if(properties.get(i).getPropertyID() == propertyID) {
                        propertyID = i;
                        break;
                    }
                }
                contracts.add(new Contract(Integer.valueOf(results.getString("contractID")), renters.get(renterID),
                                properties.get(propertyID), landlords.get(landlordID), results.getString("startDate"),
                                results.getString("endDate"), Double.valueOf(results.getString("monthlyRent")), results.getString("contractStatus")));
                for(int i = 0; i < landlords.size(); i++) {
                    if(landlords.get(i).getLandlordID() == landlordID) {
                        landlords.get(i).getProperties().add(properties.get(properties.size()-1));
                        break;
                    }
                }

            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return contracts;
    }
}
