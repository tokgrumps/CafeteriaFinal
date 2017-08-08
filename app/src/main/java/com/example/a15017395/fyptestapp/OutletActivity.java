package com.example.a15017395.fyptestapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    private GoogleMap map;
    ArrayList<Outlet> outletList;
    LatLng poi_Singapore = new LatLng(1.362424, 103.802342);
    LatLng current = new LatLng(0.0,0.0);
    SupportMapFragment mapFragment;
    Outlet outlet;


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
        outletList = (ArrayList<Outlet>) getIntent().getSerializableExtra("outlets");
        setListViewContent(outletList);

}


    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();

//        final MenuItem searchItem = menu.findItem(R.id.search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        inflater.inflate(R.menu.search_menu, menu);
        inflater.inflate(R.menu.mymenu, menu);

        return true;
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            finish();
            overridePendingTransition(R.anim.no_change,R.anim.slide_down_animation);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setListViewContent(final ArrayList<Outlet> outletList){
        if (outletList!= null){
            aa = new OutletArrayAdapter(this, R.layout.row_outlet, outletList);
            lv.setAdapter(aa);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int arg2, long arg3) {
                    outlet = (Outlet) parent.getItemAtPosition(arg2);
                    current = new LatLng(outletList.get(outlet.getId() - 1).getLatitude(), outletList.get(outlet.getId() - 1).getLongitude());
                    setMapMarker(outlet);
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

}
