package com.pinky.sharerecipebook.models;/* Created by Shay Mualem 31/08/2021 */

public class User extends Guest {

    //private Recipe myRecipe;
    private String firebaseUserId;
    private String userImagePath;
    private String emailAddress;
    private String[] favoriteRecipe;//todo id?

    // new user
    public User(String name, String userImagePath, String emailAddress, String firebaseUserId) {
        super(name, 0);
        this.userImagePath = userImagePath;
        this.emailAddress = emailAddress;
        this.firebaseUserId = firebaseUserId;
        this.favoriteRecipe = null;
    }

    public String getFirebaseUserId() {
        return firebaseUserId;
    }

    public void setFirebaseUserId(String firebaseUserId) {
        this.firebaseUserId = firebaseUserId;
    }

    public String[] getFavoriteRecipe() {
        return favoriteRecipe;
    }
    /*    public User(String name, int ratingOfUser, Recipe myRecipe, String userImagePath, String emailAddress, Recipe favoriteRecipe) {
            super(name, ratingOfUser);
            //this.myRecipe = myRecipe;
            this.userImagePath = userImagePath;
            this.emailAddress = emailAddress;
            this.favoriteRecipe = favoriteRecipe;
        }*/

    public void setFavoriteRecipe(String[] favoriteRecipe) {
        this.favoriteRecipe = favoriteRecipe;
    }

    /*public Recipe getMyRecipe() { //todo
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

}
