package com.pinky.sharerecipebook.models;/* Created by Shay Mualem 31/08/2021 */

import java.io.Serializable;

public class Recipe implements Serializable {

    private String title;
    private String preparation;
    private String ingredients; // convert to class
    private Float rank;
    private User owner;
    private String imagePath;
    private Comment comment;

    public Recipe(String title, String preparation, String ingredients, Float rank, User owner, String imagePath, Comment comment) {
        this.title = title;
        this.preparation = preparation;
        this.ingredients = ingredients;
        this.rank = rank;
        this.owner = owner;
        this.imagePath = imagePath;
        this.comment = comment;
    }

    public Recipe(String title, String preparation, String ingredients, Float rank) {
        this.title = title;
        this.preparation = preparation;
        this.ingredients = ingredients;
        this.rank = rank;
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

    public Float getRank() {
        return rank;
    }

    public void setRank(Float rank) {
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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }


}
