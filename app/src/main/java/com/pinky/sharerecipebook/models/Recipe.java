package com.pinky.sharerecipebook.models;/* Created by Shay Mualem 31/08/2021 */

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    //add userId
    private String firebaseUserMadeId;
    private String title;
    private String preparation;
    private String ingredients; // convert to class
    private int rank;
    private User owner;
    private String imagePath;
    private List<Comment> comments;

    public Recipe(String firebaseUserMadeId, String title, String preparation, String ingredients, String imagePath) {
        this.firebaseUserMadeId = firebaseUserMadeId;
        this.title = title;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.rank = 0;
        this.owner = owner;
        this.imagePath = imagePath;
        this.comments = null;
    }

    public Recipe() {
    } // mast

    public Recipe(String title, String preparation, String ingredients, FirebaseUser currentUser, String imagePath) {
    }

    public String getFirebaseUserMadeId() {
        return firebaseUserMadeId;
    }
    //private Comment comment;

    public void setFirebaseUserMadeId(String firebaseUserMadeId) {
        this.firebaseUserMadeId = firebaseUserMadeId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getFirebaseRecipeMadeId() {
        return firebaseUserMadeId;
    }

    public void setFirebaseRecipeMadeId(String firebaseUserMadeId) {
        this.firebaseUserMadeId = firebaseUserMadeId;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


}
