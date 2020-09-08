package com.example.recipe2.Categories;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.recipe2.Category_result.Meals;
import com.example.recipe2.Category_result.Meals_Root;
import com.example.recipe2.R;
import com.example.recipe2.Retrofit.JsonPlaceHolderApi;
import com.example.recipe2.ui.home.CategoryFoodList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private static final String TAG = "RecycleViewAdapter";

    ArrayList<String> mCategory=new ArrayList<>();
    ArrayList<String> mDetails=new ArrayList<>();
    ArrayList<String> mThumbnail=new ArrayList<>();
    private Context mContext;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    ArrayList<String> nCategory=new ArrayList<>();
    ArrayList<String> nDetails=new ArrayList<>();
    ArrayList<String> nThumbnail=new ArrayList<>();

    public RecycleViewAdapter(Context context,ArrayList<String> mCategory, ArrayList<String> mDetails, ArrayList<String> mThumbnail) {
        this.mCategory = mCategory;
        this.mDetails = mDetails;
        this.mThumbnail = mThumbnail;
        this.mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        holder.Category.setText((mCategory.get(position)));
        Glide.with(mContext)
                .load(mThumbnail.get(position))
                .apply(new RequestOptions().override(500,500))
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(holder.imageView);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Meals_Root> call=jsonPlaceHolderApi.getData(mCategory.get(position));
                call.enqueue(new Callback<Meals_Root>() {
                    @Override
                    public void onResponse(Call<Meals_Root> call, Response<Meals_Root> response) {
                        if (!response.isSuccessful()) {
                            // textView.setText("Code :" + response.code());
                            holder.Category.setText("Code:"+response.code());
                            return;
                        }
                        else {
                            nCategory.clear();
                            nThumbnail.clear();

                            Meals_Root meals_root = response.body();
                            Intent intent= new Intent(mContext, CategoryFoodList.class);
                            intent.putExtra("Description",mDetails.get(position));
                            intent.putExtra("Category name",mCategory.get(position));
                            intent.putExtra("Thumbnail",mThumbnail.get(position));
                            List<Meals> mealsList=meals_root.getMeals();
                            for(Meals meal:mealsList){
                                nCategory.add(meal.getStrMeal());
                                nThumbnail.add(meal.getStrMealThumb());
                            }

                            intent.putStringArrayListExtra("MealsList",nCategory);
                            intent.putStringArrayListExtra("MealsThumb",nThumbnail);
                            mContext.startActivity(intent);



                        }
                    }

                    @Override
                    public void onFailure(Call<Meals_Root> call, Throwable t) {
                        holder.Category.setText("Code:"+t.getMessage());

                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return mCategory.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Category;
        ImageView imageView;
        LinearLayout parentlayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            Category=itemView.findViewById(R.id.Category);
            parentlayout=itemView.findViewById(R.id.parent_layout);

        }
    }
}
