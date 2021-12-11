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

public class Renter extends User{
    private int renterID;
    private boolean isSubscribed; 
    private ArrayList<String> subscriptionSettings = new ArrayList<String>();

    public Renter(int renterID, String firstName, String lastName, String email, String password, String dob) {
        super(firstName, lastName, email, password, dob);
        this.renterID = renterID;
    }


    public int getRenterID() {
        return this.renterID;
    }

    public boolean isSubscribed() {
        return this.isSubscribed;
    }

    public ArrayList<String> getSubscriptionSettings() {
        return this.subscriptionSettings;
    }

    public void setSubscribed(boolean subscribed) {
        this.isSubscribed = subscribed;
    }

    public void setRenterID(int renterID) {
        this.renterID = renterID;
    }

    public void setSubscriptionSettings(ArrayList<String> subscriptionSettings) {
        this.subscriptionSettings = subscriptionSettings;
    }
}
