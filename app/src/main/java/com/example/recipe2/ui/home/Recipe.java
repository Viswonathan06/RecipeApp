package com.example.recipe2.ui.home;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.recipe2.Recipe.Meals_Recipe;
import com.example.recipe2.Recipe.Recipe_Root;
import com.example.recipe2.Retrofit.JsonPlaceHolderApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipe2.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Recipe extends AppCompatActivity {
    JsonPlaceHolderApi jsonPlaceHolderApi;
    TextView Title;
    TextView DrinkAlternate,Area,Category;
    TextView ingri_1,ingri_2,ingri_3,ingri_4,ingri_5,ingri_6,ingri_7,ingri_8,ingri_9,ingri_10,ingri_11,ingri_12,ingri_13,ingri_14,ingri_15,ingri_16,ingri_17,ingri_18,ingri_19,ingri_20;
    TextView Description;
    String thumbnail,title;
    ImageView finished;
    int clicked=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title=getIntent().getStringExtra("Recipe name");
        thumbnail=getIntent().getStringExtra("Recipe thumbnail");
        getSupportActionBar().setTitle(title);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Title=findViewById(R.id.recipename);
        Description=findViewById(R.id.procedure);
        DrinkAlternate=findViewById(R.id.drinkAlternate);
        Area=findViewById(R.id.area);
        finished=findViewById(R.id.finished);
        Category=findViewById(R.id.category);



        Title.setText(title);


        ingri_1=findViewById(R.id.ingri_1);ingri_2=findViewById(R.id.ingri_2);ingri_2=findViewById(R.id.ingri_2);ingri_3=findViewById(R.id.ingri_3);ingri_4=findViewById(R.id.ingri_4);ingri_5=findViewById(R.id.ingri_5);ingri_6=findViewById(R.id.ingri_6);ingri_7=findViewById(R.id.ingri_7);
        ingri_8=findViewById(R.id.ingri_8);ingri_9=findViewById(R.id.ingri_9);ingri_10=findViewById(R.id.ingri_10);ingri_11=findViewById(R.id.ingri_11);ingri_12=findViewById(R.id.ingri_12);ingri_13=findViewById(R.id.ingri_13);ingri_14=findViewById(R.id.ingri_14);ingri_15=findViewById(R.id.ingri_15);
        ingri_16=findViewById(R.id.ingri_16);ingri_17=findViewById(R.id.ingri_17);ingri_18=findViewById(R.id.ingri_18);ingri_19=findViewById(R.id.ingri_19);ingri_20=findViewById(R.id.ingri_20);


        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<Recipe_Root> call=jsonPlaceHolderApi.getRecipe(title);
        call.enqueue(new Callback<Recipe_Root>() {
                         @Override
                         public void onResponse(Call<Recipe_Root> call, Response<Recipe_Root> response) {
                             if (!response.isSuccessful()) {
                                Title.setText("Code :" + response.code());
                            return;
                               }
                             else{
                                 Recipe_Root root=response.body();
                                 Description.setText(root.getMeals().get(0).getStrInstructions());
                                 Category.setText(root.getMeals().get(0).getStrCategory());
                                 Area.setText(root.getMeals().get(0).getStrArea());
                                 Glide.with(Recipe.this)
                                         .load(root.getMeals().get(0).getStrMealThumb())
                                         .apply(new RequestOptions().override(700,700))
                                         .apply(new RequestOptions().circleCrop())
                                         .apply(new RequestOptions()
                                                 .diskCacheStrategy(DiskCacheStrategy.ALL))
                                         .into(finished);
                                 ingri_1.setText(root.getMeals().get(0).getStrIngredient1()+" - "+root.getMeals().get(0).getStrMeasure1());
                                 ingri_2.setText(root.getMeals().get(0).getStrIngredient2()+" - "+root.getMeals().get(0).getStrMeasure2());
                                 ingri_3.setText(root.getMeals().get(0).getStrIngredient3()+" - "+root.getMeals().get(0).getStrMeasure3());
                                 ingri_4.setText(root.getMeals().get(0).getStrIngredient4()+" - "+root.getMeals().get(0).getStrMeasure4());
                                 ingri_5.setText(root.getMeals().get(0).getStrIngredient5()+" - "+root.getMeals().get(0).getStrMeasure5());
                                 ingri_6.setText(root.getMeals().get(0).getStrIngredient6()+" - "+root.getMeals().get(0).getStrMeasure6());
                                 ingri_7.setText(root.getMeals().get(0).getStrIngredient7()+" - "+root.getMeals().get(0).getStrMeasure7());
                                 ingri_8.setText(root.getMeals().get(0).getStrIngredient8()+" - "+root.getMeals().get(0).getStrMeasure8());
                                 ingri_9.setText(root.getMeals().get(0).getStrIngredient9()+" - "+root.getMeals().get(0).getStrMeasure9());
                                 ingri_10.setText(root.getMeals().get(0).getStrIngredient10()+" - "+root.getMeals().get(0).getStrMeasure10());
                                 ingri_11.setText(root.getMeals().get(0).getStrIngredient11()+" - "+root.getMeals().get(0).getStrMeasure11());
                                 ingri_12.setText(root.getMeals().get(0).getStrIngredient12()+" - "+root.getMeals().get(0).getStrMeasure12());
                                 ingri_13.setText(root.getMeals().get(0).getStrIngredient13()+" - "+root.getMeals().get(0).getStrMeasure13());
                                 ingri_14.setText(root.getMeals().get(0).getStrIngredient14()+" - "+root.getMeals().get(0).getStrMeasure14());
                                 ingri_15.setText(root.getMeals().get(0).getStrIngredient15()+" - "+root.getMeals().get(0).getStrMeasure15());
                                 ingri_16.setText(root.getMeals().get(0).getStrIngredient16()+" - "+root.getMeals().get(0).getStrMeasure16());
                                 ingri_17.setText(root.getMeals().get(0).getStrIngredient17()+" - "+root.getMeals().get(0).getStrMeasure17());
                                 ingri_18.setText(root.getMeals().get(0).getStrIngredient18()+" - "+root.getMeals().get(0).getStrMeasure18());
                                 ingri_19.setText(root.getMeals().get(0).getStrIngredient19()+" - "+root.getMeals().get(0).getStrMeasure19());
                                 ingri_20.setText(root.getMeals().get(0).getStrIngredient20()+" - "+root.getMeals().get(0).getStrMeasure20());


                             }
                         }

                         @Override
                         public void onFailure(Call<Recipe_Root> call, Throwable t) {

                         }
        });


//        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            Callback<Recipe_Root>() {
//                    @Override
//                    public void onResponse(Call<Recipe_Root> call, Response<Recipe_Root> response) {
//                        if (!response.isSuccessful()) {
//                            // textView.setText("Code :" + response.code());
//                            holder.title.setText("Code:"+response.code());
//                            return;
//                        }
//                        else {
//                            Recipe_Root root=response.body();
//                            Meals_Recipe recipe=root.getMeals().get(0);
//
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<Recipe_Root> call, Throwable t) {
//                        holder.title.setText("Code:"+t.getMessage());
//
//                    }
//                });
//            }
//        });


                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(clicked==1){
                            Snackbar.make(view, "removed from favourites", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            fab.setImageDrawable(getResources().getDrawable(R.drawable.heart));
                            clicked=0;
                        }else {
                            clicked=1;
                            Snackbar.make(view, "Added to favourites!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            fab.setImageDrawable(getResources().getDrawable(R.drawable.heart__1_));
                        }

                      }
                });
    }
}
