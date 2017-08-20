package com.example.a15017395.fyptestapp;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.robertlevonyan.views.customfloatingactionbutton.OnFabClickListener;

@SuppressLint("NewApi")
public class addOutlet extends AppCompatActivity {


    private TextView textView;
    private String userId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_outlet);


    }

    protected void onStart() {
        super.onStart();

    }

    public void addNewRecordButtonClicked(View view){
        EditText outletNameEditText = (EditText)findViewById(R.id.editTextOutletName);
        EditText outletLocationEditText = (EditText)findViewById(R.id.editTextOutletLocation);
        EditText outletPostalCode = (EditText)findViewById(R.id.editTextPostalCode);
        EditText outletLatitude = (EditText)findViewById(R.id.editTextLatitude);
        EditText outletLongitude = (EditText)findViewById(R.id.editTextLongitude);



        //TODO 02: Send the HttpRequest to createNewEntry.php
        Toast.makeText(addOutlet.this, "Outlet List Added", Toast.LENGTH_SHORT).show();


        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/addOutlets.php");
        request.setMethod("POST");
        request.addData("outlet_name",outletNameEditText.getText().toString());
        request.addData("outlet_location",outletLocationEditText.getText().toString());
        request.addData("postalCode",outletPostalCode.getText().toString());
        request.addData("latitude",outletLatitude.getText().toString());
        request.addData("longitude",outletLongitude.getText().toString());
        request.execute();


        try{
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(
                    R.layout.activity_add_outlet, container, false);
            return rootView;
        }
    }
}
