package com.engineer.assist.entity;


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
}
