package com.google.sps.entities;

public class User{
    private long id;
    private String username;
    public String email;
    private long numberOfComments;

    public User(){};
    public User(long id, String username, String email ,long numberOfComments){
        this.id = id;
        this.username = username;
        this.email = email;
        this.numberOfComments = numberOfComments;
    }

    public long getId(){
        return this.id;
    } 

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public long getNumberOfComments(){
        return this.numberOfComments;
    }

    @Override
    public String toString(){
        return " id: " + this.id + "\n username: " + this.username + "\n email: " + this.email + "\n number of comments: " + this.numberOfComments + "\n";
    }
}