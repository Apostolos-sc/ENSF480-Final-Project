package model;

import java.util.ArrayList;

public class PeriodicalReport {
    private ArrayList<Property> listedInPeriod;
    private ArrayList<Property> rentedInPeriod;
    private int numberOfHousesRented;
    private ArrayList<Property> totalActiveListings;

    PeriodicalReport(ArrayList<Property> listedInPeriod,ArrayList<Property> rentedInPeriod, 
    int numberOfHousesRented,ArrayList<Property> totalActiveListings){
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
    public void setTotalActiveListings( ArrayList<Property> totalActiveListings){
        this.totalActiveListings=totalActiveListings;
    }

    public ArrayList<Property> getTotalActiveListings(){
        return this.totalActiveListings;
    }
}
