package com.google.sps.dtos;

public class Authentication{
    private String link;
    private boolean userIsLoggedIn;

    public Authentication(String link, boolean userIsLoggedIn){
        this.link = link;
        this.userIsLoggedIn = userIsLoggedIn;
    }
}