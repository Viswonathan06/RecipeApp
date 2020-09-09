package com.example.recipe2.AreaWise;

import java.util.List;

public class AreaRoot {
    private List<AreaMeals> meals;

    public AreaRoot() {
    }

    public AreaRoot(List<AreaMeals> meals) {
        this.meals = meals;
    }

    public List<AreaMeals> getMeals() {
        return meals;
    }

    public void setMeals(List<AreaMeals> meals) {
        this.meals = meals;
    }
}
