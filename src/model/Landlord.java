package model;

import java.util.ArrayList;

public class Landlord extends User {
    private int landlordID;
    private ArrayList<Property> properties = new ArrayList<Property>();

    public Landlord(int landlordID, String firstName, String lastName, String email, String password, String dob) {
        super(firstName, lastName, email, password, dob);
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