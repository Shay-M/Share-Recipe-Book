package com.pinky.sharerecipebook.viewmodels;/* Created by Shay Mualem 17/09/2021 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pinky.sharerecipebook.models.Recipe;
import com.pinky.sharerecipebook.models.User;
import com.pinky.sharerecipebook.repositories.AuthRepository;
import com.pinky.sharerecipebook.repositories.FirebaseDatabaseRepository;

import java.util.ArrayList;


public class HomeViewModel extends ViewModel { // todo ?change to homeViewModel

    public MutableLiveData<ArrayList<Recipe>> liveData;
    public MutableLiveData<User> userLoginliveData;
//    public LiveData<User> userliveData;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public void init() {
        if (liveData != null) {
            return;
        }
        liveData = FirebaseDatabaseRepository.getInstance().getRecipes();
    }

    public LiveData<ArrayList<Recipe>> getRecipeLiveData() {
        return liveData;
    }

    //
    public void inituserLogin() {
        if (userLoginliveData != null) {
            return;
        }
        String userId = null;
        if (AuthRepository.getInstance().getCurrentUser() != null) {
            userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        userLoginliveData = FirebaseDatabaseRepository.getInstance().getUserByIdFromFirebase(userId);
//        userliveData = FirebaseDatabaseRepository.getInstance().getUserByIdFromFirebase(userId);

    }

    public LiveData<User> getUserLiveData() {
        return userLoginliveData;
    }


}