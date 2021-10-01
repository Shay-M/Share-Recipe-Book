package com.pinky.sharerecipebook.models;/* Created by Shay Mualem 31/08/2021 */

import java.io.Serializable;
import java.util.ArrayList;

// https://stackoverflow.com/questions/3323074/android-difference-between-parcelable-and-serializable

public class Recipe implements Serializable {
    private String firebaseUserIdMade;    //add userId
    private String title;
    private String preparation;
    private String ingredients; // convert to class
    private int rank;
    private String imagePath;
    private ArrayList<Comment> comments;// todo change??

    public Recipe(String firebaseUserIdMade, String title, String preparation, String ingredients, String imagePath) {
        this.firebaseUserIdMade = firebaseUserIdMade;
        this.title = title;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.rank = 0;
        this.imagePath = imagePath;
        this.comments = null;
    }

    public Recipe() {
    } // needed

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addComments(Comment comments) {
        this.comments.add(comments);
    }

    public String getFirebaseUserIdMade() {
        return firebaseUserIdMade;
    }

    public void setFirebaseUserIdMade(String firebaseUserIdMade) {
        this.firebaseUserIdMade = firebaseUserIdMade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


}
