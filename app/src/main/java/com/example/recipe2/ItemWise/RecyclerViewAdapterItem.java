package com.example.recipe2.ItemWise;

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
import com.example.recipe2.AreaWise.Disp_AreaMeals;
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

public class RecyclerViewAdapterItem extends RecyclerView.Adapter<RecyclerViewAdapterItem.ViewHolder> {

    private static final String TAG = "RecycleViewAdapter";
    private ArrayList<String> nItems = new ArrayList<>();
    private ArrayList<String> nDescription = new ArrayList<>();
    private ArrayList<String> nCategory = new ArrayList<>();
    private ArrayList<String> nThumbnail = new ArrayList<>();
    Context context;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    public RecyclerViewAdapterItem(Context context, ArrayList<String> nItems,ArrayList<String> nDescription) {
        this.nItems = nItems;
        this.nDescription=nDescription;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerViewAdapterItem.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item,parent,false);
        RecyclerViewAdapterItem.ViewHolder holder=new RecyclerViewAdapterItem.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterItem.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        holder.title.setText(nItems.get(position));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<Meals_Root> callItemMeal=jsonPlaceHolderApi.getMeals_item(nItems.get(position));
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nCategory.clear();
                nThumbnail.clear();
                callItemMeal.enqueue(new Callback<Meals_Root>() {
                    @Override
                    public void onResponse(Call<Meals_Root> call, Response<Meals_Root> response) {
                        if(!response.isSuccessful()){
                            holder.title.setText("Code: "+response.code());

                        }
                        else{
                            Meals_Root itemroot=response.body();
                            List<Meals> mealsList=itemroot.getMeals();
                            for(Meals meal:mealsList){
                                nThumbnail.add(meal.getStrMealThumb());
                                nCategory.add(meal.getStrMeal());
                            }
                            Intent intent= new Intent(context, CategoryFoodList.class);
                            intent.putExtra("Description",nDescription.get(position));
                            intent.putExtra("Category name",nItems.get(position));
                            intent.putStringArrayListExtra("MealsList",nCategory);
                            intent.putStringArrayListExtra("MealsThumb",nThumbnail);
                            context.startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Meals_Root> call, Throwable t) {
                        holder.title.setText("Code: "+t.getMessage());

                    }
                });




            }
        });
    }

    @Override
    public int getItemCount() {
        return nItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        LinearLayout parentlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.Item);
            parentlayout=itemView.findViewById(R.id.parent_layout_area);
        }
    }
}
