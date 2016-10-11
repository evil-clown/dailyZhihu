package com.example.clown.dailyzhihu.bean;

/**
 * Created by Clown on 2016/5/31.
 */
public class Bean {

    private News mNews;
    private String mDate;
    private int mType;

    public Bean(News news, int type){
        this.mNews = news;
        this.mType = type;
    }

    public Bean(String date, int type){
        this.mDate = date;
        this.mType = type;
    }

    public News getStory() {
        return mNews;
    }

    public void setStory(News news) {
        this.mNews = news;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public int getType() {
        return mType;
    }
}
