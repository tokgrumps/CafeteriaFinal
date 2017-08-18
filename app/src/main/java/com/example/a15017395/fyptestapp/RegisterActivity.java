package com.example.a15017395.fyptestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.robertlevonyan.views.customfloatingactionbutton.CustomFloatingActionButton;
import com.robertlevonyan.views.customfloatingactionbutton.OnFabClickListener;

import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {
    EditText etFname, etLname, etUsername, etPassword, etEmail, etContacts;
    CustomFloatingActionButton fabCreate;
    boolean goodToGo;

    public static void hideSoftKeyboard(Activity activity) {
        final InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            if (activity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_logo, null);
        Button login = (Button) mCustomView.findViewById(R.id.btn_register);
        goodToGo = true;
        login.setText("Login");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        etFname = (EditText) findViewById(R.id.etFname);
        etLname = (EditText) findViewById(R.id.etLname);
        etUsername = (EditText) findViewById(R.id.etUname);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etContacts = (EditText) findViewById(R.id.etContact);
        fabCreate = (CustomFloatingActionButton) findViewById(R.id.fabCreate);

        fabCreate.setOnFabClickListener(new OnFabClickListener() {
            @Override
            public void onFabClick(View v) {
                registerUserButtonClicked(v);
            }
        });

    }

    protected void onStart() {
        super.onStart();
    }

    public void registerUserButtonClicked(View view) {
        goodToGo = true;

        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/registerUser.php");
        request.setMethod("POST");

        String firstName = etFname.getText().toString().trim();
        if (firstName.length() == 0) {
            etFname.setError("Please enter first name");

            goodToGo = false;

        } else if (!firstName.matches("^[A-Za-z\\s]+")) {
            etFname.setError("Invalid First Name");
            goodToGo = false;

        } else {
            request.addData("first_name", etFname.getText().toString());

        }

        String lastName = etLname.getText().toString().trim();
        if (lastName.length() == 0) {
            etLname.setError("Please enter Last name");
            goodToGo = false;

        } else if (!lastName.matches("^[A-Za-z\\s]+")) {
            etLname.setError("Invalid Last Name");
            goodToGo = false;

        } else {
            request.addData("last_name", etLname.getText().toString());
        }

        String username = etUsername.getText().toString().trim();
        if (username.length() == 0) {
            etUsername.setError("please enter your username");
            goodToGo = false;

        } else if (!username.matches("^[A-Za-z0-9\\s]+")) {
            etUsername.setError("invalid username");
            goodToGo = false;

        } else {
            request.addData("username", etUsername.getText().toString());
        }

        String password = etPassword.getText().toString().trim();
        if (password.length() == 0) {

            etPassword.setError("Please enter password");
            goodToGo = false;

        } else if (!password.matches("^[A-Za-z0-9\\s]+") && password.length() < 9 || password.length() > 13) {
            etPassword.setError("Invalid password");
            goodToGo = false;

        } else {
            request.addData("password", etPassword.getText().toString());
        }

        String email = etEmail.getText().toString().trim();
        if (email.length() == 0) {
            etEmail.setError("Please enter your email");
            goodToGo = false;

        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            etEmail.setError("Invalid Email Address");
            goodToGo = false;

        } else {
            request.addData("email", etEmail.getText().toString());
        }

        String contactNo = etContacts.getText().toString().trim();
        if (contactNo.length() == 0) {
            etContacts.setError("please enter contact No");
            goodToGo = false;


        } else if (contactNo.length() < 6 || contactNo.length() > 13) {
            etContacts.setError("invalid contactNo");
            goodToGo = false;

        } else {
            request.addData("contact_number", etContacts.getText().toString());
        }

        //String jsonString = request.getResponse();

        if (goodToGo){
            request.execute();
            try{
                String jsonString = request.getResponse();
//            Log.d(TAG, "jsonString: " + jsonString);
                JSONObject results = new JSONObject(jsonString);
                String status = results.getString("status");
                if(status.equalsIgnoreCase("error") ){
                    Toast.makeText(RegisterActivity.this, "Username exixts !", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                    finish();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{

            Toast.makeText(RegisterActivity.this, "Register unsuccessfull", Toast.LENGTH_LONG).show();
        }
    }
}
