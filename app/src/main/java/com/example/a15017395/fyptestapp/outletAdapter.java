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

public class outletAdapter  extends ArrayAdapter<outlets> {

    Context context;
    int layoutResourceId;
    ArrayList<outlets> outletList = null;


    public outletAdapter(Context context, int resource, ArrayList<outlets> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.outletList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        outletHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new outletHolder();
            holder.OutletName = (TextView)row.findViewById(R.id.txtOutletName);
            holder.OutletLocation = (TextView)row.findViewById(R.id.txtOutletLocation);
            holder.PostalCode = (TextView)row.findViewById(R.id.txtPostalCode);
            holder.Latitude = (TextView)row.findViewById(R.id.txtLatitude);
            holder.Longitude = (TextView)row.findViewById(R.id.txtLongitude);


            row.setTag(holder);
        }
        else
        {
            holder = (outletHolder)row.getTag();
        }

        outlets outlet = outletList.get(position);
        holder.OutletName.setText(outlet.getoutletName());
        holder.OutletLocation.setText(outlet.getoutletLocation());
        holder.PostalCode.setText(outlet.getoutletPostalCode());
        holder.Latitude.setText(outlet.getoutletLatitude());
        holder.Longitude.setText(outlet.getoutletLongitude());

        return row;
    }

    static class outletHolder
    {
        TextView OutletName;
        TextView OutletLocation;
        TextView PostalCode;
        TextView Latitude;
        TextView Longitude;

    }

}
