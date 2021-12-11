/** 
@author Dave Sharma <a href="mailto:dave.sharma@ucalgary.ca">agampreet.aulakh@ucalgary.ca </a>
Nuha Shaikh <a href="mailto:nuha.shaikh1@ucalgary.ca">nuha.shaikh1@ucalgary.ca</a>
Apostolos Scondrianis <a href="mailto:apostolos.scondriani@ucalgary.ca">huda.abbas@ucalgary.ca</a>
Harsh Sharma <a href= "mailto:harshit.sharma@ucalgary.ca">melanie.nguyen@ucalgary.ca</a>
@version 2.2
@since  2.0
  * Data.java (insert explanation of class)
*/
package model;

public class Property {
    private int propertyID;
    private PropertyDetails propertyDetails;
    private PropertyLocation propertyLocation;
    private String status;

    public Property(int propertyID, String propertyType, int noBathrooms, int noBedrooms,
                    boolean isFurnished, String address, String quadrant,String status, double price) {
        this.propertyID = propertyID;
        this.propertyDetails = new PropertyDetails(propertyType, noBathrooms, noBedrooms, isFurnished, price);
        this.propertyLocation = new PropertyLocation(address, quadrant);
        this.status = status;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public PropertyDetails getPropertyDetails() {
        return propertyDetails;
    }

    public PropertyLocation getPropertyLocation() {
        return propertyLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public void setPropertyDetails(PropertyDetails propertyDetails) {
        this.propertyDetails = propertyDetails;
    }

    public void setPropertyLocation(PropertyLocation propertyLocation) {
        this.propertyLocation = propertyLocation;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
