package com.example.a15017395.fyptestapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15056158 on 18/8/2017.
 */

public class MealArrayAdapter extends ArrayAdapter<Meal> {

    Context context;
    int layoutResourceId;
    ArrayList<Meal> mealList = null;


    public MealArrayAdapter(Context context, int resource, ArrayList<Meal> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.mealList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MealArrayAdapter.PersonHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new MealArrayAdapter.PersonHolder();
            holder.meal_name = (TextView) row.findViewById(R.id.txtmealName);
            holder.meal_price = (TextView) row.findViewById(R.id.txtmealPrice);
            holder.meal_category = (TextView) row.findViewById(R.id.txtmealCategory);


            row.setTag(holder);
        } else {
            holder = (MealArrayAdapter.PersonHolder) row.getTag();
        }

        Meal meal = mealList.get(position);
        holder.meal_name.setText(meal.getmeal_name());
        holder.meal_price.setText(meal.getmeal_price());
        holder.meal_category.setText(meal.getmeal_category());
        return row;
    }

    static class PersonHolder {
        TextView meal_name;
        TextView meal_price;
        TextView meal_category;


    }
}
