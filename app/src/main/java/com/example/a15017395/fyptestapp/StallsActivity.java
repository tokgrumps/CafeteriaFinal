package com.example.a15017395.fyptestapp;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class StallsActivity extends AppCompatActivity {

    ListView listView;
    Intent stallintent;
    private int outlet_id;
    StallArrayAdapter adapter;


    ArrayList<Stall> stallList = new ArrayList<Stall>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stalls);



        Intent intent = getIntent();
        // getting attached intent data
        outlet_id = intent.getIntExtra("outlet_id", -1);

        // displaying selected product name
        //TODO 6 Declare and create a HttpRequest object, with the URL string as the argument
        String url = "https://night-vibes.000webhostapp.com/getStallDetail.php?outlet_id="+ outlet_id;


        HttpRequest request = new HttpRequest(url);
        request.setMethod("GET");


        try {

            //TODO 7 Execute the Http request
            request.execute();

            //TODO 8 Create PhotoStore object and Parse the returned JSON string values into a PhotoStore object
            String jsonString = request.getResponse();

            System.out.println(">>" + jsonString);
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                Stall stall = new Stall();
                stall.setstall_id(Integer.parseInt(jsonObj.getString("stall_id")));
                stall.setstall_name(jsonObj.getString("stall_name"));
                stall.setstall_details(jsonObj.getString("stall_details"));
                stall.setopening_hour(jsonObj.getString("opening_hour"));
                stallList.add(stall);
                adapter.notifyDataSetChanged();

            }

            System.out.println(stallList);


        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter = new StallArrayAdapter(this, R.layout.row_stall, stallList);
        listView = (ListView) findViewById(R.id.lvStall);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int arg2, long arg3) {

                Stall stall = (Stall)parent.getItemAtPosition(arg2);

                stallintent = new Intent(getApplicationContext(), editStallActivity.class);
                stallintent.putExtra("com.example.MAIN_MESSAGE", Integer.toString(stall.getstall_id()));
                startActivity(stallintent);
            }
        });


    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
//        final MenuItem searchItem = menu.findItem(R.id.search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        inflater.inflate(R.menu.mymenu, menu);

        SharedPreferences settings = getSharedPreferences("JSON", MODE_PRIVATE);
        settings.getInt("id", 0);
        if (settings.getInt("role_id", 0) == 2){
            menu.findItem(R.id.add).setVisible(true);
        } else {
            menu.findItem(R.id.add).setVisible(false);
        }

        return true;
    }


    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

         if (id == R.id.add){
            stallintent = new Intent(getApplicationContext(), addStall.class);
            startActivity(stallintent);


        }else if (id == R.id.homepage){
            stallintent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(stallintent);
        }

        return super.onOptionsItemSelected(item);
    }




    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_stalls, container,
                    false);
            return rootView;
        }
    }
}
