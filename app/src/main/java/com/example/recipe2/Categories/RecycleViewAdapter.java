package com.example.recipe2.Categories;

import android.content.Context;
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

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private static final String TAG = "RecycleViewAdapter";

    ArrayList<String> mCategory=new ArrayList<>();
    ArrayList<String> mDetails=new ArrayList<>();
    ArrayList<String> mThumbnail=new ArrayList<>();
    private Context mContext;

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
