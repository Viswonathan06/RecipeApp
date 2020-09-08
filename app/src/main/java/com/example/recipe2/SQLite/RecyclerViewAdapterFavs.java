package com.example.recipe2.SQLite;

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
import com.example.recipe2.R;
import com.example.recipe2.Retrofit.JsonPlaceHolderApi;
import com.example.recipe2.ui.home.Recipe;

import java.util.ArrayList;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class RecyclerViewAdapterFavs extends RecyclerView.Adapter<RecyclerViewAdapterFavs.ViewHolder>{

    private ArrayList<String> nTitles = new ArrayList<>();
    private ArrayList<String> nThumbnails = new ArrayList<>();
    Context context;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    public RecyclerViewAdapterFavs( Context context,ArrayList<String> nTitles, ArrayList<String> nThumbnails) {
        this.nTitles = nTitles;
        this.nThumbnails = nThumbnails;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.foodlist,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        holder.title.setText(nTitles.get(position));
        Glide.with(context)
                .load(nThumbnails.get(position))
                .apply(new RequestOptions().override(400,400))
                .apply(new RequestOptions().circleCrop())
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(holder.imageView);
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Recipe.class);
                intent.putExtra("Recipe name",nTitles.get(position));
                intent.putExtra("Recipe thumbnail",nThumbnails.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return nTitles.size();      }

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
