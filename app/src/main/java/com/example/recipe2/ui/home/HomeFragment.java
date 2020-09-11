package com.example.recipe2.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe2.AreaWise.AreaMeals;
import com.example.recipe2.AreaWise.AreaRoot;
import com.example.recipe2.AreaWise.RecyclerViewAdapterArea;
import com.example.recipe2.Categories.Categories;
import com.example.recipe2.Categories.RecycleViewAdapter;
import com.example.recipe2.Categories.Root;
import com.example.recipe2.Category_result.Meals_Root;
import com.example.recipe2.MainActivity;
import com.example.recipe2.R;
import com.example.recipe2.Recipe.Recipe_Root;
import com.example.recipe2.Retrofit.JsonPlaceHolderApi;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
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
    EditText Searchbox;
    ImageButton search, sadIce;
    TextView error, text,exp1,exp2,int_text;
    RecyclerView recyclerView,AreaRecycler;
    ProgressBar loading,loading2,loading3;

    //initializing variables
    ArrayList<String> mCategory = new ArrayList<>();
    ArrayList<String> mDetails = new ArrayList<>();
    ArrayList<String> mThumbnail = new ArrayList<>();
    ArrayList<Integer> mAreaThumbnails=new ArrayList<>();
    ArrayList<String> mAreaMeals = new ArrayList<>();


    private void initRecyclerView() {
        Log.d("initRecyclerView", "Recycler VIew Init'd");
        RecyclerView recyclerView = getActivity().findViewById(R.id.RecyclerView);
        RecycleViewAdapter adapter = new RecycleViewAdapter(getContext(), mCategory, mDetails, mThumbnail);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        Searchbox = root.findViewById(R.id.SearchBox);
        search = root.findViewById(R.id.search);
        sadIce = root.findViewById(R.id.sadIce);
        text = root.findViewById(R.id.category_text);
        int_text = root.findViewById(R.id.International_text);
        exp1=root.findViewById(R.id.exp1);
        exp2=root.findViewById(R.id.exp2);


        error = root.findViewById(R.id.error);
        recyclerView = root.findViewById(R.id.RecyclerView);
        AreaRecycler=root.findViewById(R.id.AreaRecycler);
        loading=root.findViewById(R.id.loading);
        loading2=root.findViewById(R.id.loading2);
        loading3=root.findViewById(R.id.loading3);

        loading2.setVisibility(View.VISIBLE);
        loading3.setVisibility(View.VISIBLE);



        //adding flags to array!
        mAreaThumbnails.add(R.drawable.us_flag);
        mAreaThumbnails.add(R.drawable.uk_flag);
        mAreaThumbnails.add(R.drawable.ca_flag);
        mAreaThumbnails.add(R.drawable.tn_ch_flag);
        mAreaThumbnails.add(R.drawable.nl_flag);
        mAreaThumbnails.add(R.drawable.eg_flag);
        mAreaThumbnails.add(R.drawable.fr_flag);
        mAreaThumbnails.add(R.drawable.gr_flag);
        mAreaThumbnails.add(R.drawable.in_flag);
        mAreaThumbnails.add(R.drawable.ei_flag);
        mAreaThumbnails.add(R.drawable.it_flag);
        mAreaThumbnails.add(R.drawable.jm_flag);
        mAreaThumbnails.add(R.drawable.ja_flag);
        mAreaThumbnails.add(R.drawable.ke_flag);
        mAreaThumbnails.add(R.drawable.my_flag);
        mAreaThumbnails.add(R.drawable.mx_flag);
        mAreaThumbnails.add(R.drawable.mo_flag);
        mAreaThumbnails.add(R.drawable.pl_flag);
        mAreaThumbnails.add(R.drawable.rs_flag);
        mAreaThumbnails.add(R.drawable.sp_flag);
        mAreaThumbnails.add(R.drawable.th_flag);
        mAreaThumbnails.add(R.drawable.ts_flag);
        mAreaThumbnails.add(R.drawable.tu_flag);
        mAreaThumbnails.add(R.drawable.un_flag);
        mAreaThumbnails.add(R.drawable.vm_flag);





        error.setVisibility(View.GONE);
        sadIce.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<Root> call = jsonPlaceHolderApi.getData();
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (!response.isSuccessful()) {
                    recyclerView.setVisibility(View.GONE);
                    AreaRecycler.setVisibility(View.GONE);
                    text.setVisibility(View.GONE);
                    exp1.setVisibility(View.GONE);
                    exp2.setVisibility(View.GONE);
                    int_text.setVisibility(View.GONE);
                    sadIce.setVisibility(View.VISIBLE);
                    error.setVisibility(View.VISIBLE);
                    loading.setVisibility(View.VISIBLE);

                    error.setText("Code :" + response.code());

                    return;
                } else {


                    recyclerView.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                    sadIce.setVisibility(View.GONE);
                    error.setVisibility(View.GONE);
                    Searchbox.setVisibility(View.VISIBLE);

                    Root root = response.body();
                    List<Categories> categories = root.getCategories();
                    for (Categories category : categories) {
                        mCategory.add(category.getStrCategory());
                        mThumbnail.add(category.getStrCategoryThumb());
                        mDetails.add(category.getStrCategoryDescription());
                    }
                    loading2.setVisibility(View.GONE);

                    initRecyclerView();

                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                AreaRecycler.setVisibility(View.GONE);
                text.setVisibility(View.GONE);
                exp1.setVisibility(View.GONE);
                exp2.setVisibility(View.GONE);
                int_text.setVisibility(View.GONE);
                sadIce.setVisibility(View.VISIBLE);
                error.setVisibility(View.VISIBLE);
                Searchbox.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);

                error.setText(t.getMessage());
            }
        });
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit1.create(JsonPlaceHolderApi.class);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Searchbox.getText().toString().isEmpty()){
                    loading.setVisibility(View.GONE);

                }
                Call<Recipe_Root> call = jsonPlaceHolderApi.getRecipe(Searchbox.getText().toString());
                call.enqueue(new Callback<Recipe_Root>() {
                    @Override
                    public void onResponse(Call<Recipe_Root> call, Response<Recipe_Root> response) {
                        if (!response.isSuccessful()) {
                            recyclerView.setVisibility(View.GONE);
                            text.setVisibility(View.GONE);
                            sadIce.setVisibility(View.VISIBLE);
                            error.setVisibility(View.VISIBLE);

                            error.setText("Code :" + response.code());
                            return;
                        } else {

                            Recipe_Root root = response.body();
                            if(root.getMeals()==null){
                                recyclerView.setVisibility(View.GONE);
                                AreaRecycler.setVisibility(View.GONE);
                                text.setVisibility(View.GONE);
                                exp1.setVisibility(View.GONE);
                                exp2.setVisibility(View.GONE);
                                int_text.setVisibility(View.GONE);
                                sadIce.setVisibility(View.VISIBLE);
                                error.setVisibility(View.VISIBLE);
                                Searchbox.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.VISIBLE);

                            }
                            else{
                                recyclerView.setVisibility(View.VISIBLE);
                                text.setVisibility(View.VISIBLE);
                                sadIce.setVisibility(View.GONE);
                                error.setVisibility(View.GONE);
                                Intent intent=new Intent(getContext(),Recipe.class);
                                intent.putExtra("Recipe name",root.getMeals().get(0).getStrMeal());
                                intent.putExtra("Recipe thumbnail",root.getMeals().get(0).getStrMealThumb());
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Recipe_Root> call, Throwable t) {
                        recyclerView.setVisibility(View.GONE);
                        text.setVisibility(View.GONE);
                        sadIce.setVisibility(View.VISIBLE);
                        error.setVisibility(View.VISIBLE);
                        error.setText(t.getMessage());
                    }
                });
            }
        });

        Call<AreaRoot> meals_rootCall=jsonPlaceHolderApi.getMeals("list");
        meals_rootCall.enqueue(new Callback<AreaRoot>() {
            @Override
            public void onResponse(Call<AreaRoot> call, Response<AreaRoot> response) {
                if (!response.isSuccessful()) {
                    recyclerView.setVisibility(View.GONE);
                    text.setVisibility(View.GONE);
                    sadIce.setVisibility(View.VISIBLE);
                    error.setVisibility(View.VISIBLE);

                    error.setText("Code :" + response.code());
                    return;
                } else {
                    AreaRoot root=response.body();
                    List<AreaMeals> area_meals=root.getMeals();
                    loading3.setVisibility(View.GONE);
                    for(AreaMeals meals:area_meals){
                        mAreaMeals.add(meals.getStrArea());
                    }
                    RecyclerViewAdapterArea recyclerViewAdapterArea=new RecyclerViewAdapterArea(getContext(),mAreaMeals,mAreaThumbnails);
                    AreaRecycler.setAdapter(recyclerViewAdapterArea);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    AreaRecycler.setLayoutManager(mLayoutManager);
                }
            }


            @Override
            public void onFailure(Call<AreaRoot> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                text.setVisibility(View.GONE);
                sadIce.setVisibility(View.VISIBLE);
                error.setVisibility(View.VISIBLE);
                error.setText(t.getMessage());
            }
        });

        sadIce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.VISIBLE);
                AreaRecycler.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                exp1.setVisibility(View.VISIBLE);
                exp2.setVisibility(View.VISIBLE);
                int_text.setVisibility(View.VISIBLE);
                sadIce.setVisibility(View.GONE);
                error.setVisibility(View.GONE);
                Searchbox.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                Searchbox.setText("");
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

