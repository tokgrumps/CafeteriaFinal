package com.example.a15017395.fyptestapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.robertlevonyan.views.customfloatingactionbutton.CustomFloatingActionButton;
import com.robertlevonyan.views.customfloatingactionbutton.OnFabClickListener;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText un,pw;
    CustomFloatingActionButton login;
    TextView result;
    String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        un = (EditText) findViewById(R.id.input_username);
        pw = (EditText) findViewById(R.id.input_password);
        login = (CustomFloatingActionButton) findViewById(R.id.btnLogin);
        result = (TextView) findViewById(R.id.result);
        getSupportActionBar().setTitle("Login");
        setLogin();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.no_change,R.anim.slide_down_animation);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setLogin(){
        login.setOnFabClickListener(new OnFabClickListener() {
            @Override
            public void onFabClick(View v) {
                username = un.getText().toString();
                password = pw.getText().toString();
                String url = "https://night-vibes.000webhostapp.com/doLogin.php";
                HttpRequest request = new HttpRequest(url);
                request.setMethod("POST");
                request.addData("username", username);
                request.addData("password", password);
                request.execute();
                Toast.makeText(LoginActivity.this, "Logged in as " + username,
                        Toast.LENGTH_LONG).show();

                try {
                    String jsonString = request.getResponse();
                    JSONObject jsonObject = new JSONObject(jsonString);
                    Log.i("message",jsonString);
                    System.out.println(">>" + jsonString);
                    if (jsonObject.getBoolean("authenticated")){

                        SharedPreferences settings = getSharedPreferences("JSON", MODE_PRIVATE);

                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("username", username);
                        editor.putInt("id", jsonObject.getInt("id"));
                        editor.putInt("role_id", jsonObject.getInt("role_id"));
                        editor.apply();


                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                    } else {
                        result.setText("Please try again");
                    }

                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}