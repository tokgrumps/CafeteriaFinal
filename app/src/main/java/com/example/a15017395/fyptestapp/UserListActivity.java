package com.example.a15017395.fyptestapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 15056158 on 19/8/2017.
 */



public class UserListActivity extends AppCompatActivity {

    Intent intent;
    ArrayList<user> userList = new ArrayList<user>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_user_list);

    }

    public void onResume() {
        super.onResume();
        userList.clear();
        // Check if there is network access
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            HttpRequest request = new HttpRequest("https://night-vibes.000webhostapp.com/getUser.php");
            request.setMethod("GET");
            request.execute();
            try {
                String jsonString = request.getResponse();
                System.out.println(">>" + jsonString);

                JSONArray jsonArray = new JSONArray(jsonString);

                // Populate the arraylist personList
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jObj = jsonArray.getJSONObject(i);
                    System.out.println(jObj.getString("username"));
                    user User = new user();
                    User.setId(jObj.getInt("id"));
                    User.setFirstName(jObj.getString("first_name"));
                    User.setLastName(jObj.getString("last_name"));
                    User.setUsername(jObj.getString("username"));
                    User.setEmail(jObj.getString("email"));
                    User.setContactNo(jObj.getInt("contact_number"));
                    User.setRoleID(jObj.getInt("role_id"));
                    userList.add(User);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            userAdapter arrayAdapter = new userAdapter(this, R.layout.row_userlist, userList);
            listView = (ListView) findViewById(R.id.lvUser);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int arg2, long arg3) {

                    user User = (user) parent.getItemAtPosition(arg2);

                    intent = new Intent(getApplicationContext(), DisplayUserInfoActivity.class);
                    intent.putExtra("com.example.MAIN_MESSAGE", Integer.toString(User.getId()));
                    startActivity(intent);
                }
            });
        } else {
            // AlertBox
            showAlert();
        }
    }

    private void showAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("No network connection!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        UserListActivity.this.finish();
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