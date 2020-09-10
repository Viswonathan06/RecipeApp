package com.example.recipe2.ui.gallery;

import android.content.ClipData;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe2.Categories.RecycleViewAdapter;
import com.example.recipe2.ItemWise.ItemMeals;
import com.example.recipe2.ItemWise.ItemRoot;
import com.example.recipe2.ItemWise.RecyclerViewAdapterItem;
import com.example.recipe2.R;
import com.example.recipe2.Recipe.Recipe_Root;
import com.example.recipe2.Retrofit.JsonPlaceHolderApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    RecyclerView recyclerView;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    ArrayList<String> mItems=new ArrayList<>();
    ArrayList<String> mDescript=new ArrayList<>();
    ProgressBar loading1;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        recyclerView=root.findViewById(R.id.Recycler_Items);
        loading1=root.findViewById(R.id.loading1);
        loading1.setVisibility(View.VISIBLE);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<ItemRoot> itemRootCall=jsonPlaceHolderApi.getItemMeals("list");
        itemRootCall.enqueue(new Callback<ItemRoot>() {
            @Override
            public void onResponse(Call<ItemRoot> call, Response<ItemRoot> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code :" + response.code());
                    return;
                }
                else {

                    ItemRoot itemRoot= response.body();
                    List<ItemMeals> Itemlist=itemRoot.getMeals();
                    loading1.setVisibility(View.GONE);
                    for(ItemMeals meal:Itemlist){
                        mItems.add(meal.getStrIngredient());
                        mDescript.add(meal.getStrDescription());
                    }
                    Log.d("initRecyclerView", "Recycler VIew Init'd");
                    RecyclerViewAdapterItem adapter = new RecyclerViewAdapterItem(getContext(), mItems,mDescript);
                    recyclerView.setAdapter(adapter);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);

                }
            }

            @Override
            public void onFailure(Call<ItemRoot> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });








        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
