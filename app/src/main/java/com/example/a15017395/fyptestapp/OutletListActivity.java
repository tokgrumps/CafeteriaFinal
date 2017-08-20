package com.example.a15017395.fyptestapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.*;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class OutletListActivity extends AppCompatActivity {

    Intent intent;
    ArrayList<outlets> outletList = new ArrayList<outlets>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet_list);
    }

    public void onResume(){
        super.onResume();
        outletList.clear();
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
                    outlets outlet = new outlets();
                    outlet.setId(jObj.getInt("outlet_id"));
                    outlet.setoutletName(jObj.getString("outlet_name"));
                    outlet.setoutletLocation(jObj.getString("outlet_location"));
                    outlet.setoutletPostalCode(jObj.getString("postalCode"));
                    outlet.setoutletLatitude(jObj.getString("latitude"));
                    outlet.setoutletLongitude(jObj.getString("longitude"));
                    outletList.add(outlet);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            outletAdapter arrayAdapter = new  outletAdapter(this, R.layout.row_outlet_list, outletList);
            listView = (ListView) findViewById(R.id.lvOutletList);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int arg2, long arg3) {

                    outlets outlet = (outlets)parent.getItemAtPosition(arg2);

                    intent = new Intent(getApplicationContext(), editOutlet.class);
                    intent.putExtra("com.example.MAIN_MESSAGE", Integer.toString(outlet.getId()));
                    startActivity(intent);
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
                        OutletListActivity.this.finish();
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
            intent = new Intent(getApplicationContext(), addOutlet.class);
            startActivity(intent);


        }else if (id == R.id.homepage){
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


    }

