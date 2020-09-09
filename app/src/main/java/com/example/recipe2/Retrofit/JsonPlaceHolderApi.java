package com.example.recipe2.Retrofit;

import com.example.recipe2.AreaWise.AreaRoot;
import com.example.recipe2.Categories.Root;
import com.example.recipe2.Category_result.Meals;
import com.example.recipe2.Category_result.Meals_Root;
import com.example.recipe2.Recipe.Recipe_Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
//
    @GET("categories.php")
    Call<Root> getData();

    //?c=Seafood
    @GET("filter.php")
    Call<Meals_Root> getData(@Query("c") String category);

    @GET("search.php")
    Call<Recipe_Root> getRecipe(@Query("s") String mean_name);
//?a=list
    @GET("list.php")
    Call<AreaRoot> getMeals(@Query("a") String list);

    @GET("filter.php")
    Call<Meals_Root> getAreaMeals(@Query("a") String category);



}

