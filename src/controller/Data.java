package controller;

import model.*;

import java.util.ArrayList;

public class Data {
    private ArrayList<Renter> renters;
    private ArrayList<Property> properties;
    private ArrayList<Landlord> landlords;
    private ArrayList<Manager> managers;


    public Data() {
        renters = new ArrayList<Renter>();
        properties = new ArrayList<Property>();
        landlords = new ArrayList<Landlord>();
        managers = new ArrayList<Manager>();
    }

    public Data(ArrayList<Renter> renters, ArrayList<Property> properties, ArrayList<Landlord> landlords, ArrayList<Manager> managers) {
        this.renters = renters;
        this.properties = properties;
        this.landlords = landlords;
        this.managers = managers;
    }

    public ArrayList<Renter> getRenters() {
        return renters;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public ArrayList<Landlord> getLandlords() {
        return landlords;
    }

    public ArrayList<Manager> getManagers() {
        return managers;
    }

    public void setRenters(ArrayList<Renter> renters) {
        this.renters = renters;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public void setLandlords(ArrayList<Landlord> landlords) {
        this.landlords = landlords;
    }

    public void setManagers(ArrayList<Manager> managers) {
        this.managers = managers;
    }
}
