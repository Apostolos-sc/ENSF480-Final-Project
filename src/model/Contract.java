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
import java.util.Objects;

public class Contract {
    private int contractID;
    private Property property;
    private Landlord landlord;
    private Renter renter;
    private String startDate;
    private String endDate;
    private double monthlyRent;
    private String contractStatus;

    public Contract(int contractID, Renter renter, Property property, Landlord landlord, String startDate, String endDate, double monthlyRent, String contractStatus) {
        this.contractID = contractID;
        this.landlord = landlord;
        this.property = property;
        this.renter = renter;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyRent = monthlyRent;
        this.contractStatus = contractStatus;
    }

    public int getContractID() {
        return contractID;
    }

    public Property getProperty() {
        return property;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    public Renter getRenter() {
        return renter;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getMonthlyRent() {
        return monthlyRent;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setMonthlyRent(double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }
}
