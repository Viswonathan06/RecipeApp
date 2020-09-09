package com.example.recipe2.AreaWise;

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

import com.example.recipe2.Category_result.RecycleViewAdapterMeals;
import com.example.recipe2.R;
import com.example.recipe2.Retrofit.JsonPlaceHolderApi;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerViewAdapterArea extends RecyclerView.Adapter<RecyclerViewAdapterArea.ViewHolder>{
    private static final String TAG = "RecycleViewAdapter";
    private ArrayList<String> nCountry = new ArrayList<>();
    private ArrayList<String> nThumbnails = new ArrayList<>();
    Context context;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    public RecyclerViewAdapterArea(Context context, ArrayList<String> nTitles, ArrayList<String> nThumbnails) {
        this.nCountry = nTitles;
        this.nThumbnails = nThumbnails;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.foodlist,parent,false);
        RecyclerViewAdapterArea.ViewHolder holder=new RecyclerViewAdapterArea.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        holder.title.setText(nCountry.get(position));
        //SET FLAGS TO THE IMAGEVIEW
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Disp_AreaMeals.class);
                intent.putExtra("Country",nCountry.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return nCountry.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;
        LinearLayout parentlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.Title);
            parentlayout=itemView.findViewById(R.id.parent_layout);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
}
