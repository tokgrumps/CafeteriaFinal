package com.example.a15017395.fyptestapp;

/**
 * Created by 15056158 on 20/8/2017.
 */

public class outletStall {

    private int id;
    private int stall_id;
    private String outletName;
    private String outletLocation;
    private String outletPostalCode;
    private String outletLatitude;
    private String outletLongitude;


    public outletStall(){
        super();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getStall_id() {
        return stall_id;
    }
    public void setStall_id(int stall_id) {
        this.stall_id = stall_id;
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
        return getStall_id()+ " " + getoutletName() + " " + getoutletLocation()  + " " + getoutletPostalCode()  + " " + getoutletLatitude()  + " " + getoutletLongitude();
    }
}
