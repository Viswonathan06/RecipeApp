package com.example.recipe2.Recipe;

import java.util.List;

public class Recipe_Root {
    private List<Meals_Recipe> meals;

    public Recipe_Root() {
    }

    public Recipe_Root(List<Meals_Recipe> meals) {
        this.meals = meals;
    }

    public List<Meals_Recipe> getMeals() {
        return meals;
    }

    public void setMeals(List<Meals_Recipe> meals) {
        this.meals = meals;
    }
}
