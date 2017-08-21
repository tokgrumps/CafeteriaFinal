package com.example.a15017395.fyptestapp;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

@SuppressLint("NewApi")
public class editMenuActivity extends AppCompatActivity {

    private int menu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

    }

    protected void onStart(){
        super.onStart();
        Intent intent = getIntent();
        menu_id = intent.getIntExtra("menu_id", -1);
        HttpRequest request= new HttpRequest("https://night-vibes.000webhostapp.com/getMenuDetail.php?menu_id=" + menu_id);
        request.setMethod("GET");
        request.execute();

        try{
            String jsonString = request.getResponse();
            System.out.println(jsonString);
            JSONObject jsonObj = new JSONObject(jsonString);
            // TODO 01: Set values in the EditText fields

            EditText menuCategoryET = (EditText)findViewById(R.id.editTextMenuCategory);
            menuCategoryET.setText(jsonObj.getString("menu_category"));
            EditText menuAvailabilityET = (EditText)findViewById(R.id.editTextMenuAvailability);
            menuAvailabilityET.setText(jsonObj.getString("menu_availability"));
            EditText menuPromotionET = (EditText)findViewById(R.id.editTextMenuPromotion);
            menuPromotionET.setText(jsonObj.getString("menu_promotion"));






        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDetailsButtonClicked(View view){
        EditText menuCategory = (EditText)findViewById(R.id.editTextMenuCategory);
        EditText menuPromotion = (EditText)findViewById(R.id.editTextMenuPromotion);
        EditText menuAvailability = (EditText)findViewById(R.id.editTextMenuAvailability);


        //TODO 03: Send the HttpRequest to updateContact.php
        Toast.makeText(editMenuActivity.this, "Menu List Updated", Toast.LENGTH_SHORT).show();

        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/updateMenu.php");
        request.setMethod("POST");
        request.addData("menu_category",menuCategory.getText().toString());
        request.addData("menu_promotion",menuPromotion.getText().toString());
        request.addData("menu_availability",menuAvailability.getText().toString());
        request.addData("menu_id", ""+menu_id);

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
                        HttpRequest request= new HttpRequest("https://night-vibes.000webhostapp.com/deleteMenu.php");
                        request.setMethod("POST");

                        request.addData("menu_id", ""+menu_id);
                        request.execute();


                        Toast.makeText(editMenuActivity.this, "Menu Delete successful", Toast.LENGTH_LONG).show();

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
                    R.layout.activity_edit_menu, container, false);
            return rootView;
        }
    }
}
