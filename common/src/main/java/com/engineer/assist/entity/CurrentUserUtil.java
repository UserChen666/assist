package com.engineer.assist.entity;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class CurrentUserUtil {

    private static ThreadLocal<User> loginUser =  new ThreadLocal<>();

    public static User getUser(){
        return loginUser.get();
    }

    public static String getName(){
        User u = loginUser.get();
        if(u != null) {
            return u.getUserName();
        }
        return "unknow";
    }

    public static void setUser(User user){
        loginUser.set(user);
    }

    public static void removeUserInfo() {
        loginUser.remove();
    }

    public static String currentToken() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        if(request == null) {
            return "";
        }

        return request.getHeader("token");
    }
}
