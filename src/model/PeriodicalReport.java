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

public class PeriodicalReport {
    private ArrayList<Property> listedInPeriod;
    private ArrayList<Property> rentedInPeriod;
    private int numberOfHousesRented;
    private int totalActiveListings;

    public PeriodicalReport(ArrayList<Property> listedInPeriod,ArrayList<Property> rentedInPeriod, 
    int numberOfHousesRented,int totalActiveListings){
        this.listedInPeriod=listedInPeriod;
        this.rentedInPeriod=rentedInPeriod;
        this.numberOfHousesRented=numberOfHousesRented;
        this.totalActiveListings=totalActiveListings;
    }

    public void setListedInPeriod(ArrayList<Property> listedInPeriod){
        this.listedInPeriod=listedInPeriod;
    }
    
    public ArrayList<Property> getListedInPeriod(){
        return this.listedInPeriod;
    }

    public void setRentedInPeriod(ArrayList<Property> rentedInPeriod) {
        this.rentedInPeriod = rentedInPeriod;
    }

    public ArrayList<Property> getRentedInPeriod(){
        return this.rentedInPeriod;
    }

    public void setNumberOfHousesRented(int numberOfHousesRented){
        this.numberOfHousesRented=numberOfHousesRented;
    }

    public int getNumberOfHousesRented(){
        return this.numberOfHousesRented;
    }
    public void setTotalActiveListings( int totalActiveListings){
        this.totalActiveListings=totalActiveListings;
    }

    public int getTotalActiveListings(){
        return this.totalActiveListings;
    }
}
