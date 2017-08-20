package com.example.a15017395.fyptestapp;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MealActivity extends AppCompatActivity {


    ListView listView;
    Intent mealintent;
    private int menu_id;
    Intent intent;

    ArrayList<Meal> mealList = new ArrayList<Meal>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);


        Intent intent = getIntent();
        // getting attached intent data

        menu_id = intent.getIntExtra("menu_id",-1);

        // displaying selected product name
        //TODO 6 Declare and create a HttpRequest object, with the URL string as the argument
        String url = "https://night-vibes.000webhostapp.com/getMealList.php?menu_id=" + menu_id;
        //http://localhost/FYPstall/getMenuDetail.php?menu_id=1

        HttpRequest request = new HttpRequest(url);
        request.setMethod("GET");


        try {

            //TODO 7 Execute the Http request
            request.execute();

            //TODO 8 Create PhotoStore object and Parse the returned JSON string values into a PhotoStore object
            String jsonString = request.getResponse();

            System.out.println(">>" + jsonString);
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                Meal meal = new Meal();
                meal.setmealId(Integer.parseInt(jsonObj.getString("meal_id")));
                meal.setmeal_name(jsonObj.getString("meal_name"));
                meal.setmeal_price(jsonObj.getString("meal_price"));
                meal.setmeal_category(jsonObj.getString("meal_category"));
                mealList.add(meal);
            }

            System.out.println(mealList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MealArrayAdapter adapter = new MealArrayAdapter(this, R.layout.row_meal, mealList);
        listView = (ListView) findViewById(R.id.lvMeal);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int arg2, long arg3) {

                Meal meal = (Meal)parent.getItemAtPosition(arg2);

                mealintent = new Intent(getApplicationContext(), editMeal.class);
                mealintent.putExtra("com.example.MAIN_MESSAGE", Integer.toString(meal.getmealId()));
                startActivity(mealintent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
//        final MenuItem searchItem = menu.findItem(R.id.search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        inflater.inflate(R.menu.mymenu, menu);

        SharedPreferences settings = getSharedPreferences("JSON", MODE_PRIVATE);
        settings.getInt("id", 0);
        if (settings.getInt("role_id", 0) == 3){
            menu.findItem(R.id.add).setVisible(true);
        } else {
            menu.findItem(R.id.add).setVisible(false);
        }

        return true;
    }


    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

         if (id == R.id.add){
            intent = new Intent(getApplicationContext(), AddMeal.class);
            startActivity(intent);


        }else if (id == R.id.homepage){
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }




    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_meal, container,
                    false);
            return rootView;
        }
    }
    }

