package com.engineer.assist.service.impl;

import com.engineer.assist.entity.User;
import com.engineer.assist.service.IUserService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class SessionService implements InitializingBean {

    private Cache<String, User> cache;

    @Autowired
    @Lazy
    private IUserService userService;

    @Override
    public void afterPropertiesSet() throws Exception {
        cache = CacheBuilder
                .newBuilder()
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .maximumSize(50).build();
    }

    public User get(String key) throws Exception {
        return cache.getIfPresent(key);
    }

    public void put(String key,User value){
        cache.put(key,value);
    }
}
