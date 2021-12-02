package controller;

import model.*;

import java.util.ArrayList;
import java.util.Objects;

public class Data {
    private static ArrayList<Renter> renters;
    private static ArrayList<Property> properties;
    private static ArrayList<Landlord> landlords;
    private static ArrayList<Manager> managers;

    public Data(ArrayList<Renter> renters, ArrayList<Property> properties, ArrayList<Landlord> landlords, ArrayList<Manager> managers) {
        setRenters(renters);
        setProperties(properties);
        setLandlords(landlords);
        setManagers(managers);
    }

    public static ArrayList<Renter> getRenters() {
        return renters;
    }

    public static ArrayList<Property> getProperties() {
        return properties;
    }

    public static ArrayList<Landlord> getLandlords() {
        return landlords;
    }

    public static ArrayList<Manager> getManagers() {
        return managers;
    }

    public static void setRenters(ArrayList<Renter> renters) {
        Data.renters = renters;
    }

    public static void setProperties(ArrayList<Property> properties) {
        Data.properties = properties;
    }

    public static void setLandlords(ArrayList<Landlord> landlords) {
        Data.landlords = landlords;
    }

    public static void setManagers(ArrayList<Manager> managers) {
        Data.managers = managers;
    }
}
