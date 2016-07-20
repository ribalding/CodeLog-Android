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

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
