package com.pinky.sharerecipebook.repositories;/* Created by Shay Mualem 17/09/2021 */

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pinky.sharerecipebook.models.Recipe;
import com.pinky.sharerecipebook.models.User;

import java.util.ArrayList;

public class FirebaseDatabaseRepository {

/*

public abstract class FirebaseDatabaseRepository<Model> {

    protected DatabaseReference databaseReference;
    protected FirebaseDatabaseRepositoryCallback<Model> firebaseCallback;
    private Application application;
    private ValueEventListener listener;

    public FirebaseDatabaseRepository(Application application) {
        databaseReference = FirebaseDatabase.getInstance().getReference(getRootNode());
        this.application = application;
    }

    public static FirebaseDatabaseRepository getInstance() {
        return FirebaseDatabaseRepository.getInstance();
    }

    protected abstract String getRootNode();

//    public void addListener(FirebaseDatabaseRepositoryCallback<Model> firebaseCallback) {
    public void addListener(FirebaseDatabaseRepositoryCallback firebaseCallback) {
        this.firebaseCallback = firebaseCallback;
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("onDataChange", "snapshot: " + snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("onCancelled", "snapshot: " + error);

            }
        };
        databaseReference.addValueEventListener(listener);
    }

    public void removeListener() {
        databaseReference.removeEventListener(listener);
    }

    public interface FirebaseDatabaseRepositoryCallback<T> {
        void onSuccess(List<T> result);

        void onError(Exception e);
    }

*/

    static FirebaseDatabaseRepository INSTANCE;

    private ArrayList<Recipe> recipeList = new ArrayList<>();
    private User user;

    private MutableLiveData<ArrayList<Recipe>> arrayListMutableLiveData;
    private MutableLiveData<User> userMutableLiveData;


    public static FirebaseDatabaseRepository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new FirebaseDatabaseRepository();

        return INSTANCE;
    }

    // new FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<Recipe>()

    public MutableLiveData<ArrayList<Recipe>> getRecipes() {
        LoadRecipeData();
        arrayListMutableLiveData = new MutableLiveData<>();
        arrayListMutableLiveData.setValue(recipeList);
        return arrayListMutableLiveData;
    }


    //    private void LoadRecipeData(FirebaseDatabaseRepositoryCallback<Recipe> firebaseCallback) {
    private void LoadRecipeData() {
        recipeList.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("recipe");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Recipe obj = snapshot.getValue(Recipe.class);
//                    obj.setFirebaseUserIdMade(snapshot.getKey()); // todo need?
                    recipeList.add(obj);
                    Log.d("show", obj.getFirebaseUserIdMade());
                }
                arrayListMutableLiveData.postValue(recipeList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("LoadRecipeData", databaseError.toString());
            }
        });
    }

    public MutableLiveData<User> getUserByIdFromFirebase(String userIdTofind) {
        user = new User();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    if (!Objects.requireNonNull(ds.child("is_Manger").getValue(Boolean.class))) {
                    if (snapshot.getKey().equals(userIdTofind)) {
                        user = snapshot.getValue(User.class);
//                        User obj = snapshot.getValue(User.class);
                        // userMutableLiveData.setValue(obj);
                        Log.d("getUserByIdFromFirebase", "obj.getName() : " + user.getName());
                    } else {
                        //userMutableLiveData.setValue(null);
                        //user = null;
                        Log.d("getUserByIdFromFirebase", "not found user by this id: " + userIdTofind);
                    }
                }
                userMutableLiveData.postValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("getUserByIdFromFirebase", databaseError.toString());
            }
        });

        userMutableLiveData = new MutableLiveData<>();
        userMutableLiveData.setValue(user);
        return userMutableLiveData;
    }


    //
/*    private void LoadUserData(String userIdTofind) {
        recipeList.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getKey().equals(userIdTofind)) {
                        User obj = snapshot.getValue(User.class);
                        userMutableLiveData.setValue(null);
//                        recipeList.add(obj);
                       // articles.setValue(obj);
                        Log.d("getUserByIdFromFirebase", "obj.getName() : " + obj.getName());
                    } else {
                        //userMutableLiveData.setValue(null);
                        Log.d("getUserByIdFromFirebase", "not found user by this id: " + userIdTofind);

                    }
                }
                arrayListMutableLiveData.postValue(recipeList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("LoadRecipeData", databaseError.toString());
            }
        });
    }*/

    //


    /*public void removeListener() {
        myRef.removeEventListener(listener); // not work ,see if need
    }*/

/*    public interface FirebaseDatabaseRepositoryCallback<T> {
        void onSuccess(List<T> AllRecipes);

        void onError(Exception e);
    }*/


}
// https://www.youtube.com/watch?v=0W1QoPxfcS8
//https://www.youtube.com/watch?v=PAi9m69KYWs
// https://medium.com/globallogic-latinoamerica-mobile/viewmodel-firebase-database-3cc708044b5d