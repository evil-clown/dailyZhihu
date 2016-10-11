package com.example.clown.dailyzhihu.bean;

/**
 * Created by Clown on 2016/6/7.
 */
public class NewsExtra {
    private String long_comments;
    private String popularity;
    private String short_comments;
    private String comments;

    public NewsExtra(String long_comments, String popularity, String short_comments, String comments) {
        this.long_comments = long_comments;
        this.popularity = popularity;
        this.short_comments = short_comments;
        this.comments = comments;
    }

    public String getLong_comments() {
        return long_comments;
    }

    public void setLong_comments(String long_comments) {
        this.long_comments = long_comments;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getShort_comments() {
        return short_comments;
    }

    public void setShort_comments(String short_comments) {
        this.short_comments = short_comments;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
