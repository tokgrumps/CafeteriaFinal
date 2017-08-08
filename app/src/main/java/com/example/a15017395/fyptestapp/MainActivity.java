package com.example.a15017395.fyptestapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG_RETAINED_FRAGMENT = "ContactFragment";
    ArrayList<Outlet> outletList = new ArrayList<>();

    private ContactFragment mRetainedFragment;
    Button btnLogin, btnRegister, btnLogout;
    TextView username, role;
    LinearLayout user, visitor;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    Integer roleId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences("JSON", MODE_PRIVATE);

        setElements();
        setButtonClick();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            item.setChecked(true);
            if(getSupportActionBar()!=null){getSupportActionBar().setTitle("Home");}
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.linearlayout_for_fragment,
                    homeFragment,
                    homeFragment.getTag())
                    .commit();


        } else if (id == R.id.nav_about) {
            if(getSupportActionBar()!=null){getSupportActionBar().setTitle("About");}
            item.setChecked(true);
            AboutFragment aboutFragment = new AboutFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.linearlayout_for_fragment,
                    aboutFragment,
                    aboutFragment.getTag())
                    .commit();


        } else if (id == R.id.nav_contact) {
            MyProgressFragment progressFragment = new MyProgressFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.linearlayout_for_fragment,
                    progressFragment,
                    progressFragment.getTag())
                    .commit();


            item.setChecked(true);
            Handler handler = new Handler();
            Runnable runnable = new Runnable() {

                @Override
                public void run() {
                    if(getSupportActionBar()!=null){getSupportActionBar().setTitle("Contact");}
                    ContactFragment homeFragment = new ContactFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.linearlayout_for_fragment,
                            homeFragment,
                            homeFragment.getTag())
                            .commitAllowingStateLoss();

                }
            };

            handler.postDelayed(runnable, 350);




        } else if (id == R.id.nav_feedback) {
            item.setChecked(true);
            if(getSupportActionBar()!=null){getSupportActionBar().setTitle("Send Feedback");}
            FeedbackFragment feedbackFragment = new FeedbackFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.linearlayout_for_fragment,
                    feedbackFragment,
                    feedbackFragment.getTag())
                    .commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setElements(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //listen for navigation events
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // select the correct nav menu item
        //Drawer stuff
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (getSupportActionBar()!= null){ getSupportActionBar().setTitle("Home");}
        HomeFragment homeFragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.linearlayout_for_fragment,
                homeFragment,
                homeFragment.getTag())
                .commit();

        View hView =  navigationView.getHeaderView(0);
        btnLogin = (Button)hView.findViewById(R.id.btnLogin);
        btnRegister = (Button)hView.findViewById(R.id.btnRegister);
        btnLogout = (Button)hView.findViewById(R.id.btnLogout);
        user = (LinearLayout)hView.findViewById(R.id.user);
        visitor = (LinearLayout)hView.findViewById(R.id.visitor);
        username = (TextView)hView.findViewById(R.id.username);
        role = (TextView)hView.findViewById(R.id.role);

        roleId = settings.getInt("role_id", 0);

        if (roleId == 0){
            //visitor
            user.setVisibility(View.GONE);
            visitor.setVisibility(View.VISIBLE);
        } else {
            //user
            username.setText(settings.getString("username", ""));
            if(roleId == 1){
                role.setText("User");
            } else if (roleId == 2){
                role.setText("Admin");
            } else if (roleId == 3){
                role.setText("Stall Owner");
            }

            user.setVisibility(View.VISIBLE);
            visitor.setVisibility(View.GONE);
        }

    }

    public void setButtonClick(){
        editor = settings.edit();

        if (btnLogin != null){
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i); }});}

        if (btnLogout != null){
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("apikey", "0");
                    editor.putInt("id", 0);
                    editor.commit();
                    user.setVisibility(View.GONE);
                    visitor.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Logged out",
                            Toast.LENGTH_LONG).show();
                }});}
    }


}