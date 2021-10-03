package com.pinky.sharerecipebook.utils;/* Created by Shay Mualem 03/10/2021 */

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.models.User;
import com.pinky.sharerecipebook.repositories.FirebaseDatabaseRepository;
import com.pinky.sharerecipebook.viewmodels.RecipeDetailsViewModel;

public class UserLoginHelper {

    private static UserLoginHelper instead;
    private RecipeDetailsViewModel recipeDetailsViewModel;



    public static UserLoginHelper getInstance() {
        if (instead == null)
            instead = new UserLoginHelper();
        return instead;
    }

    public void setUser(String email) {
        /*String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        MutableLiveData<User> getUserFromFirebase = FirebaseDatabaseRepository.getInstance().getUserByIdFromFirebase(userId);

        recipeDetailsViewModel
                .getUserLiveData(userId)
                .observe(getViewLifecycleOwner(),
                        new Observer<User>() {
                            @Override
                            public void onChanged(User user) {



                            }
                        });

        Log.d("setUser", "setUser: "+ getUserFromFirebase.getValue().getName());

        //getUserFromFirebase.observe(this,user -> {});
        User loginUser = new User(
                "name",
                "", // todo
                email,
                userId
        );

*/

    }


}
