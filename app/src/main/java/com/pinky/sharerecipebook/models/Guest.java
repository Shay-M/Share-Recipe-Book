package com.pinky.sharerecipebook.models;/* Created by Shay Mualem 31/08/2021 */

public class Guest {
    protected String name = "Guest";
    protected int ratingOfUser = 0;

    public Guest(String name, int ratingOfUser) {
        this.name = name;
        this.ratingOfUser = ratingOfUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRatingOfUser() {
        return ratingOfUser;
    }

    public void setRatingOfUser(int ratingOfUser) {
        this.ratingOfUser = ratingOfUser;
    }
}
