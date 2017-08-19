package com.example.a15017395.fyptestapp;

/**
 * Created by 15056158 on 18/8/2017.
 */

public class Meal {

    private int meal_id;
    private int menu_id;
    private String meal_name;
    private String meal_price;
    private String meal_category;

    public Meal(){
        super();
    }

    public int getmealId() {
        return  meal_id;
    }
    public void setmealId(int id) {
        this.meal_id = meal_id;
    }


    public int getmenu_Id() {
        return  menu_id;
    }
    public void setmenu_Id(int id) {
        this.menu_id = menu_id;
    }



    public String getmeal_name() {
        return meal_name;
    }
    public void setmeal_name(String  meal_name) {
        this. meal_name =  meal_name;
    }

    public String getmeal_price() {
        return meal_price;
    }
    public void setmeal_price(String  meal_price) {
        this. meal_price =  meal_price;
    }


    public String getmeal_category() {
        return meal_category;
    }
    public void setmeal_category(String meal_category) {
        this.meal_category = meal_category;
    }




    @Override
    public String toString() {
        return "Meal [meal_id =" + meal_id + ", menu_id =" + menu_id + ", meal_name =" + meal_name +
                ", meal_price =" + meal_price +  ", meal_category =" + meal_category +  "]";
    }
}
