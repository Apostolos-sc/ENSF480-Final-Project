package controller;

/**
 @author Harsh Sharma <a href="mailto:harshit.sharma@ucalgary.ca">harshit.sharma@ucalgary.ca </a>
 Nuha Shaikh <a href="mailto:nuha.shaikh1@ucalgary.ca">nuha.shaikh1@ucalgary.ca</a>
 Apostolos Scondrianis <a href="mailto:"> </a>
 Dave Sharma <a href= "mailto:"> </a>

 @version 1.0
 @since  1.0
  * DatabaseAccess.java accesses and updates the SQL inventory database. It has data members DBURL to specify
  * which database is being accessed, USERNAME to specific username of user, PASSWORD and dbConnect
  * to establish a connection to the database.
 */

import java.sql.*;

public class SingletonDatabaseAccess {
    private String DBURL; //store the database url information, no setters
    private String USERNAME; //store the user's account username
    private String PASSWORD; //store the user's account password
    private static Connection dbConnect; //connection data member to establish connection to interact with database

    public SingletonDatabaseAccess() {
    }

    /**
     * Constructor for DatabaseAccess, sets username, dburl and password
     * @params the database url for database connection
     * @params the username for the database connection
     * @params the password for the database connection
     *
     */
    public void SingletonDatabaseAccess (String DBURL, String USERNAME, String PASSWORD) {
        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
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
    public boolean initializeConnection() {
        try { //need to have try and catch in the case the program fails to make connection with database
            dbConnect = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * close is a method that closes the Connection when all methods are run and executed.
     * @params nothing
     * @return nothing
     */
    public void close() {
        try {
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}