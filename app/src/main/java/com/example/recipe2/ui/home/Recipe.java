package com.example.recipe2.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.recipe2.Recipe.Meals_Recipe;
import com.example.recipe2.Recipe.Recipe_Root;
import com.example.recipe2.Retrofit.JsonPlaceHolderApi;
import com.example.recipe2.SQLite.DataBaseHelper;
import com.example.recipe2.SQLite.FavouriteModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

public class Recipe extends AppCompatActivity {
    JsonPlaceHolderApi jsonPlaceHolderApi;
    TextView Title;
    ProgressBar loading4;
    LinearLayout LAYOUT;
    TextView DrinkAlternate,Area,Category;
    TextView ingri_1,ingri_2,ingri_3,ingri_4,ingri_5,ingri_6,ingri_7,ingri_8,ingri_9,ingri_10,ingri_11,ingri_12,ingri_13,ingri_14,ingri_15,ingri_16,ingri_17,ingri_18,ingri_19,ingri_20;
    String ingridient_1,ingridient_2,ingridient_3,ingridient_4,ingridient_5,ingridient_6,ingridient_7,ingridient_8,ingridient_9,ingridient_10,ingridient_11,ingridient_12,ingridient_13,ingridient_14,ingridient_15,ingridient_16,ingridient_17,ingridient_18,ingridient_19,ingridient_20;
    ImageButton playButton;
    ArrayList<String> nCategory = new ArrayList<>();
    ArrayList<String> nDetails = new ArrayList<>();
    ArrayList<String> nThumbnail = new ArrayList<>();

    TextView Description;
    String thumbnail,title;
    ImageView finished;
    int clicked=0,id;

    FavouriteModel favouriteModelDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title=getIntent().getStringExtra("Recipe name");
        thumbnail=getIntent().getStringExtra("Recipe thumbnail");
        getSupportActionBar().setTitle(title);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        List<FavouriteModel> favs = dataBaseHelper.getRecipes();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        Title=findViewById(R.id.recipename);
        Description=findViewById(R.id.procedure);
        DrinkAlternate=findViewById(R.id.drinkAlternate);
        Area=findViewById(R.id.area);
        finished=findViewById(R.id.finished);
        Category=findViewById(R.id.category);
        loading4=findViewById(R.id.loading4);
        LAYOUT=findViewById(R.id.LAYOUT);
        playButton=findViewById(R.id.playButton);

        loading4.setVisibility(View.VISIBLE);
        LAYOUT.setVisibility(View.GONE);
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
                                 loading4.setVisibility(View.GONE);
                                 LAYOUT.setVisibility(View.VISIBLE);

                                 Recipe_Root root=response.body();
                                 Description.setText(root.getMeals().get(0).getStrInstructions());
                                 Category.setText(root.getMeals().get(0).getStrCategory());
                                 Area.setText(root.getMeals().get(0).getStrArea());

                                 playButton.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent intent = new  Intent(Intent.ACTION_VIEW);

                                         intent.setPackage("com.google.android.youtube");
                                         intent.setData(Uri.parse(root.getMeals().get(0).getStrYoutube()));

