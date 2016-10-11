package com.example.clown.dailyzhihu.bean;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Clown on 2016/8/7.
 */
public class CommentLab {

    private ArrayList<Comment> comments;

    public CommentLab(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
