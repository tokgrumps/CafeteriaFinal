package com.example.a15017395.fyptestapp;

import java.io.Serializable;

/**
 * Created by 15056158 on 18/8/2017.
 */

public class Stall{

    private int id;
    private String stallName;
    private String stallDetail;
    private String openingHour;





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




    public String toString(){
        return "stall [stall_id =" + id + ", stall_name =" + stallName +
                ", stall_details =" + stallDetail +  ", opening_hour =" + openingHour +  "]";
    }
}
