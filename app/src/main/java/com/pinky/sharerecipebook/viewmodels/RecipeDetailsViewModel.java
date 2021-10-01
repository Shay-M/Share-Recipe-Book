package com.pinky.sharerecipebook.viewmodels;/* Created by Shay Mualem 01/10/2021 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pinky.sharerecipebook.models.User;
import com.pinky.sharerecipebook.repositories.FirebaseDatabaseRepository;

public class RecipeDetailsViewModel extends ViewModel {

    public MutableLiveData<User> liveDataUser;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myUsersDBRef = database.getReference("users");

    public void init() {
        if (liveDataUser != null) {
            return;
        }
        liveDataUser = FirebaseDatabaseRepository.getInstance().getUserByIdFromFirebase(null);
    }

    public LiveData<User> getUserLiveData(String userId) {
        liveDataUser = FirebaseDatabaseRepository.getInstance().getUserByIdFromFirebase(userId);
        return liveDataUser;
    }



}
