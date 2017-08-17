package com.example.a15017395.fyptestapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

import java.util.ArrayList;

public class OutletActivity extends AppCompatActivity {
    Intent intent;
    ArrayAdapter aa;
    ListView lv;
    ArrayList<Outlet> outletList;
    LatLng poi_Singapore = new LatLng(1.362424, 103.802342);
    LatLng current = new LatLng(0.0,0.0);
    SupportMapFragment mapFragment;
    Outlet outlet;
    Boolean check = false;
    AlertDialog dialog;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Action bar stuff
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) { actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.background))); }
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Nearby Outlets </font>"));
        setContentView(R.layout.activity_outlet);

        //Map stuff
        FragmentManager fm = getSupportFragmentManager();
        mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.maps);
        setMapOptions();

        //Listview stuff
        lv = (ListView) findViewById(R.id.lvOutlet);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent newActivity = new Intent(OutletActivity.this, StallActivity.class);
                startActivity(newActivity);

            }
        });
        outletList = (ArrayList<Outlet>) getIntent().getSerializableExtra("outlets");
        setListViewContent(outletList);

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
            menu.findItem(R.id.edit).setVisible(true);
            menu.findItem(R.id.delete).setVisible(true);
            menu.findItem(R.id.add).setVisible(true);
        } else {
            menu.findItem(R.id.edit).setVisible(false);
            menu.findItem(R.id.delete).setVisible(false);
            menu.findItem(R.id.add).setVisible(false);
        }

        return true;
    }


    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            finish();
            overridePendingTransition(R.anim.no_change,R.anim.slide_down_animation);
        } else if (id == R.id.edit){
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
            //Open update outlet
        } else if (id == R.id.delete){
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
            //Delete outlet
        }else if (id == R.id.add){
            addOutletfunc();



        }

        return super.onOptionsItemSelected(item);
    }

    private void setListViewContent(final ArrayList<Outlet> outletList){
        if (outletList!= null){
            aa = new OutletArrayAdapter(this, R.layout.row_outlet, outletList);
            lv.setAdapter(aa);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
                    check = true;
                    outlet = (Outlet) parent.getItemAtPosition(position);
                    if (outletList.get(outlet.getId() - 1) != null) {
                        current = new LatLng(outletList.get(outlet.getId() - 1).getLatitude(), outletList.get(outlet.getId() - 1).getLongitude());

                    setMapMarker(outlet);
                }
                }
            });
        }
    }

    private void setMapMarker(final Outlet outlet){
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.clear();
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 15));

                Marker cp = map.addMarker(new
                        MarkerOptions()
                        .position(current)
                        .title(outletList.get(outlet.getId() - 1).getName())
                        .snippet("Click for more info")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                cp.showInfoWindow();
                map.getUiSettings().setMapToolbarEnabled(true);
            }});
    }

    private void setMapOptions(){
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;


                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore,
                        10));

                map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {

                        intent = new Intent(getApplicationContext(), StallActivity.class);
                        intent.putExtra("com.example.MAIN_MESSAGE", Integer.toString(outlet.getId()));
                        startActivity(intent);
                    }
                });
            }});

    }

    private void addOutletfunc() {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout addOutlet =
                (LinearLayout) inflater.inflate(R.layout.addoutlet, null);

        final EditText etName = (EditText) addOutlet.findViewById(R.id.editTextName);
        final EditText etLocation = (EditText) addOutlet.findViewById(R.id.editTextLocation);

        dialog = new AlertDialog.Builder(this)
                .setView(addOutlet)
                .setTitle("Add Outlet")
                .setNegativeButton("Reset", null)
                .setPositiveButton("Submit", null)
                .create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface dialog) {

                Button btnReset = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                Button btnSubmit = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/addOutlet.php");
                        request.setMethod("POST");
                        request.addData("outlet_name", etName.getText().toString());
                        request.addData("outlet_location", etLocation.getText().toString());
                        request.execute();
                        Toast.makeText(OutletActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
                        lv = (ListView) findViewById(R.id.lvOutlet);
                        lv.invalidateViews();
                        dialog.dismiss();
                        try {
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                btnReset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        etName.setText("");
                        etLocation.setText("");
                    }
                });
            }

        });
        dialog.show();


    }

}
