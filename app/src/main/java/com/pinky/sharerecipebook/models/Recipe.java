package com.pinky.sharerecipebook.models;/* Created by Shay Mualem 31/08/2021 */

import java.io.Serializable;
import java.util.ArrayList;

// https://stackoverflow.com/questions/3323074/android-difference-between-parcelable-and-serializable

public class Recipe implements Serializable {
    private String recipeId;
    private String firebaseUserIdMade;    //add userId
    private String title;
    private String preparation;
    private String ingredients; // convert to class
    private int rank;
    private String imagePath;
    private ArrayList<Comment> commentArrayList;// todo change??

    public Recipe(String firebaseUserIdMade, String title, String preparation, String ingredients, String imagePath, String recipeId) {
        this.firebaseUserIdMade = firebaseUserIdMade;
        this.recipeId = recipeId;
        this.title = title;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.rank = 0;
        this.imagePath = imagePath;
        this.commentArrayList = new ArrayList<>();
        Comment c = new Comment("sdfsdf","sdfsdf");
        commentArrayList.add(c);

    }

    public Recipe() {
    } // needed

    public ArrayList<Comment> getCommentArrayList() {
        return commentArrayList;
    }

    public void setCommentArrayList(ArrayList<Comment> commentArrayList) {
        this.commentArrayList = commentArrayList;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }


    public void addCommentArrayList(Comment comment) {
        //this.commentArrayList.add(new Comment("hii", "123", timeStamp));
        this.commentArrayList.add(comment);
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
