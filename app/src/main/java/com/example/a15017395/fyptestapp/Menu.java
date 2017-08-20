package com.example.a15017395.fyptestapp;

/**
 * Created by 15056158 on 18/8/2017.
 */

public class Menu {

    private int menu_id;
    private String menu_category;
    private String menu_promotion;
    private String menu_availability;



    public Menu() {
        super();
    }

    public int getMenu_id() {
        return menu_id;
    }
    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }
    public String getMenu_category() {
        return menu_category;
    }
    public void setMenu_category(String menu_category) {
        this.menu_category = menu_category;
    }
    public String getMenu_promotion() {
        return menu_promotion;
    }
    public void setMenu_promotion(String menu_promotion) {
        this.menu_promotion = menu_promotion;
    }

    public String getMenu_availability() {
        return menu_availability;
    }
    public void setMenu_availability(String menu_availability) {
        this.menu_availability = menu_availability;
    }

    @Override
    public String toString() {
        return getMenu_category() + " " + getMenu_availability()  + " " + getMenu_promotion();
    }
}
