package com.appentus.ezyridedriver;

public class RatingModel {


    private String name, time, comment, ratings;


    public RatingModel(String name, String time, String comment, String ratings) {
        this.name = name;
        this.time = time;
        this.comment = comment;
        this.ratings = ratings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }
}
