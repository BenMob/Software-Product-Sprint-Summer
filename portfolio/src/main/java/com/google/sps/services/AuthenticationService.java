package com.google.sps.services;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.sps.dtos.Authentication;
import com.google.sps.entities.User;
import com.google.sps.services.AuthenticationService;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;

public final class AuthenticationService extends Services{

   /**************************************************************
    * This method creates a com.google.sps.entities.User object given 
    * a User entity object.
    *
    * @param: com.google.appengine.api.datastore.Entity
    * @return: com.google.sps.entities.User
    *******/
    public static User getUserObject(Entity userEntity){
        User user = new User(
            userEntity.getKey().getId(),
            (String)userEntity.getProperty("username"),
            (String)userEntity.getProperty("email"),
            (long)userEntity.getProperty("number_of_comments")
        );
        return user;
    }
   
    /*******************************************************************
    * This method creates a Comment entity and populates all of its
    * properties from request parameters and userservice.
    *
    * @param: javax.servlet.http.HttpServletRequest
    * @return: com.google.appengine.api.datastore.Entity. Type: User
    */
    public static Entity createUserEntity(HttpServletRequest request, UserService userService){
        Entity userEntity = new Entity("User");
        userEntity.setProperty("id", (String)userService.getCurrentUser().getUserId());
        userEntity.setProperty("email", (String)userService.getCurrentUser().getEmail());
        Enumeration<String> parameterNames = request.getParameterNames();

        while(parameterNames.hasMoreElements()){
             String name = parameterNames.nextElement();
             String property = request.getParameter(name);
             userEntity.setProperty(name, property);
        }
        userEntity.setProperty("number_of_comments", 0); 
        return userEntity;
    }

    /*******************************************************************
    * This method creates a com.google.sps.dtos.Authentication object with
    * its field filled base on wether the user is logged in or logged out.
    *
    * @param: com.google.appengine.api.users.UserService
    * @param: String -> Link to redirect to after logging in or logging out. 
    * @return: com.google.sps.dtos.Authentication
    */
    public static Authentication getAuthenticationInfo(UserService userservice){
        if(userservice.isUserLoggedIn()){
            return new Authentication(userservice.createLogoutURL("/"), true);
        }else{
            return new Authentication(userservice.createLoginURL("/"), false);
        }
    }

    /********************************************************
     * This method extract a single User from Datastore.
     * @param: String user id
     * @return: com.google.sps.entities.User
     * If a match is found, the corresponding User object is returned
     * otherwise, an empty User object with id = 0 is returned.
     *****/
    public static User queryUserById(String userId) throws TooManyResultsException {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query("User").addFilter("id", FilterOperator.EQUAL, userId);
        PreparedQuery result = datastore.prepare(query);
        Entity currentUser = result.asSingleEntity();

        if(Objects.isNull(currentUser)){
            return new User();
        }
        
        return getUserObject(currentUser);
    }
}