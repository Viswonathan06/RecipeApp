package com.example.recipe2.SQLite;

public class FavouriteModel {
    private int id;
    private String recipe_name;
    private String recipe_thumb;
    private int clicked;

    public FavouriteModel(int id, String recipe_name, String recipe_thumb, int clicked) {
        this.id = id;
        this.recipe_name = recipe_name;
        this.recipe_thumb = recipe_thumb;
        this.clicked = clicked;
    }

    public FavouriteModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getRecipe_thumb() {
        return recipe_thumb;
    }

    public void setRecipe_thumb(String recipe_thumb) {
        this.recipe_thumb = recipe_thumb;
    }

    public int getClicked() {
        return clicked;
    }

    public void setClicked(int clicked) {
        this.clicked = clicked;
    }
}
