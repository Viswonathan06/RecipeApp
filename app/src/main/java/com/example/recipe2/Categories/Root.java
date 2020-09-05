package com.example.recipe2.Categories;

import java.util.List;

public class Root {
    private List<Categories> categories;

    public Root() {
    }

    public Root(List<Categories> categories) {
        this.categories = categories;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
