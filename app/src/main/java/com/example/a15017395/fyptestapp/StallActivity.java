package com.example.a15017395.fyptestapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class StallActivity extends AppCompatActivity {

    TextView hours, review;
    CardView cardReview;
    ImageView ivStall,dropdown;
    String outletId,reviewResult, openingHours, url, headerTitle;
    LinearLayout two, footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stall);

    }

    protected void onStart(){
        super.onStart();
        setContentView(R.layout.activity_stall);
        setElements();
        loadStalls();
        showCardReview();

        SharedPreferences settings = getSharedPreferences("JSON", MODE_PRIVATE);
        settings.getInt("id", 0);

        if (settings.getInt("role_id", 0) == 0){
            //visitor
            footer.setVisibility(View.GONE);
        } else {
            //user
            footer.setVisibility(View.VISIBLE);
        }

        hours.setText(openingHours);
        review.setText(reviewResult);
        Picasso.with(StallActivity.this).load(url).into(ivStall);
        getSupportActionBar().setTitle(Html.fromHtml(headerTitle));
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
        } else if (id == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.slide_right_animation,R.anim.slide_left_animation);
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadStalls(){
        // Check if there is network access
        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();



        if (networkInfo != null && networkInfo.isConnected()) {
            Intent intent = getIntent();
            outletId = intent.getStringExtra("com.example.MAIN_MESSAGE");
            HttpRequest request= new HttpRequest("https://night-vibes.000webhostapp.com/getStalls.php?outlet_id=" + outletId);
            request.setMethod("GET");
            request.execute();
            try{
                String jsonString = request.getResponse();
                JSONObject jsonObj = new JSONObject(jsonString);
                headerTitle = "<font color='#000000'>" + jsonObj.getString("stall_name") + "</font>";
                openingHours = jsonObj.getString("opening_hour");
                reviewResult = jsonObj.getString("stall_review");
                url = "https://night-vibes.000webhostapp.com/images/" + jsonObj.getString("image") + ".jpg";
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            // AlertBox
            showAlert();
        }
    }

    private void showAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("No network connection!")
                .setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = builder.create();

        // show it
        alertDialog.show();
    }

    private void setElements(){
        //Back button on top left
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.background)));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.back);
        }
        ivStall = (ImageView) findViewById(R.id.ivStall);
        cardReview = (CardView) findViewById(R.id.cardReview);
        dropdown  = (ImageView) findViewById(R.id.ivdropdown);
        review = (TextView) findViewById(R.id.review);
        hours = (TextView) findViewById(R.id.hours);
        two = (LinearLayout) findViewById(R.id.two);
        two.setVisibility(View.GONE);
        footer = (LinearLayout) findViewById(R.id.footer);
        footer.setVisibility(View.GONE);
    }

    private void showCardReview(){
        cardReview.setOnClickListener(new View.OnClickListener() {
            int check = 1; //When check = 1 ,you have your FIRST background set to the button

            @Override
            public void onClick(View v){
                if(check == 1){
                    dropdown.setImageResource(R.drawable.dropup);
                    two.setVisibility(View.VISIBLE);
                    check = 0;
                }else{
                    dropdown.setImageResource(R.drawable.dropdown);
                    review.setText("");
                    two.setVisibility(View.GONE);
                    check = 1;
                }
            }
        });
    }
}
