package model;

import java.util.ArrayList;

public class PeriodicalReport {
    private ArrayList<Property> listedInPeriod;
    private ArrayList<Property> rentedInPeriod;
    private int numberOfHousesRented;
    int totalActiveListings;

    public PeriodicalReport(){

    }

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
