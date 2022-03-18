package com.engineer.assist.util;

import com.engineer.assist.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class RestUtil {
    private static final Logger log = LoggerFactory.getLogger(RestUtil.class);
    private static ThreadLocal<User> userInfo = new ThreadLocal();
    private static ThreadLocal<User> originalUserInfo = new ThreadLocal();

    public RestUtil() {
    }

    public static User getUserInfo(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            User userInfo = new User();
            Cookie[] var3 = cookies;
            int var4 = cookies.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Cookie c = var3[var5];
                if ("name".equals(c.getName())) {
                    userInfo.setUserName(c.getValue());
                }

            }

            if (userInfo.getUserName() == null) {
                return null;
            } else {
                return userInfo;
            }
        } else {
            return null;
        }
    }

    public static void removeUserInfo() {
        userInfo.remove();
        originalUserInfo.remove();
    }
    public static void setUserInfo(User usrInfo) {
        if (null != usrInfo) {
            userInfo.set(usrInfo);
            if (originalUserInfo.get() == null) {
                User originalUser = new User();
                originalUser.setUserName(usrInfo.getUserName());
                originalUserInfo.set(originalUser);
            }
        }

    }

}
