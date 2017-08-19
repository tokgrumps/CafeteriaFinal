package com.example.a15017395.fyptestapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class userAdapter extends ArrayAdapter<user> {

    Context context;
    int layoutResourceId;
    ArrayList<user> userList = null;


    public userAdapter(Context context, int resource, ArrayList<user> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.userList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        userHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new userHolder();
            holder.username = (TextView)row.findViewById(R.id.textViewFullName);
            holder.role = (TextView)row.findViewById(R.id.textViewUsername);


            row.setTag(holder);
        }
        else
        {
            holder = (userHolder)row.getTag();
        }

        user User = userList.get(position);
        holder.role.setText(User.getUsername());
        holder.username.setText(User.getFirstName() + " " + User.getLastName());
        // holder.role.setText(User.getRole());
        return row;
    }

    static class userHolder
    {
        TextView username;
        TextView role;
    }





}

