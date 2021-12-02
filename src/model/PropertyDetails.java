package model;

public class PropertyDetails {
    private String propertyType;
    private int noBathrooms;
    private int noBedrooms;
    private boolean isFurnished;

    public PropertyDetails(String propertyType, int noBathrooms, int noBedrooms, boolean isFurnished) {
        this.isFurnished = isFurnished;
        this.noBathrooms = noBathrooms;
        this.noBedrooms = noBedrooms;
        this.propertyType = propertyType;
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
}
