package com.pinky.sharerecipebook.models;/* Created by Shay Mualem 31/08/2021 */

public class Comment {
    private String txt;
    private Float RatingGive;
    private User user;

    public Comment(String txt, Float ratingGive, User user) {
        this.txt = txt;
        RatingGive = ratingGive;
        this.user = user;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Float getRatingGive() {
        return RatingGive;
    }

    public void setRatingGive(Float ratingGive) {
        RatingGive = ratingGive;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
