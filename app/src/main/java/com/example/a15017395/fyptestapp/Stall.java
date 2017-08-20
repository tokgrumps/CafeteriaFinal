package com.example.a15017395.fyptestapp;

import java.io.Serializable;

/**
 * Created by 15056158 on 18/8/2017.
 */

public class Stall{

    private int stall_id;
    private int outlet_id;
    private String stall_name;
    private String stall_details;
    private String opening_hour;





    public Stall(){
        super();
    }

    public int getstall_id() {
        return stall_id;
    }
    public void setstall_id(int stall_id) {
        this.stall_id = stall_id;
    }

    public int getoutlet_id() {
        return outlet_id;
    }
    public void setoutlet_id(int outlet_id) {
        this.outlet_id = outlet_id;
    }

    public String getstall_name() {
        return stall_name ;
    }
    public void setstall_name(String stall_name) {
        this.stall_name = stall_name;
    }
    public String getstall_details() {
        return stall_details;
    }
    public void setstall_details(String stall_details) {
        this.stall_details = stall_details;
    }
    public String getopening_hour() {
        return opening_hour;
    }
    public void setopening_hour(String opening_hour) {
        this.opening_hour = opening_hour;
    }




    public String toString(){
        return "stall [stall_id =" + stall_id + ", outlet_id =" + outlet_id +
                ", stall_name =" +  stall_name +
                ", stall_details =" + stall_details +  ", opening_hour =" + opening_hour +  "]";
    }
}
