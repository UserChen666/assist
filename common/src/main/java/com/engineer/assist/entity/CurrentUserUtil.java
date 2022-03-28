package com.engineer.assist.entity;


public class CurrentUserUtil {

    private static ThreadLocal<User> loginUser =  new ThreadLocal<>();

    public static User getUser(){
        return loginUser.get();
    }

    public static void setUser(User user){
        loginUser.set(user);
    }
}
