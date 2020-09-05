package com.example.recipe2.Category_result;

import java.util.List;

public class Meals_Root {
    private List<Meals> meals;

    public Meals_Root(List<Meals> meals) {
        this.meals = meals;
    }

    public Meals_Root() {
    }

    public List<Meals> getMeals() {
        return meals;
    }

    public void setMeals(List<Meals> meals) {
        this.meals = meals;
    }
}
