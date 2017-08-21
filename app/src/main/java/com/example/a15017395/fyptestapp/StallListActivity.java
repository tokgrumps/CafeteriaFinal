package com.example.a15017395.fyptestapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class StallListActivity extends AppCompatActivity {

    Intent intent;
    ArrayList<outletStall> outletStallList = new ArrayList<outletStall>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stall_list2);

    }

    public void onResume(){
        super.onResume();
        outletStallList.clear();
        // Check if there is network access
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/getOutlets.php");
            request.setMethod("GET");
            request.execute();
            try{
                String jsonString = request.getResponse();
                System.out.println(">>" + jsonString);

                JSONArray jsonArray = new JSONArray(jsonString);

                // Populate the arraylist personList
                for(int i=0 ; i < jsonArray.length(); i++){
                    JSONObject jObj = jsonArray.getJSONObject(i);
                    System.out.println(jObj.getString("outlet_name") + " " + jObj.getString("outlet_location"));
                    outletStall outlet = new outletStall();
                    outlet.setId(jObj.getInt("outlet_id"));
                    outlet.setoutletName(jObj.getString("outlet_name"));
                    outlet.setoutletLocation(jObj.getString("outlet_location"));
                    outlet.setoutletPostalCode(jObj.getString("postalCode"));
                    outlet.setoutletLatitude(jObj.getString("latitude"));
                    outlet.setoutletLongitude(jObj.getString("longitude"));
                    outletStallList.add(outlet);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            outletStallAdapter arrayAdapter = new  outletStallAdapter(this, R.layout.row_stall_list, outletStallList);
            listView = (ListView) findViewById(R.id.lvOutletStallList);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int arg2, long arg3) {

                    outletStall outlet = (outletStall)parent.getItemAtPosition(arg2);

                    Intent i = new Intent(getApplicationContext(), StallsActivity.class);
                    i.putExtra("outlet_id", outlet.getId());
                    startActivity(i);
                }
            });
        } else {
            // AlertBox
            showAlert();
        }
    }

    private void showAlert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("No network connection!")
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        StallListActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = builder.create();

        // show it
        alertDialog.show();
    }


}