                                         startActivity(intent);
                                     }
                                 });

                                 Glide.with(getApplicationContext())
                                         .load(root.getMeals().get(0).getStrMealThumb())
                                         .apply(new RequestOptions().override(700,700))
                                         .apply(new RequestOptions().circleCrop())
                                         .apply(new RequestOptions()
                                                 .diskCacheStrategy(DiskCacheStrategy.ALL))
                                         .into(finished);

                                 ingridient_20 = root.getMeals().get(0).getStrIngredient1() + " - " + root.getMeals().get(0).getStrMeasure1();
                                 ingri_1.setText(ingridient_20);
                                 ingridient_1 = root.getMeals().get(0).getStrIngredient2() + " - " + root.getMeals().get(0).getStrMeasure2();
                                 ingri_2.setText(ingridient_1);
                                 ingridient_2 = root.getMeals().get(0).getStrIngredient3() + " - " + root.getMeals().get(0).getStrMeasure3();
                                 ingri_3.setText(ingridient_2);
                                 ingridient_3 = root.getMeals().get(0).getStrIngredient4() + " - " + root.getMeals().get(0).getStrMeasure4();
                                 ingri_4.setText(ingridient_3);
                                 ingridient_4 = root.getMeals().get(0).getStrIngredient5() + " - " + root.getMeals().get(0).getStrMeasure5();
                                 ingri_5.setText(ingridient_4);
                                 ingridient_5 = root.getMeals().get(0).getStrIngredient6() + " - " + root.getMeals().get(0).getStrMeasure6();
                                 ingri_6.setText(ingridient_5);
                                 ingridient_6 = root.getMeals().get(0).getStrIngredient7() + " - " + root.getMeals().get(0).getStrMeasure7();
                                 ingri_7.setText(ingridient_6);
                                 ingridient_7 = root.getMeals().get(0).getStrIngredient8() + " - " + root.getMeals().get(0).getStrMeasure8();
                                 ingri_8.setText(ingridient_7);
                                 ingridient_8 = root.getMeals().get(0).getStrIngredient9() + " - " + root.getMeals().get(0).getStrMeasure9();
                                 ingri_9.setText(ingridient_8);
                                 ingridient_9 = root.getMeals().get(0).getStrIngredient10() + " - " + root.getMeals().get(0).getStrMeasure10();
                                 ingri_10.setText(ingridient_9);
                                 ingridient_10 = root.getMeals().get(0).getStrIngredient11() + " - " + root.getMeals().get(0).getStrMeasure11();
                                 ingri_11.setText(ingridient_10);
                                 ingridient_11 = root.getMeals().get(0).getStrIngredient12() + " - " + root.getMeals().get(0).getStrMeasure12();
                                 ingri_12.setText(ingridient_11);
                                 ingridient_12 = root.getMeals().get(0).getStrIngredient13() + " - " + root.getMeals().get(0).getStrMeasure13();
                                 ingri_13.setText(ingridient_12);
                                 ingridient_13 = root.getMeals().get(0).getStrIngredient14() + " - " + root.getMeals().get(0).getStrMeasure14();
                                 ingri_14.setText(ingridient_13);
                                 ingridient_14 = root.getMeals().get(0).getStrIngredient15() + " - " + root.getMeals().get(0).getStrMeasure15();
                                 ingri_15.setText(ingridient_14);
                                 ingridient_15 = root.getMeals().get(0).getStrIngredient16() + " - " + root.getMeals().get(0).getStrMeasure16();
                                 ingri_16.setText(ingridient_15);
                                 ingridient_16 = root.getMeals().get(0).getStrIngredient17() + " - " + root.getMeals().get(0).getStrMeasure17();
                                 ingri_17.setText(ingridient_16);
                                 ingridient_17 = root.getMeals().get(0).getStrIngredient18() + " - " + root.getMeals().get(0).getStrMeasure18();
                                 ingri_18.setText(ingridient_17);
                                 ingridient_18 = root.getMeals().get(0).getStrIngredient19() + " - " + root.getMeals().get(0).getStrMeasure19();
                                 ingri_19.setText(ingridient_18);
                                 ingridient_19 = root.getMeals().get(0).getStrIngredient20() + " - " + root.getMeals().get(0).getStrMeasure20();
                                 ingri_20.setText(ingridient_19);



                                 for (int i=0;i<favs.size();i++) {

                                     if (favs.get(i).getRecipe_name().compareTo(root.getMeals().get(0).getStrMeal())==0){
                                         Toast.makeText(Recipe.this, "This is in favs!", Toast.LENGTH_SHORT).show();
                                         clicked=1;
                                         id=favs.get(i).getId();
                                         favouriteModelDelete=favs.get(i);

                                     }
                                 }
                                 if(clicked==1){
                                     fab.setImageDrawable(getResources().getDrawable(R.drawable.heart__1_));
                                 }else
                                     fab.setImageDrawable(getResources().getDrawable(R.drawable.heart));




                                 fab.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         if(clicked==1){
                                             Snackbar.make(view, "Removed from favourites", Snackbar.LENGTH_LONG)
                                                     .setAction("Action", null).show();
                                             fab.setImageDrawable(getResources().getDrawable(R.drawable.heart));
                                             boolean removeone=dataBaseHelper.deleteOne(favouriteModelDelete);


                                             clicked=0;
                                         }else {
                                             clicked=1;
                                             Snackbar.make(view, "Added to favourites!", Snackbar.LENGTH_LONG)
                                                     .setAction("Action", null).show();
                                             FavouriteModel favouriteModel=new FavouriteModel(-1,root.getMeals().get(0).getStrMeal(), root.getMeals().get(0).getStrMealThumb(),clicked);

                                             boolean addone = dataBaseHelper.addone(favouriteModel);

                                             fab.setImageDrawable(getResources().getDrawable(R.drawable.heart__1_));
                                         }

                                     }
                                 });


                             }
                         }

                         @Override
                         public void onFailure(Call<Recipe_Root> call, Throwable t) {
                             Title.setText("Code :" + t.getMessage());

                         }
        });

    }
}
