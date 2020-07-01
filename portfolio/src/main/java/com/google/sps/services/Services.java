package com.google.sps.services;
/********************************************
 * This class will host all utility methods
 **/
 
 import com.google.gson.Gson;
 import com.google.appengine.api.datastore.DatastoreService;
 import com.google.appengine.api.datastore.DatastoreServiceFactory;
 import com.google.appengine.api.datastore.Entity;

 public class Services{

   /**********************************************
    * Converts a Java Object into JSON String
    * @param : A Java Object
    * @return : Json String 
    **/
    public static String toJson(Object o){
        return new Gson().toJson(o);
    }

   /*******************************************************************
    * This method takes an Entity and saves it in dataStore
    * @param: com.google.appengine.api.datastore.Entity
    */
    public static void save(Entity entity){
       DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
       datastore.put(entity);
    }
 }

