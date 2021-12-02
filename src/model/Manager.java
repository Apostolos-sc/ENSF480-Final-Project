package model;

public class Manager extends User {
    private int managerID;

    public Manager(int managerID, String firstName, String lastName, String email, String password, String dob) {
        super(firstName, lastName, email, password, dob);
        this.managerID = managerID;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }
}
