package com.example.guest.codelog.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 7/20/16.
 */
@Parcel
public class Post {
    private String title;
    private String postBody;
    private String projectID;

    public Post(){}

    public Post(String title, String postBody, String projectID){
        this.postBody = postBody;
        this.title = title;
        this.projectID = projectID;
    }

    public String getTitle() {
        return title;
    }

    public String getPostBody() {
        return postBody;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
}
