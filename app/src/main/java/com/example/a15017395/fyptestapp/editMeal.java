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
public class editMeal extends AppCompatActivity {

    private String meal_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal);

    }

    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        meal_id = intent.getStringExtra("com.example.MAIN_MESSAGE");
        HttpRequest request= new HttpRequest("https://night-vibes.000webhostapp.com/getMealDetail.php?meal_id=" + meal_id);
        request.setMethod("GET");
        request.execute();

        try{
            String jsonString = request.getResponse();
            JSONObject jsonObj = new JSONObject(jsonString);
            // TODO 01: Set values in the EditText fields

            EditText mealNameET = (EditText)findViewById(R.id.editTextMealName);
            mealNameET.setText(jsonObj.getString("meal_name"));
            EditText mealPriceET = (EditText)findViewById(R.id.editTextMealPrice);
            mealPriceET.setText(jsonObj.getString("meal_price"));
            EditText mealCategoryET = (EditText)findViewById(R.id.editTextMealCategory);
            mealCategoryET.setText(jsonObj.getString("meal_category"));


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateDetailsButtonClicked(View view){
        EditText mealNameEditText = (EditText)findViewById(R.id.editTextMealName);
        EditText mealPriceEditText = (EditText)findViewById(R.id.editTextMealPrice);
        EditText mealCategoryEditText = (EditText)findViewById(R.id.editTextMealCategory);


        //TODO 03: Send the HttpRequest to updateContact.php
        Toast.makeText(editMeal.this, "Perform TODO task 3", Toast.LENGTH_SHORT).show();

        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/editMeal.php");
        request.setMethod("POST");
        request.addData("meal_name",mealNameEditText.getText().toString());
        request.addData("meal_price",mealPriceEditText.getText().toString());
        request.addData("meal_category",mealCategoryEditText.getText().toString());
        request.addData("meal_id", meal_id);

        request.execute();



        try{

            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteRecordButtonClicked(View view){
        //TODO 04: Send HttpRequest to removeContact.php
        Toast.makeText(editMeal.this, "Perform TODO task 4", Toast.LENGTH_SHORT).show();
        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/deleteMeal.php");
        request.setMethod("POST");


        request.addData("id", meal_id);
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
                    R.layout.activity_edit_meal, container, false);
            return rootView;
        }
    }
    }