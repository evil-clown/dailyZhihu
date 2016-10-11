package com.example.clown.dailyzhihu.bean;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Clown on 2016/5/14.
 */
public class DataWrapper {

    public String date;
    public ArrayList<News> stories;
    public ArrayList<News> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<News> getStories() {
        return stories;
    }

    public void setStories(ArrayList<News> stories) {
        this.stories = stories;
    }

    public ArrayList<News> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(ArrayList<News> top_stories) {
        this.top_stories = top_stories;
    }

    public static DataWrapper fromJson(String string){
        return new Gson().fromJson(string, DataWrapper.class);
    }

    public String toString(){
        return new Gson().toJson(this);
    }
}