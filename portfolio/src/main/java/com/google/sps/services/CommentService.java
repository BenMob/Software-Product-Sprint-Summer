package com.google.sps.services;

/**********************************************
 * This class contains all the service methods
 * to process a Comment Object.
 *
 * Child class to: com.google.sps.services.CommentServices
 *
 * TODO: Consider doing exeption handling where appropriate
 */
 import com.google.appengine.api.datastore.Entity;
 import com.google.appengine.api.datastore.DatastoreService;
 import com.google.appengine.api.datastore.DatastoreServiceFactory;
 import com.google.appengine.api.datastore.PreparedQuery;
 import com.google.appengine.api.datastore.Query;
 import com.google.appengine.api.datastore.Query.SortDirection;
 import com.google.sps.entities.Comment;
 import java.util.Enumeration;
 import javax.servlet.http.HttpServletRequest;
 import java.util.ArrayList;

 public final class CommentService extends Services {

    /*********************************************************************** 
    * This method extracts data from a newly queried comment entity and  
    * and creates a com.google.sps.entities.Comment Object using that data.
    *
    * @param: com.google.appengine.api.datastore.Entity;
    * @return: com.google.sps.entities.Comment
    ***/
     public static Comment getComment(Entity entity){
        Comment comment = new Comment(
            entity.getKey().getId(),
            (String)entity.getProperty("author"),
            (String)entity.getProperty("comment"),
            (long)entity.getProperty("timestamp")
        );
        return comment;
     }

     /*******************************************************************
      * This method creates a Comment entity and populates all of its
      * properties from request parameters.
      *
      * @param: javax.servlet.http.HttpServletRequest
      * @return: com.google.sps.entities.Comment
      */
     public static Entity createEntity(HttpServletRequest request){
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
      * @param: com.google.sps.entities.Comment
      */
      public static void save(Entity commentEntity){
          DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
          datastore.put(commentEntity);
      }

       /********************************************************************
      * Queries all the Comments from datatsore in ascending order based on
      * timestamps.
      *
      * @return: java.util.ArrayList<Comment> 
      */
      public static ArrayList<Comment> getAllComments(){
          Query query = new Query("Comment").addSort("timestamp", SortDirection.DESCENDING);
          DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
          PreparedQuery results = dataStore.prepare(query);

          ArrayList<Comment> comments = new ArrayList<>();
          for(Entity entity: results.asIterable()){
              comments.add(getComment(entity)); 
          }
          return comments;
      }
 }

 