package com.pinky.sharerecipebook.viewmodels;/* Created by Shay Mualem 17/09/2021 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pinky.sharerecipebook.models.Recipe;
import com.pinky.sharerecipebook.repositories.FirebaseDatabaseRepository;

import java.util.ArrayList;


public class HomeViewModel extends ViewModel { // todo ?change to homeViewModel

    public MutableLiveData<ArrayList<Recipe>> liveData;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("recipe");

    public void init() {
        if (liveData != null) {
            return;
        }
        liveData = FirebaseDatabaseRepository.getInstance().getRecipes();
    }

    public LiveData<ArrayList<Recipe>> getRecipeLiveData() {
        return liveData;
    }

    public void Add_Recipe(Recipe obj) {
        String key = myRef.push().getKey();
        myRef.child(key).setValue(obj);
    }

}