package com.pinky.sharerecipebook.models;/* Created by Shay Mualem 31/08/2021 */

public class Comment {
    private String txt;
    //    private Float ratingGive;
    private User user;

    public Comment(String txt, User user) {
        this.txt = txt;
//        this.ratingGive = ratingGive;
        this.user = user;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
