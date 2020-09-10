package com.example.recipe2.ItemWise;

import java.util.List;

public class ItemRoot {
    private List<ItemMeals> meals;

    public ItemRoot() {
    }

    public ItemRoot(List<ItemMeals> meals) {
        this.meals = meals;
    }

    public List<ItemMeals> getMeals() {
        return meals;
    }

    public void setMeals(List<ItemMeals> meals) {
        this.meals = meals;
    }
}
