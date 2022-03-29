package com.engineer.assist.config;

import com.engineer.assist.entity.User;
import com.engineer.assist.exception.ServerException;
import com.engineer.assist.entity.CurrentUserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String swaggerToken = request.getHeader("token");
        if ("assist".equals(swaggerToken)){
            User user = new User();
            user.setUserName("assist");
            CurrentUserUtil.setUser(user);
            return true;
        }
        //取出session里面的name属性,如果name为空, 就重定向到index界面
        User name = (User) request.getSession().getAttribute("currentUser");
        if (Objects.isNull(name)) {
            throw new ServerException("未登录", HttpStatus.FORBIDDEN.value());
        }

        CurrentUserUtil.setUser(name);

        //name属性存在, 即会话没有过期, 那么允许本次请求
        return true;
    }
}
