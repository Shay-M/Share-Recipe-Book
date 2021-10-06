package com.pinky.sharerecipebook.viewmodels;/* Created by Shay Mualem 01/10/2021 */
// https://www.youtube.com/watch?v=qWyzvkySKaU&list=PLs1bCj3TvmWmM-qN3FsCuPTTX-29I8Gh7&index=27

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pinky.sharerecipebook.adapters.NotificationAdapter;
import com.pinky.sharerecipebook.models.Recipe;
import com.pinky.sharerecipebook.models.User;
import com.pinky.sharerecipebook.repositories.FirebaseDatabaseRepository;
import com.pinky.sharerecipebook.repositories.MyFirebaseMessagingService;

import java.util.ArrayList;

public class RecipeDetailsViewModel extends ViewModel {

    public MutableLiveData<User> liveDataUserRecipeCreated;
    public MutableLiveData<User> liveDataCurrentUser;
    private String userMakeId;
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

    public void changeLikeToRecipe(String IdTofind, int newValue) {
        String folder = "recipe";
        String fildeToChange = "rank";

        // change like in recipe
        FirebaseDatabaseRepository.getInstance().changeDataFirebase(folder, IdTofind, fildeToChange, Integer.toString(newValue), 0);

    }

    public void addIdLikeToUser(String IdTofind, ArrayList<String> newValue) {
        String folder = "users";
        String fildeToChange = "favoriteRecipe";

        // change like in recipe
        FirebaseDatabaseRepository.getInstance().changeDataFirebaseArrayList(folder, IdTofind, fildeToChange, newValue);

    }

    public void sendNotification(String SenderId, String ownerId, String recipeName) {
        String folder = "recipe";
        String fildeToChange = "commentArrayList";

        // send notification to owner
        MyFirebaseMessagingService.getInstance().changeDataFirebaseCommentArrayList(SenderId, ownerId, recipeName, folder,fildeToChange);

    }

/*    public LiveData<User> getCurrentUserLiveData() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        liveDataCurrentUser = FirebaseDatabaseRepository.getInstance().getUserByIdFromFirebase(userId);
        return liveDataCurrentUser;
    }*/


}
