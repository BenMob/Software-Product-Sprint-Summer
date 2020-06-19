package com.google.sps.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.sps.services.AuthenticationService;
import com.google.sps.dtos.Authentication;
import com.google.sps.entities.User;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@WebServlet("/authentication")
public class AuthenticationServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        UserService userService = UserServiceFactory.getUserService();
        Authentication authInfo = AuthenticationService.getAuthenticationInfo(userService, "/index.html");
        response.setContentType("application/json");
        response.getWriter().println(AuthenticationService.toJson(authInfo));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        UserService userService = UserServiceFactory.getUserService();
        if(userService.isUserLoggedIn()){ // Save User Info
            final Entity userEntity = AuthenticationService.createUserEntity(request, userService);
            AuthenticationService.save(userEntity);
            response.sendRedirect("/index.html#comments");
        }else{
            response.sendRedirect(userService.createLoginURL("/register.html"));
        }
    }
}