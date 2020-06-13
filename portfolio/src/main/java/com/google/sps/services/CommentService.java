package com.google.sps.services;

/**********************************************
 * This class contains all the service methods
 * to process a Comment Object.
 *
 * Child class to: com.google.sps.services.CommentServices
 */
 import com.google.appengine.api.datastore.Entity;
 import com.google.appengine.api.datastore.DatastoreService;
 import com.google.appengine.api.datastore.DatastoreServiceFactory;
 import com.google.sps.entities.Comment;
 import java.util.Enumeration;
 import javax.servlet.http.HttpServletRequest;

 public final class CommentService extends Services{

     /*******************************************************************
      * This method creates a Comment entity and populates all of its
      * properties from request parameters.
      *
      * @param: com.google.sps.entities.Comment
      * @param: javax.servlet.http.HttpServletRequest
      */
     public static Entity getEntity(HttpServletRequest request){
         Entity commentEntity = new Entity("Comment");
         Enumeration<String> parameterNames = request.getParameterNames();

         while(parameterNames.hasMoreElements()){
             String name = parameterNames.nextElement();
             String property = request.getParameter(name);
             commentEntity.setProperty(name, property);
         }
         commentEntity.setProperty("timestamp", (long)System.currentTimeMillis());

         return commentEntity;
     }

     /*******************************************************************
      * This method takes a Comment Entity and saves in dataStore
      *
      * @param: com.google.sps.entities.Comment
      */
      public static void save(Entity commentEntity){
          DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
          datastore.put(commentEntity);
      }
 }

 