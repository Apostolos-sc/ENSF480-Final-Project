package model;

public class PropertyDetails {
    private String propertyType;
    private int noBathrooms;
    private int noBedrooms;
    private boolean isFurnished;
    private double price;
    private double payment=20;
    private int validPeriod= 2; //days


    public PropertyDetails(String propertyType, int noBathrooms, int noBedrooms, boolean isFurnished, double price) {
        this.isFurnished = isFurnished;
        this.noBathrooms = noBathrooms;
        this.noBedrooms = noBedrooms;
        this.propertyType = propertyType;
        this.price = price;
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
