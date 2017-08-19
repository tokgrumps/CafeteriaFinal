package com.example.a15017395.fyptestapp;

/**
 * Created by 15056158 on 18/8/2017.
 */

public class Stall {

    private int id;
    private String stallName;
    private String stallDetail;
    private String openingHour;
    private String stallImage;




    public Stall(){
        super();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getStallName() {
        return stallName ;
    }
    public void setStallName(String StallName) {
        this.stallName = stallName;
    }
    public String getStallDetail() {
        return stallDetail;
    }
    public void setStallDetail(String StallDetail) {
        this.stallDetail = stallDetail;
    }
    public String getOpeningHour() {
        return openingHour;
    }
    public void setOpeningHour(String OpeningHour) {
        this.openingHour = openingHour;
    }

    public String getStallImage() {
        return stallImage;
    }
    public void setStallImage(String StallImage) {
        this.stallImage = stallImage;
    }



    public String toString(){
        return getStallName() + " " + getStallDetail() + " " + getOpeningHour() + " " + getStallImage();
    }
}
