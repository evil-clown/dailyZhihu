package com.example.clown.dailyzhihu.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Clown on 2016/5/14.
 */
public class News {

    private String title;
    private ArrayList<String> images;
    private String type;
    private String id;
    private String image;

    public News(String title, ArrayList<String> images, String type, String id){
        this.title = title;
        this.images = images;
        this.type =type;
        this.id = id;
    }

    public News(String title, String image, String type, String id){
        this.title = title;
        this.image = image;
        this.type = type;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Title:" + getTitle() +", Type: " + getType() +", ID:" + getId();
    }
}