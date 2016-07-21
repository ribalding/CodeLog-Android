package com.example.guest.codelog.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by Guest on 7/20/16.
 */
@Parcel
public class Project {
    private String projectName;
    private ArrayList<Post> posts;
    private String userID;

    public Project(String projectName, String userID){
        this.projectName = projectName;
        this.userID = userID;
    }

    public Project(){}

    public String getProjectName() {
        return projectName;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public String getUserID() {
        return userID;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void addPostToProject(Post post){
        this.posts.add(post);
    }
}
