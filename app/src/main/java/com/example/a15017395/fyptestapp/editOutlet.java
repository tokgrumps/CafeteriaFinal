package com.example.a15017395.fyptestapp;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

@SuppressLint("NewApi")
public class editOutlet extends AppCompatActivity {

    private String outlet_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_outlet);

    }

    protected void onStart(){
        super.onStart();
        Intent intent = getIntent();
        outlet_id = intent.getStringExtra("com.example.MAIN_MESSAGE");
        HttpRequest request= new HttpRequest("https://night-vibes.000webhostapp.com/getOutletDetail.php?outlet_id=" + outlet_id);
        request.setMethod("GET");
        request.execute();

        try{
            String jsonString = request.getResponse();
            JSONObject jsonObj = new JSONObject(jsonString);
            // TODO 01: Set values in the EditText fields

            EditText outletNameET = (EditText)findViewById(R.id.editTextOutletName);
            outletNameET.setText(jsonObj.getString("outlet_name"));
            EditText outletLocationET = (EditText)findViewById(R.id.editTextOutletLocation);
            outletLocationET.setText(jsonObj.getString("outlet_location"));
            EditText outletPostalCodeET = (EditText)findViewById(R.id.editTextPostalCode);
            outletPostalCodeET.setText(jsonObj.getString("postalCode"));
            EditText outletLatitudeET = (EditText)findViewById(R.id.editTextLatitude);
            outletLatitudeET.setText(jsonObj.getString("latitude"));
            EditText outletLongitudeET = (EditText)findViewById(R.id.editTextLongitude);
            outletLongitudeET.setText(jsonObj.getString("longitude"));




        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDetailsButtonClicked(View view){
        EditText outletNameEditText = (EditText)findViewById(R.id.editTextOutletName);
        EditText outletLocationEditText = (EditText)findViewById(R.id.editTextOutletLocation);
        EditText outletPostalCodeEditText = (EditText)findViewById(R.id.editTextPostalCode);
        EditText outletLatitudeEditText = (EditText)findViewById(R.id.editTextLatitude);
        EditText outletLongitudeEditText = (EditText)findViewById(R.id.editTextLongitude);


        //TODO 03: Send the HttpRequest to updateContact.php
        Toast.makeText(editOutlet.this, "Outlet List Updated", Toast.LENGTH_SHORT).show();

        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/updateOutlet.php");
        request.setMethod("POST");
        request.addData("outlet_name",outletNameEditText.getText().toString());
        request.addData("outlet_location",outletLocationEditText.getText().toString());
        request.addData("postalCode",outletPostalCodeEditText.getText().toString());
        request.addData("latitude",outletLatitudeEditText.getText().toString());
        request.addData("longitude",outletLongitudeEditText.getText().toString());
        request.addData("outlet_id", outlet_id);

        request.execute();



        try{

            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteRecordButtonClicked(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure u want to delete this user ?")
                // Set text for the positive button and the corresponding
                //  OnClickListener when it is clicked
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO 04: Send HttpRequest to deleteUser.php
                        HttpRequest request= new HttpRequest("https://night-vibes.000webhostapp.com/deleteOutlet.php");
                        request.setMethod("POST");

                        request.addData("outlet_id",outlet_id);
                        request.execute();


                        Toast.makeText(editOutlet.this, "outlet delete successful", Toast.LENGTH_LONG).show();

                        try{

                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                // Set text for the negative button and the corresponding
                //  OnClickListener when it is clicked
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(
                    R.layout.activity_edit_outlet, container, false);
            return rootView;
        }
    }
}
