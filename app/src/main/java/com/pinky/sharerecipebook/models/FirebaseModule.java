package com.pinky.sharerecipebook.models;

import com.google.firebase.auth.FirebaseAuth;


public class FirebaseModule {

    public FirebaseAuth getFirebaseAuth() {

        return FirebaseAuth.getInstance();
    }
}
