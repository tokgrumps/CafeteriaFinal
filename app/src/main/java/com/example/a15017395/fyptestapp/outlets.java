package com.example.a15017395.fyptestapp;

/**
 * Created by 15056158 on 20/8/2017.
 */

public class outlets {

    private int id;
    private String outletName;
    private String outletLocation;
    private String outletPostalCode;
    private String outletLatitude;
    private String outletLongitude;


    public outlets(){
        super();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getoutletName() {
        return outletName ;
    }
    public void setoutletName(String outletName) {
        this.outletName = outletName;
    }
    public String getoutletLocation() {
        return outletLocation;
    }
    public void setoutletLocation(String outletLocation) {
        this.outletLocation = outletLocation;
    }
    public String getoutletPostalCode() {
        return outletPostalCode;
    }

    public void setoutletPostalCode(String outletPostalCode) {
        this.outletPostalCode = outletPostalCode;
    }

    public String getoutletLatitude() {
        return outletLatitude;
    }

    public void setoutletLatitude(String outletLatitude) {
        this.outletLatitude = outletLatitude;
    }

    public String getoutletLongitude() {
        return outletLongitude;
    }

    public void setoutletLongitude(String outletLongitude) {
        this.outletLongitude = outletLongitude;
    }


    public String toString(){
        return getoutletName() + " " + getoutletLocation()  + " " + getoutletPostalCode()  + " " + getoutletLatitude()  + " " + getoutletLongitude();
    }

}
