package com.pinky.sharerecipebook.models;/* Created by Shay Mualem 31/08/2021 */

public class Comment {
    private String txt;
    private String userId;
    private String postDate;

    public Comment(String txt, String userId, String postDate)  {
        this.txt = txt;
        this.userId = userId;
        this.postDate = postDate;
    }

    public Comment() {
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }


}
