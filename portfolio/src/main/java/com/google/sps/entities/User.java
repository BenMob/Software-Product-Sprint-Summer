package com.google.sps.entities;

public class User{
    private long id;
    private String username;
    private long numberOfComments;

    public User(long id, String username, long numberOfComments){
        this.id = id;
        this.username = username;
        this.numberOfComments = numberOfComments;
    } 
}