package com.example.recipe2.AreaWise;

import android.os.Bundle;

import com.example.recipe2.Category_result.Meals;
import com.example.recipe2.Category_result.Meals_Root;
import com.example.recipe2.Category_result.RecycleViewAdapterMeals;
import com.example.recipe2.Retrofit.JsonPlaceHolderApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe2.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Disp_AreaMeals extends AppCompatActivity {
    String country;
    TextView warning, country_name,details;
    private ArrayList<String> nTitles = new ArrayList<>();
    private ArrayList<String> nThumbnails = new ArrayList<>();
    JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp__area_meals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        warning=findViewById(R.id.warning);
        country_name=findViewById(R.id.Country);
        details=findViewById(R.id.Details_country);
        country=getIntent().getStringExtra("Country");
        country_name.setText(country);
        details.setText("Sitting in your comfortable clothes, in front of the TV and with a" +
                " good, home-cooked meal in front of you is the ideal experience.\n \n " +
                "Enjoy your " + country+
                " meal today! ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(country);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<Meals_Root> call=jsonPlaceHolderApi.getAreaMeals(country);
        call.enqueue(new Callback<Meals_Root>() {
            @Override
            public void onResponse(Call<Meals_Root> call, Response<Meals_Root> response) {
                if (!response.isSuccessful()) {
                    warning.setText("Code :" + response.code());
                    return;
                } else {
                    Meals_Root root=response.body();
                    List<Meals> areameals=root.getMeals();
                    for(Meals meal:areameals){
                        nTitles.add(meal.getStrMeal());
                        nThumbnails.add(meal.getStrMealThumb());
                    }
                    RecyclerView recyclerView=findViewById(R.id.RecyclerViewArea1);
                    RecycleViewAdapterMeals adapter=new RecycleViewAdapterMeals(Disp_AreaMeals.this,nTitles,nThumbnails);
                    recyclerView.setAdapter(adapter);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Disp_AreaMeals.this,LinearLayoutManager.HORIZONTAL,false);
                    recyclerView.setLayoutManager(mLayoutManager);
                }
            }

            @Override
            public void onFailure(Call<Meals_Root> call, Throwable t) {
                warning.setText("Code :" + t.getMessage());

            }
        });






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
