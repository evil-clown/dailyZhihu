package com.example.clown.dailyzhihu.bean;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Clown on 2016/5/15.
 */
public class NewsLab {

    private static NewsLab sNewsLab;
    private ArrayList<News> mStories;
    private ArrayList<News> mTopStories;
    private ArrayList<String> mDate;
    private ArrayList<Integer> mItemCount;
    private String todayDate;
    private Context mAppContext;

    private NewsLab(Context appContext){
        this.mAppContext = appContext;
        this.mStories = new ArrayList<>();
        this.mTopStories = new ArrayList<>();
        this.mDate = new ArrayList<>();
        this.mItemCount = new ArrayList<>();
    }

    public static NewsLab get(Context c){
        if(sNewsLab == null){
            sNewsLab = new NewsLab(c.getApplicationContext());
        }
        return sNewsLab;
    }

    public ArrayList<News> getStories() {
        return mStories;
    }

    public void setStories(ArrayList<News> stories) {
        this.mStories = stories;
    }

    public ArrayList<News> getTopStories() {
        return mTopStories;
    }

    public void setTopStories(ArrayList<News> TopStories) {
        this.mTopStories = TopStories;
    }

    public ArrayList<String> getDate() {
        return mDate;
    }

    public void setDate(ArrayList<String> date) {
        this.mDate = date;
    }

    public ArrayList<Integer> getItemCount() {
        return mItemCount;
    }

    public void setItemCount(ArrayList<Integer> ItemCount) {
        this.mItemCount = ItemCount;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }
}
