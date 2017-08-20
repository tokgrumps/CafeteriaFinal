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
 * Created by 15056158 on 20/8/2017.
 */

public class menuMealAdapter extends ArrayAdapter<menuMeal> {
    Context context;
    int layoutResourceId;
    ArrayList<menuMeal> menuList = null;


    public menuMealAdapter(Context context, int resource, ArrayList<menuMeal> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.menuList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        menuMealAdapter.PersonHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new menuMealAdapter.PersonHolder();
            holder.menuCategory = (TextView) row.findViewById(R.id.txtCategory);
            holder.menuPromotion = (TextView) row.findViewById(R.id.txtPromotion);
            holder.menuAvailablity = (TextView) row.findViewById(R.id.txtAvailability);


            row.setTag(holder);
        } else {
            holder = (menuMealAdapter.PersonHolder) row.getTag();
        }

        menuMeal menu = menuList.get(position);
        holder.menuCategory.setText(menu.getMenu_category());
        holder.menuPromotion.setText(menu.getMenu_promotion());
        holder.menuAvailablity.setText(menu.getMenu_availability());
        return row;
    }

    static class PersonHolder {
        TextView menuCategory;
        TextView menuPromotion;
        TextView menuAvailablity;
    }
}
