package com.example.clown.dailyzhihu.bean;

/**
 * Created by Clown on 2016/8/7.
 */
public class Comment {

    public String author;
    public String content;
    public String likes;
    public String time;
    public String avatar;

    public Comment(String author, String content, String likes, String time, String avatar) {
        this.author = author;
        this.content = content;
        this.likes = likes;
        this.time = time;
        this.avatar = avatar;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
