package com.engineer.assist.util;

import com.engineer.assist.entity.User;

public class CurrentUserUtil {

    private static ThreadLocal<User> loginUser =  new ThreadLocal<>();

    public User getUser(){
        return loginUser.get();
    }

    public void setUser(User user){
        loginUser.set(user);
    }
}
