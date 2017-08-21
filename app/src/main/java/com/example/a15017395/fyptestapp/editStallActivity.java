package com.example.a15017395.fyptestapp;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

@SuppressLint("NewApi")
public class editStallActivity extends AppCompatActivity {

    private String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stall);


    }

    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Id = intent.getStringExtra("stall_id");
        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/getOutletDetail.php?Id=" + Id);
        request.setMethod("GET");
        request.execute();

        try {
            String jsonString = request.getResponse();
            JSONObject jsonObj = new JSONObject(jsonString);
            // TODO 01: Set values in the EditText fields

            EditText stallNameET = (EditText) findViewById(R.id.editTextStallName);
            stallNameET.setText(jsonObj.getString("stall_name"));
            EditText stallDetailET = (EditText) findViewById(R.id.editTextStallDetail);
            stallDetailET.setText(jsonObj.getString("stall_detail"));
            EditText openingHoursET = (EditText) findViewById(R.id.editTextOpeningHour);
            openingHoursET.setText(jsonObj.getString("opening_hours"));




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDetailsButtonClicked(View view) {
        EditText stallNameET = (EditText) findViewById(R.id.editTextStallName);
        EditText stallDetailET = (EditText) findViewById(R.id.editTextStallDetail);
        EditText openingHoursET = (EditText) findViewById(R.id.editTextOpeningHour);


        //TODO 03: Send the HttpRequest to updateContact.php
        Toast.makeText(editStallActivity.this, "Perform TODO task 3", Toast.LENGTH_SHORT).show();

        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/updateStall.php");
        request.setMethod("POST");
        request.addData("stall_name", stallNameET.getText().toString());
        request.addData("stall_detail", stallDetailET.getText().toString());
        request.addData("opening_hours", openingHoursET.getText().toString());
        request.addData("stall_id", Id);

        request.execute();


        try {

            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteRecordButtonClicked(View view) {
        //TODO 04: Send HttpRequest to removeContact.php
        Toast.makeText(editStallActivity.this, "Perform TODO task 4", Toast.LENGTH_SHORT).show();
        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/deleteStall.php");
        request.setMethod("POST");


        request.addData("stall_id", Id);
        request.execute();


        try {

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
                    R.layout.activity_edit_stall, container, false);
            return rootView;
        }

    }
}
