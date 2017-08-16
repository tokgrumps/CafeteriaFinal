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


public class RegisterActivity extends AppCompatActivity {
    EditText etFname, etLname, etUsername, etPassword, etEmail, etContacts;
    CustomFloatingActionButton fabCreate;

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

        final String email = etEmail.getText().toString().trim();
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

//        etEmail.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                if (email.matches(emailPattern) && s.length() > 0) {
//                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
//                    // or
//                    etEmail.setText("valid email");
//                } else {
//                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
//                    //or
//                    etEmail.setText("invalid email");
//                }
//            }
//
//
//        });

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

        // Log.d(TAG, "registerUserButtonClicked()...");


        HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/registerUser.php");
        request.setMethod("POST");

        if (etFname.getText().toString().length() == 0) {
            etFname.setError("First name have not been entered");
            etFname.requestFocus();

        } else if (etFname.getText().toString().length() <= 2) {
            etFname.setError("First name must have at least 3 characters");
            etFname.requestFocus();

        } else {
            request.addData("first_name", etFname.getText().toString());
        }

        if (etLname.getText().toString().length() == 0) {
            etLname.setError("Last Name have not been entered");
            etLname.requestFocus();

        } else if (etLname.getText().toString().length() <= 2) {
            etLname.setError("Last Name must have at least 3 characters");
            etLname.requestFocus();

        } else {
            request.addData("last_name", etLname.getText().toString());
        }


        request.addData("username", etUsername.getText().toString());

        request.addData("password", etPassword.getText().toString());

        request.addData("email", etEmail.getText().toString());
        request.addData("contact_number", etContacts.getText().toString());


        //if
        //sdfghjk
        request.execute();

        Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show();

        try {
//            String jsonString = request.getResponse();
//            Log.d(TAG, "jsonString: " + jsonString);

            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Toast.makeText(this, "Please fill in all the empty fields", Toast.LENGTH_LONG).show();
    }
}
