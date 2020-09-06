package com.example.recipe2.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.recipe2.Categories.RecycleViewAdapter;
import com.example.recipe2.Category_result.RecycleViewAdapterMeals;
import com.example.recipe2.R;

import java.util.ArrayList;

public class CategoryFoodList extends AppCompatActivity {
    ArrayList<String> nCategory=new ArrayList<>();
    ArrayList<String> nDetails=new ArrayList<>();
    ArrayList<String> nThumbnail=new ArrayList<>();
    String details,category,thumbnail;
    ImageView image;
    TextView title;
    TextView descript;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_food_list);
        nCategory=getIntent().getStringArrayListExtra("MealsList");
        nThumbnail=getIntent().getStringArrayListExtra("MealsThumb");
        thumbnail=getIntent().getStringExtra("Thumbnail");
        details= getIntent().getStringExtra("Description");
        category=getIntent().getStringExtra("Category name");
        image=findViewById(R.id.thumbnail);
        descript=findViewById(R.id.details);
        title=findViewById(R.id.category);

        title.setText(category);
        descript.setText(details);

        Glide.with(this)
                .load(thumbnail)
                .apply(new RequestOptions().circleCrop())
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(image);

        //Recycler view implementation
        Log.d("initRecyclerView","Recycler VIew Init'd");
        RecyclerView recyclerView=findViewById(R.id.RecyclerViewFood);
        RecycleViewAdapterMeals adapter=new RecycleViewAdapterMeals(this,nCategory,nThumbnail);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);

        //





    }
}
