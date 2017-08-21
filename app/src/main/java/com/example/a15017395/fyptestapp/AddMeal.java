package com.example.a15017395.fyptestapp;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class AddMeal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);


    }

    protected void onStart() {
        super.onStart();

    }

    public void addNewRecordButtonClicked(View view) {
        EditText mealName = (EditText) findViewById(R.id.editTextMealName);
        EditText mealPrice = (EditText) findViewById(R.id.editTextMealPrice);
        EditText mealCategory = (EditText) findViewById(R.id.editTextMealCategory);


        //TODO 02: Send the HttpRequest to createNewEntry.php
        Toast.makeText(AddMeal.this, "Perform TODO task 2", Toast.LENGTH_SHORT).show();


        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/addMeal.php");
        request.setMethod("POST");
        request.addData("meal_name", mealName.getText().toString());
        request.addData("meal_price", mealPrice.getText().toString());
        request.addData("meal_category", mealCategory.getText().toString());
        request.execute();


        try {
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
                    R.layout.activity_add_meal, container, false);
            return rootView;
        }
    }

}