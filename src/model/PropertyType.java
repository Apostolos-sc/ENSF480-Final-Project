package model;

public class PropertyType {
    private int propertyTypeID;
    private String propertyType;
    private Double fee;
    private int validDays;

    public PropertyType(int propertyTypeID, String propertyType, Double fee, int validDays) {
        this.propertyTypeID = propertyTypeID;
        this.propertyType = propertyType;
        this.fee = fee;
        this.validDays = validDays;
    }

    public int getPropertyTypeID() {
        return propertyTypeID;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public Double getFee() {
        return fee;
    }

    public int getValidDays() {
        return validDays;
    }

    public void setPropertyTypeID(int propertyTypeID) {
        this.propertyTypeID = propertyTypeID;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public void setValidDays(int validDays) {
        this.validDays = validDays;
    }
}
