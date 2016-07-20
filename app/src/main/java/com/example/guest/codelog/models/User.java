package com.example.guest.codelog.models;

import java.util.ArrayList;

/**
 * Created by Guest on 7/20/16.
 */
public class User {
    private String name;
    private String email;
    private String password;
    private ArrayList<Project> projects;

    public User(){}

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }
}
