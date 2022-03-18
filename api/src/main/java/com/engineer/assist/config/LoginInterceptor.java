package com.engineer.assist.config;

import com.engineer.assist.entity.User;
import com.engineer.assist.util.RestUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();

        if(ArrayUtils.isEmpty(cookies)){
            //unlogin
            return false;
        }

        List<Cookie> sessionIds = Arrays.stream(cookies).filter(k -> "assistSessionId".equals(k.getName())).collect(Collectors.toList());

        if(CollectionUtils.isEmpty(sessionIds)) {
            //unlogin
            return false;
        }

        Cookie cookie = sessionIds.get(0);
        String sessionKey = cookie.getValue();
        User usrInfo = new User();
        usrInfo.setUserName(sessionKey);
        RestUtil.setUserInfo(usrInfo);
        return true;
    }
}
