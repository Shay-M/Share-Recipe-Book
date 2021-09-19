package com.pinky.sharerecipebook.repositories;/* Created by Shay Mualem 18/09/2021 */

import com.pinky.sharerecipebook.models.Recipe;

import java.util.List;

public interface RecipeDataInterface {
    public List<Recipe> getUserData();

    public void AddUser(Recipe obj);
}
