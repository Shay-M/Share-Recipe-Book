package com.pinky.sharerecipebook.viewmodels;/* Created by Shay Mualem 01/10/2021 */
// https://www.youtube.com/watch?v=qWyzvkySKaU&list=PLs1bCj3TvmWmM-qN3FsCuPTTX-29I8Gh7&index=27

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.pinky.sharerecipebook.models.Recipe;
import com.pinky.sharerecipebook.models.User;
import com.pinky.sharerecipebook.repositories.FirebaseDatabaseRepository;

public class RecipeDetailsViewModel extends ViewModel {

    public MutableLiveData<User> liveDataUserRecipeCreated;
    private String userMakeId;

    public MutableLiveData<User> liveDataCurrentUser;
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //final DatabaseReference myUsersDBRef = database.getReference("users");
//
//    public MutableLiveData<Boolean> LikedRecipeOn;

//    protected final FirebaseUser CurrentUser = FirebaseAuth.getInstance().getCurrentUser();

    public void init() {
        if (liveDataUserRecipeCreated != null)
            return;
        liveDataUserRecipeCreated = FirebaseDatabaseRepository.getInstance().getUserByIdFromFirebase(userMakeId);

    }

    public LiveData<User> getUserLiveData(String userId) {
        userMakeId = userId;
        liveDataUserRecipeCreated = FirebaseDatabaseRepository.getInstance().getUserByIdFromFirebase(userId);
        return liveDataUserRecipeCreated;
    }

    public void setRecipe(Recipe recipeGet) {

    }


/*    public LiveData<User> getCurrentUserLiveData() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        liveDataCurrentUser = FirebaseDatabaseRepository.getInstance().getUserByIdFromFirebase(userId);
        return liveDataCurrentUser;
    }*/



}
