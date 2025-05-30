package com.example.myapplication.Model;

import java.util.ArrayList;
import java.util.List;
public class PostModel {
    public int id;
    public String content;
    public String userName;
    public String userAvatar;
    public String image;
    public int likeCount;
    public List<String> comments;
    public String timePosted;
    public String avatarUri;
    public List<String> imageList;
    public int commentCount;
    public boolean isLiked = false;
    public PostModel(int id, String content, String userName, String userAvatar, String image, int likeCount,boolean isLiked) {
        this.id = id;
        this.content = content;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.image = image;
        this.likeCount = likeCount;
        this.comments = new ArrayList<>();
        this.isLiked = isLiked;
    }
    public int getId() {
        return id;
    }
    public String getUserAvatar() {
        return userAvatar;
    }
    public String getContent() {
        return content;
    }
    public String getUserName() {
        return userName;
    }
    public List<String> getComments() {
        return comments;
    }
    public int getLikeCount() {
        return likeCount;
    }
    public String getImage() {
        return image;
    }
}

