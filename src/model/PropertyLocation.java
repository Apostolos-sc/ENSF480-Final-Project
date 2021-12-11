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
