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

public class StallArrayAdapter extends ArrayAdapter<Stall> {

    Context context;
    int layoutResourceId;
    ArrayList<Stall> stallList = null;


    public StallArrayAdapter(Context context, int resource, ArrayList<Stall> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.stallList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        stallHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new  stallHolder();
            holder.StallName = (TextView)row.findViewById(R.id.txtStallName);
            holder.StallDetail = (TextView)row.findViewById(R.id.txtStallDetail);
            holder.OpeningHours = (TextView)row.findViewById(R.id.txtOpeningHour);

            row.setTag(holder);
        }
        else
        {
            holder = (stallHolder)row.getTag();
        }

        Stall stall = stallList.get(position);
        holder.StallName.setText(stall.getStallName());
        holder.StallDetail.setText(stall.getStallDetail());
        holder.OpeningHours.setText(stall.getOpeningHour());
        return row;
    }

    static class  stallHolder
    {
        TextView StallName;
        TextView StallDetail;
        TextView OpeningHours;

    }
}
