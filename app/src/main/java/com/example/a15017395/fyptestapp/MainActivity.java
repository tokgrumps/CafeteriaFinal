package com.example.a15017395.fyptestapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG_RETAINED_FRAGMENT = "ContactFragment";
    ArrayList<Outlet> outletList = new ArrayList<>();
    Button btnLogin, btnRegister, btnLogout;
    TextView username, role;
    LinearLayout user, visitor;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    Integer roleId = 0;
    ImageView profile;
    private ContactFragment mRetainedFragment;
    NavigationView navigationView;
    Intent intent;

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
            getSupportFragmentManager().beginTransaction().replace(R.id.linearlayout_for_fragment, homeFragment, homeFragment.getTag()).commit();

        } else if (id == R.id.nav_about) {
            item.setChecked(true);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("About");
            }
            AboutFragment aboutFragment = new AboutFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.linearlayout_for_fragment, aboutFragment, aboutFragment.getTag()).commit();

        } else if (id == R.id.nav_contact) {
            item.setChecked(true);
            MyProgressFragment progressFragment = new MyProgressFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.linearlayout_for_fragment, progressFragment, progressFragment.getTag()).commit();

            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if(getSupportActionBar()!=null){getSupportActionBar().setTitle("Contact");}
                    ContactFragment homeFragment = new ContactFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.linearlayout_for_fragment, homeFragment, homeFragment.getTag()).commitAllowingStateLoss();
                }
            };
            handler.postDelayed(runnable, 350);

        } else if (id == R.id.nav_feedback) {
            item.setChecked(true);
            if(getSupportActionBar()!=null){getSupportActionBar().setTitle("Send Feedback");}
            FeedbackFragment feedbackFragment = new FeedbackFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.linearlayout_for_fragment, feedbackFragment, feedbackFragment.getTag()).commit();

        } else if (id == R.id.manage_account) {
            item.setChecked(true);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Manage Account");
            }
            intent = new Intent(getApplicationContext(), UserListActivity.class);
            startActivity(intent);

        }  else if (id == R.id.manage_outlet) {
                item.setChecked(true);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Manage Outlet");
                }
                intent = new Intent(getApplicationContext(), OutletListActivity.class);
                startActivity(intent);

        }else if (id == R.id.manage_stall) {
            item.setChecked(true);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Manage Stall");
            }
            intent = new Intent(getApplicationContext(), StallList.class);
            startActivity(intent);
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
        profile = (ImageView) hView.findViewById(R.id.ivProfile);
        username = (TextView)hView.findViewById(R.id.username);
        role = (TextView)hView.findViewById(R.id.role);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();


        roleId = settings.getInt("role_id", 0);

        if (roleId == 0){
            //visitor
            user.setVisibility(View.GONE);
            visitor.setVisibility(View.VISIBLE);
            nav_Menu.findItem(R.id.manage_account).setVisible(false);
            profile.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.visitor));
        } else {
            //user
            username.setText(settings.getString("username", ""));
            if(roleId == 1){
                role.setText("User");
                nav_Menu.findItem(R.id.manage_account).setVisible(false);
                profile.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.user));
            } else if (roleId == 2){
                role.setText("Admin");
                nav_Menu.findItem(R.id.nav_contact).setVisible(false);
                nav_Menu.findItem(R.id.nav_feedback).setVisible(false);
                nav_Menu.findItem(R.id.manage_account).setVisible(true);
                nav_Menu.findItem(R.id.manage_outlet).setVisible(true);
                nav_Menu.findItem(R.id.manage_stall).setVisible(true);
                profile.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.admin));
            } else if (roleId == 3){
                role.setText("Stall Owner");
                nav_Menu.findItem(R.id.manage_account).setVisible(false);
                nav_Menu.findItem(R.id.manage_outlet).setVisible(false);
                profile.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.stall_owner));
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

        if (btnRegister != null) {
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(i);
                }
            });
        }

        if (btnLogout != null){
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putInt("role_id", 0);
                    editor.putInt("id", 0);
                    navigationView = (NavigationView) findViewById(R.id.nav_view);
                    Menu nav_Menu = navigationView.getMenu();
                    editor.commit();
                    nav_Menu.findItem(R.id.manage_account).setVisible(false);
                    profile.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.visitor));
                    user.setVisibility(View.GONE);
                    visitor.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Logged out",
                            Toast.LENGTH_LONG).show();
                }});}

    }


}