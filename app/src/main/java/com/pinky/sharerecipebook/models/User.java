package com.pinky.sharerecipebook.models;/* Created by Shay Mualem 31/08/2021 */

public class User extends Guest {

    //private Recipe myRecipe;
    private  String firebaseUserId;
    private String userImagePath;
    private String emailAddress;
    private Recipe favoriteRecipe;//todo id?


    public User(String name, Float ratingOfUser, Recipe myRecipe, String userImagePath, String emailAddress, Recipe favoriteRecipe) {
        super(name, ratingOfUser);
        //this.myRecipe = myRecipe;
        this.userImagePath = userImagePath;
        this.emailAddress = emailAddress;
        this.favoriteRecipe = favoriteRecipe;
    }

    /*public Recipe getMyRecipe() {
        return myRecipe;
    }*/

   /* public void setMyRecipe(Recipe myRecipe) {
        this.myRecipe = myRecipe;
    }*/

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Recipe getFavoriteRecipe() {
        return favoriteRecipe;
    }

    public void setFavoriteRecipe(Recipe favoriteRecipe) {
        this.favoriteRecipe = favoriteRecipe;
    }
}
