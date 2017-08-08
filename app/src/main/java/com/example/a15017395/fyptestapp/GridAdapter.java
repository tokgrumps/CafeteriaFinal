package com.example.a15017395.fyptestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by 15017395 on 4/8/2017.
 */
public class GridAdapter extends BaseAdapter {
        private Context context;
        private final String[] mobileValues;

        public GridAdapter(Context context, String[] mobileValues) {
            this.context = context;
            this.mobileValues = mobileValues;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View gridView;

            if (convertView == null) {

                gridView = new View(context);

                // get layout from mobile.xml
                gridView = inflater.inflate(R.layout.grid, parent, false);




            } else {
                gridView = (View) convertView;
            }


            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(mobileValues[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String mobile = mobileValues[position];
            String url = "https://night-vibes.000webhostapp.com/images/";


                if (mobile.equals("Firebake")) {
                    Picasso.with(context).load(url + "Firebake.jpg").into(imageView);
                } else if (mobile.equals("Birders")) {
                    Picasso.with(context).load(url + "Birders.jpg").into(imageView);
                } else if (mobile.equals("Lewin Terrace")) {
                    Picasso.with(context).load(url + "LewinTerrace.jpg").into(imageView);
                } else if (mobile.equals("The Black Swan")) {
                    Picasso.with(context).load(url + "TheBlackSwan.jpg").into(imageView);
                } else if (mobile.equals("SPRMRKT")) {
                    Picasso.with(context).load(url + "SPRMRKT.jpg").into(imageView);
                } else if (mobile.equals("The Pillar")) {
                    Picasso.with(context).load(url + "ThePillar.jpg").into(imageView);
                } else if (mobile.equals("Kotobuki")) {
                    Picasso.with(context).load(url + "ThePillar.jpg").into(imageView);
                } else if (mobile.equals("Schmear")) {
                    Picasso.with(context).load(url + "Schmear.jpg").into(imageView);
                }
            return gridView;
        }

        @Override
        public int getCount() {
            return mobileValues.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

    }
