package com.example.recipe2.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe2.Categories.Categories;
import com.example.recipe2.Categories.RecycleViewAdapter;
import com.example.recipe2.Categories.Root;
import com.example.recipe2.MainActivity;
import com.example.recipe2.R;
import com.example.recipe2.Retrofit.JsonPlaceHolderApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    //initializing variables
    ArrayList<String> mCategory=new ArrayList<>();
    ArrayList<String> mDetails=new ArrayList<>();
    ArrayList<String> mThumbnail=new ArrayList<>();

    private void initRecyclerView(){
        Log.d("initRecyclerView","Recycler VIew Init'd");
        RecyclerView recyclerView=getActivity().findViewById(R.id.RecyclerView);
        RecycleViewAdapter adapter=new RecycleViewAdapter(getContext(),mCategory,mDetails,mThumbnail);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);

    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<Root> call=jsonPlaceHolderApi.getData();
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code :" + response.code());

                    return;
                }
                else{
                    Root root=response.body();
                    List<Categories> categories=root.getCategories();
                    for(Categories category:categories){
                        mCategory.add(category.getStrCategory());
                        mThumbnail.add(category.getStrCategoryThumb());
                        mDetails.add(category.getStrCategoryDescription());
                    }
                    initRecyclerView();
                    /*details.replace("[1]","\n");
                    details.replace("[2]","\n");
                    details.replace("[3]","\n");
                    details.replace("[4]","\n");

                     */

                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });










        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });
        return root;
    }
}
