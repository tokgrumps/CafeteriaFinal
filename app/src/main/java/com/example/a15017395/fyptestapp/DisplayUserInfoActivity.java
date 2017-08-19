package com.example.a15017395.fyptestapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by 15056158 on 19/8/2017.
 */

public class DisplayUserInfoActivity extends AppCompatActivity {

    private String userId;
    private int roleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_info);

        TextView tvFname = (TextView) findViewById(R.id.textViewFname); // First name
        TextView tvLname = (TextView) findViewById(R.id.textViewLname); // Last Name
        TextView tvUsername = (TextView) findViewById(R.id.textViewUsername); // username
        TextView tvMobile = (TextView) findViewById(R.id.textViewMobile); // mobile
        TextView tvEmail = (TextView) findViewById(R.id.textViewEmail); // Email

        Intent intent = getIntent();
        userId = intent.getStringExtra("com.example.MAIN_MESSAGE");
        HttpRequest request= new HttpRequest("https://night-vibes.000webhostapp.com/getUserDetail.php?id=" + userId);
        request.setMethod("GET");
        request.execute();
        try{
            String jsonString = request.getResponse();
            JSONObject jsonObj = new JSONObject(jsonString);
            // TODO 01: Set values in the EditText fields

            tvFname.setText(jsonObj.getString("first_name"));
            tvLname.setText(jsonObj.getString("last_name"));
            tvUsername.setText(jsonObj.getString("username"));
            tvEmail.setText(jsonObj.getString("email"));
            tvMobile.setText(String.valueOf(jsonObj.getInt("contact_number")));

        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDetailsButtonClicked(View view){

        TextView tvFname = (TextView) findViewById(R.id.textViewFname); // First name
        TextView tvLname = (TextView) findViewById(R.id.textViewLname); // Last Name
        TextView tvUsername = (TextView) findViewById(R.id.textViewUsername); // username
        TextView tvMobile = (TextView) findViewById(R.id.textViewMobile); // mobile
        TextView tvEmail = (TextView) findViewById(R.id.textViewEmail); // Email

        RadioGroup rgRole = (RadioGroup) findViewById(R.id.rgRole);
        // Get the Id of the selected radio button in the RadioGroup
        int selectedButtonId = rgRole.getCheckedRadioButtonId();
        // Get the radio button object from the Id we had gotten above
        RadioButton rb = (RadioButton) findViewById(selectedButtonId);
        String role = rb.getText().toString();

        if (role.equalsIgnoreCase("user")){
            roleId = 1;
        }else if (role.equalsIgnoreCase("admin")){
            roleId = 2;
        }else{
            roleId = 3;
        }

        //TODO 03: Send the HttpRequest to DoupdateUser.php
        Toast.makeText(DisplayUserInfoActivity.this, "Perform TODO task 3", Toast.LENGTH_SHORT).show();
        HttpRequest request= new HttpRequest("https://night-vibes.000webhostapp.com/DoupdateUser.php");
        request.setMethod("POST");

        request.addData("id",userId);
        request.addData("role_id",String.valueOf(roleId));
        request.execute();

        Toast.makeText(this, "role assign successful", Toast.LENGTH_LONG).show();
        try{

            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void onStart() {
        super.onStart();


    }

    public void deleteRecordButtonClicked(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure u want to delete this user ?")
                // Set text for the positive button and the corresponding
                //  OnClickListener when it is clicked
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO 04: Send HttpRequest to deleteUser.php
                        HttpRequest request= new HttpRequest("https://night-vibes.000webhostapp.com/deleteUser.php");
                        request.setMethod("POST");

                        request.addData("",userId);
                        request.execute();


                        Toast.makeText(DisplayUserInfoActivity.this, "user delete successful", Toast.LENGTH_LONG).show();

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
}
