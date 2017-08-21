package com.example.a15017395.fyptestapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class menuMealActivity extends AppCompatActivity {

    Intent intent;
    ArrayList<menuMeal> menuList = new ArrayList<menuMeal>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_meal);
    }

    public void onResume(){
        super.onResume();
        menuList.clear();

        // Check if there is network access
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/getMenu.php");
            request.setMethod("GET");
            request.execute();
            try{
                String jsonString = request.getResponse();
                System.out.println(">>" + jsonString);
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    menuMeal menus = new menuMeal();
                    menus.setMenu_id(Integer.parseInt(jsonObj.getString("menu_id")));
                    menus.setMenu_category(jsonObj.getString("menu_category"));
                    menus.setMenu_promotion(jsonObj.getString("menu_promotion"));
                    menus.setMenu_availability(jsonObj.getString("menu_availability"));
                    menuList.add(menus);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            menuMealAdapter arrayAdapter = new  menuMealAdapter(this, R.layout.row_menu_meal, menuList);
            listView = (ListView) findViewById(R.id.lvMenuMeal);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int arg2, long arg3) {

                    menuMeal menu = (menuMeal)parent.getItemAtPosition(arg2);

                    Intent i = new Intent(getApplicationContext(), MealActivity.class);
                    i.putExtra("menu_id", menu.getMenu_id());
                    startActivity(i);
                }
            });
        } else {
            // AlertBox
            showAlert();
        }
    }

    private void showAlert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("No network connection!")
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        menuMealActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = builder.create();

        // show it
        alertDialog.show();
    }


}
