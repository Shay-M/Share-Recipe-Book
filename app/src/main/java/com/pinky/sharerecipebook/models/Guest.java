package com.pinky.sharerecipebook.models;/* Created by Shay Mualem 31/08/2021 */

public class Guest {
    String name = "Guest";
    private Float RatingOfUser = 0f;

    public Guest(String name, Float ratingOfUser) {
        this.name = name;
        RatingOfUser = ratingOfUser;
    }

}
