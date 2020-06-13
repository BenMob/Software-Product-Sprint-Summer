package com.google.sps.services;
/********************************************
 * This class will host all utility methods
 **/

 import com.google.gson.Gson;

 public class Services{

   /**********************************************
    * Converts a Java Object into JSON String
    * @param : A Java Object
    * @return : Json String 
    **/
    public static String toJson(Object o){
        Gson gson = new Gson();
        return gson.toJson(o);
    }
 }

