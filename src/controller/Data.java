package controller;

import model.*;

import java.util.ArrayList;

public class Data {
    private ArrayList<Renter> renters;
    private ArrayList<Property> properties;
    private ArrayList<Landlord> landlords;
    private ArrayList<Manager> managers;
    private ArrayList<Contract> contracts;
    private ArrayList<PropertyType> propertyTypes;


    public Data() {
        renters = new ArrayList<Renter>();
        properties = new ArrayList<Property>();
        landlords = new ArrayList<Landlord>();
        managers = new ArrayList<Manager>();
        contracts = new ArrayList<Contract>();
        propertyTypes = new ArrayList<PropertyType>();
    }

    public Data(ArrayList<Renter> renters, ArrayList<Property> properties, ArrayList<Landlord> landlords, ArrayList<Manager> managers, ArrayList<Contract> contracts, ArrayList<PropertyType> propertyTypes) {
        this.renters = renters;
        this.properties = properties;
        this.landlords = landlords;
        this.managers = managers;
        this.contracts = contracts;
        this.propertyTypes = propertyTypes;
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

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public ArrayList<PropertyType> getPropertyTypes() {
        return propertyTypes;
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

    public void setContracts(ArrayList<model.Contract> contracts) {
        this.contracts = contracts;
    }

    public void setPropertyTypes(ArrayList<PropertyType> propertyTypes) {
        this.propertyTypes = propertyTypes;
    }
}
