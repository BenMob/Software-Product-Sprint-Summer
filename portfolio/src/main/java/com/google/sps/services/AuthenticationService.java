package com.google.sps.services;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.sps.dtos.Authentication;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public final class AuthenticationService extends Services{

    /*******************************************************************
    * This method creates a Comment entity and populates all of its
    * properties from request parameters and userservice.
    *
    * @param: javax.servlet.http.HttpServletRequest
    * @return: com.google.appengine.api.datastore.Entity (User)
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
    public static Authentication getAuthenticationInfo(UserService userservice, String destination){
        if(userservice.isUserLoggedIn()){
            return new Authentication(userservice.createLogoutURL(destination), true);
        }else{
            return new Authentication(userservice.createLoginURL(destination), false);
        }
    }
}