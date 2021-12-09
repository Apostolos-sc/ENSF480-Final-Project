package model;

public class Date {
    private int date;
    private int month;
    private int year;

    Date(int date, int month, int year){
        this.date=date;
        this.month=month;
        this.year=year;
    }

    public void setDate(int date){
        this.date=date;
    }
    
    public int getDate(){
        return this.date;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return this.month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return this.year;
    }
}
