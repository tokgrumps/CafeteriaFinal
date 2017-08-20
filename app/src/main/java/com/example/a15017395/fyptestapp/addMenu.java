package com.example.a15017395.fyptestapp;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class addMenu extends AppCompatActivity {

    private TextView textView;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
    }

    protected void onStart(){
        super.onStart();

    }
    public void addNewRecordButtonClicked(View view){
        EditText menuCategory = (EditText)findViewById(R.id.editTextMenuCategory);
        EditText menuPromotion = (EditText)findViewById(R.id.editTextMenuPromotion);
        EditText menuAvailability = (EditText)findViewById(R.id.editTextMenuAvailability);


        //TODO 02: Send the HttpRequest to createNewEntry.php
        Toast.makeText(addMenu.this, "Menu Added Successfully", Toast.LENGTH_SHORT).show();


        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/addMenu.php");
        request.setMethod("POST");
        request.addData("menu_category",menuCategory.getText().toString());
        request.addData("menu_promotion",menuPromotion.getText().toString());
        request.addData("menu_availability",menuAvailability.getText().toString());
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
                    R.layout.activity_add_menu, container, false);
            return rootView;
        }
    }
}
