package com.pinky.sharerecipebook.viewmodels;/* Created by Shay Mualem 19/09/2021 */

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pinky.sharerecipebook.models.Recipe;
import com.pinky.sharerecipebook.repositories.FirebaseDatabaseRepository;
import com.pinky.sharerecipebook.utils.CameraManagerUrl;

import java.util.ArrayList;

public class AddRecipeViewModel extends ViewModel {
    public MutableLiveData<ArrayList<Recipe>> liveData;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRecipeDBRef = database.getReference("recipe");

    private CameraManagerUrl cameraManagerUrl;

  /*  public AddRecipeViewModel(@NonNull Application application) {
        super(application);
        cameraManagerUrl = CameraManagerUrl.getInstance();
        //initLaunchers();

    }*/
    private Uri imgUri = null;

    public void init() {
        if (liveData != null) {
            return;
        }
        liveData = FirebaseDatabaseRepository.getInstance().getRecipes();
    }

    public LiveData<ArrayList<Recipe>> getRecipeLiveData() {
        return liveData;
    }

    public void AttachNewRecipe(Recipe newRecipe) {
        String key = myRecipeDBRef.push().getKey();
        myRecipeDBRef.child(key).setValue(newRecipe);
    }

}


