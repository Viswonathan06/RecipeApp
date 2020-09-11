package com.example.recipe2.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe2.Categories.RecycleViewAdapter;
import com.example.recipe2.Category_result.RecycleViewAdapterMeals;
import com.example.recipe2.R;
import com.example.recipe2.SQLite.DataBaseHelper;
import com.example.recipe2.SQLite.FavouriteModel;
import com.example.recipe2.SQLite.RecyclerViewAdapterFavs;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {
    ArrayList<String> nCategory = new ArrayList<>();
    ArrayList<String> nDetails = new ArrayList<>();
    ArrayList<String> nThumbnail = new ArrayList<>();
    String details, category, thumbnail;
    ImageView image;
    TextView title;

    public void initRecycleView() {


    }

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
        List<FavouriteModel> favs = dataBaseHelper.getRecipes();
        for (int i = 0; i < favs.size(); i++) {
            nCategory.add(favs.get(i).getRecipe_name());
            nThumbnail.add(favs.get(i).getRecipe_thumb());
        }
        //Toast.makeText(getContext(), nCategory.get(3), Toast.LENGTH_SHORT).show();
        Log.d("initRecyclerView", "Recycler VIew Init'd");
        RecyclerView recyclerView = root.findViewById(R.id.favorites_recycle);
        if (recyclerView == null) {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
        } else {
            RecyclerViewAdapterFavs adapterFavs = new RecyclerViewAdapterFavs(getContext(), nCategory, nThumbnail);
            if (nCategory.isEmpty() || nThumbnail.isEmpty()) {

            } else {
                recyclerView.setAdapter(adapterFavs);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(mLayoutManager);

            }
        }

        /*for(FavouriteModel fav:favs){
            nCategory.add(fav.getRecipe_name());
            nThumbnail.add(fav.getRecipe_thumb());
        }
        */


        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}

