 package com.google.sps.entities;
/**
 * This class models a comment entity 
 **/
 public final class Comment{
     private final long id;
     private final long userId;
     private final String username;
     private final String comment;
     private final long timestamp;

     public Comment(long id, long userId, String username, String comment, long timestamp){
         this.id = id;
         this.userId = userId;
         this.username = username;
         this.comment = comment;
         this.timestamp = timestamp;
     }
 }