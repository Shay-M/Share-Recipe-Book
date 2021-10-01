package com.pinky.sharerecipebook.models;/* Created by Shay Mualem 31/08/2021 */

import java.util.ArrayList;

public class User extends Guest {

    //private Recipe myRecipe;
    private String firebaseUserId;
    private String emailAddress;
    private ArrayList<String> favoriteRecipe; //todo id? List<Comment>

    // new user
    public User(String name, String userImagePath, String emailAddress, String firebaseUserId) {
        super(name, 0, userImagePath);
        this.emailAddress = emailAddress;
        this.firebaseUserId = firebaseUserId;
        this.favoriteRecipe = null;
    }

    public User() {
        //need for fire base
    }

    public String getFirebaseUserId() {
        return firebaseUserId;
    }

    public void setFirebaseUserId(String firebaseUserId) {
        this.firebaseUserId = firebaseUserId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public ArrayList<String> getFavoriteRecipe() {
        return favoriteRecipe;
    }

    public void addFavoriteRecipe(String favoriteRecipe) {
        this.favoriteRecipe.add(favoriteRecipe);
    }


}
