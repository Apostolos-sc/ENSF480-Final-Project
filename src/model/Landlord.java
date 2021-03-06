/** 
@author Dave Sharma <a href="mailto:dave.sharma@ucalgary.ca">agampreet.aulakh@ucalgary.ca </a>
Nuha Shaikh <a href="mailto:nuha.shaikh1@ucalgary.ca">nuha.shaikh1@ucalgary.ca</a>
Apostolos Scondrianis <a href="mailto:apostolos.scondriani@ucalgary.ca">huda.abbas@ucalgary.ca</a>
Harsh Sharma <a href= "mailto:harshit.sharma@ucalgary.ca">melanie.nguyen@ucalgary.ca</a>
@version 2.2
@since  2.0
  * Data.java (insert explanation of class)
*/
package model;

import java.util.ArrayList;

public class Landlord extends User {
    private int landlordID;
    private ArrayList<Property> properties = new ArrayList<Property>();

    public Landlord(int landlordID, String firstName, String lastName, String email, String password, String dob) {
        super(firstName, lastName, email, password, dob);
        this.landlordID = landlordID;
    }

    public int getLandlordID() {
        return landlordID;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public void setLandlordID(int landlordID) {
        this.landlordID = landlordID;
    }

    public void addProperty(Property property) {
        this.properties.add(property);
    }

    public void removeProperty(int index) {
        if(index < properties.size() && index > 0) {
            properties.remove(index);
        }
    }

    public Property getProperty(int index) {
        if(index > 0 && index < properties.size()) {
            return properties.get(index);
        } else {
            return null;
        }
    }

    public int getPropertySize() {
        return properties.size();
    }
}