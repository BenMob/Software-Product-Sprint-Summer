 package com.google.sps.entities;
/**
 * This class models a comment entity 
 **/
 public final class Comment{
     private final long id;
     private final String author;
     private final String comment;
     private final long timestamp;

     public Comment(long id, String author, String comment, long timestamp){
         this.id = id;
         this.author = author;
         this.comment = comment;
         this.timestamp = timestamp;
     }
 }