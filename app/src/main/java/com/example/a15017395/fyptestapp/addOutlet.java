package com.example.a15017395.fyptestapp;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class addOutlet extends AppCompatActivity {

    EditText etName, etLocation;
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

    public void addNewRecordButtonClicked(View view) {
        etName = (EditText) findViewById(R.id.editTextName);
        etLocation = (EditText) findViewById(R.id.editTextLocation);


        //TODO 02: Send the HttpRequest to createNewEntry.php
        Toast.makeText(addOutlet.this, "Submitted", Toast.LENGTH_SHORT).show();


        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/addOutlet.php");
        request.setMethod("POST");
        request.addData("outlet_name", etName.getText().toString());
        request.addData("outlet_location", etLocation.getText().toString());
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
                    R.layout.activity_add_outlet, container, false);
            return rootView;
        }
    }
}
