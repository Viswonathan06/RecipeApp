package com.example.recipe2.Retrofit;

import com.example.recipe2.Categories.Root;
import com.example.recipe2.Category_result.Meals_Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
//
    @GET("categories.php")
    Call<Root> getData();

    //?c=Seafood
    @GET("filter.php")
    Call<Meals_Root> getData(@Query("c") String category);
}

