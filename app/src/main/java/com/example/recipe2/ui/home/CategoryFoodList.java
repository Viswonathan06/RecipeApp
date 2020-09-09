package com.example.recipe2.ui.home;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.recipe2.Category_result.RecycleViewAdapterMeals;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        setContentView(R.layout.activity_temp_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        //setSupportActionBar(toolbar);

        nCategory=getIntent().getStringArrayListExtra("MealsList");
        nThumbnail=getIntent().getStringArrayListExtra("MealsThumb");
        thumbnail=getIntent().getStringExtra("Thumbnail");
        details= getIntent().getStringExtra("Description");
        category=getIntent().getStringExtra("Category name");
        image=findViewById(R.id.thumbnail);
        descript=findViewById(R.id.details);
        title=findViewById(R.id.category);
        getSupportActionBar().setTitle(category);

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
        RecyclerView recyclerView=findViewById(R.id.RecyclerViewFood1);
        RecycleViewAdapterMeals adapter=new RecycleViewAdapterMeals(this,nCategory,nThumbnail);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);











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
