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

public class outletStallAdapter extends ArrayAdapter<outletStall> {
    Context context;
    int layoutResourceId;
    ArrayList<outletStall> outletStallList = null;


    public outletStallAdapter(Context context, int resource, ArrayList<outletStall> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.outletStallList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        outletStallAdapter.outletHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new outletStallAdapter.outletHolder();
            holder.OutletName = (TextView) row.findViewById(R.id.txtOutletName);
            holder.OutletLocation = (TextView) row.findViewById(R.id.txtOutletLocation);
            holder.PostalCode = (TextView) row.findViewById(R.id.txtPostalCode);
            holder.Latitude = (TextView) row.findViewById(R.id.txtLatitude);
            holder.Longitude = (TextView) row.findViewById(R.id.txtLongitude);


            row.setTag(holder);
        } else {
            holder = (outletStallAdapter.outletHolder) row.getTag();
        }

        outletStall outlet = outletStallList.get(position);
        holder.OutletName.setText(outlet.getoutletName());
        holder.OutletLocation.setText(outlet.getoutletLocation());
        holder.PostalCode.setText(outlet.getoutletPostalCode());
        holder.Latitude.setText(outlet.getoutletLatitude());
        holder.Longitude.setText(outlet.getoutletLongitude());

        return row;
    }

    static class outletHolder {
        TextView OutletName;
        TextView OutletLocation;
        TextView PostalCode;
        TextView Latitude;
        TextView Longitude;

    }
}