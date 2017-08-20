package com.example.a15017395.fyptestapp;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class addStall extends AppCompatActivity {


    private TextView textView;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stall);
    }

    protected void onStart(){
        super.onStart();

    }
    public void addNewRecordButtonClicked(View view){
        EditText stallNameEditText = (EditText)findViewById(R.id.editTextStallName);
        EditText stallDetailEditText = (EditText)findViewById(R.id.editTextStallDetail);
        EditText openingHoursEditText = (EditText)findViewById(R.id.editTextOpeningHour);



        //TODO 02: Send the HttpRequest to createNewEntry.php
        Toast.makeText(addStall.this, "Perform TODO task 2", Toast.LENGTH_SHORT).show();


        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/addStall.php");
        request.setMethod("POST");
        request.addData("stall_name",stallNameEditText.getText().toString());
        request.addData("stall_detail",stallDetailEditText.getText().toString());
        request.addData("opening_hours",openingHoursEditText.getText().toString());
        request.execute();


        try{
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(
                    R.layout.activity_add_stall, container, false);
            return rootView;
        }
    }
}
