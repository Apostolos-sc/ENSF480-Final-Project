package model;

public class PropertyLocation {
    private String address;
    private String quadrant;

    public PropertyLocation(String address, String quadrant) {
        this.address = address;
        this.quadrant = quadrant;
    }

    public String getAddress() {
        return address;
    }

    public String getQuadrant() {
        return quadrant;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setQuadrant(String quadrant) {
        this.quadrant = quadrant;
    }
}
