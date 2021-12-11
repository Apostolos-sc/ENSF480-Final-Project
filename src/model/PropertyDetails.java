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

import java.util.*;
import java.util.Date;

public class PropertyDetails {
    private String propertyType;
    private int noBathrooms;
    private int noBedrooms;
    private boolean isFurnished;
    private double price;
    private double payment=20;
    private int validPeriod= 2; 
    private String startDate; // Date date=Date.valueOf(str);//converting string into sql date
    private String endDate; // Date date=Date.valueOf(str);//converting string into sql date


    public PropertyDetails(String propertyType, int noBathrooms, int noBedrooms, boolean isFurnished, double price) {
        this.isFurnished = isFurnished;
        this.noBathrooms = noBathrooms;
        this.noBedrooms = noBedrooms;
        this.propertyType = propertyType;
        this.price = price;
    }
    
    public String getStartDate(){
        return this.startDate;
    }

    public String getEndDate(Date endDate){
        return this.endDate;
    }

    public void setStartDate(String startDate){
        this.startDate=startDate;
    }

    public void setEndDate(String endDate){
        this.endDate=endDate;
    }

    public int getValidPeriod() {
        return validPeriod;
    }
    public void setValidPeriods(int val) {
        this.validPeriod=val;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double val) {
        this.payment = val;
    }
    public String getPropertyType() {
        return propertyType;
    }

    public int getNoBathrooms() {
        return noBathrooms;
    }

    public int getNoBedrooms() {
        return noBedrooms;
    }

    public boolean isFurnished() {
        return isFurnished;
    }

    public double getPrice() {
        return price;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setNoBathrooms(int noBathrooms) {
        this.noBathrooms = noBathrooms;
    }

    public void setNoBedrooms(int noBedrooms) {
        this.noBedrooms = noBedrooms;
    }

    public void setFurnished(boolean furnished) {
        isFurnished = furnished;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
