package com.pinky.sharerecipebook.viewmodels;/* Created by Shay Mualem 19/09/2021 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pinky.sharerecipebook.models.Recipe;

import java.util.ArrayList;

public class AddRecipeViewModel extends ViewModel {
    public MutableLiveData<ArrayList<Recipe>> liveData;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRecipeDBRef = database.getReference("recipe");


    public LiveData<ArrayList<Recipe>> getRecipeLiveData() {
        return liveData;
    }

    public void AttachNewRecipe(Recipe newRecipe) {
        String key = myRecipeDBRef.push().getKey();
        newRecipe.setRecipeId(key);
        myRecipeDBRef.child(key).setValue(newRecipe);
    }

}


