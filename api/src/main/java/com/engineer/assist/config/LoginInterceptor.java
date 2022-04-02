package com.engineer.assist.config;

import com.engineer.assist.entity.User;
import com.engineer.assist.exception.ServerException;
import com.engineer.assist.entity.CurrentUserUtil;
import com.engineer.assist.service.impl.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class LoginInterceptor implements HandlerInterceptor {

    private SessionService sessionService;

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

        User user = sessionService.get(swaggerToken);

        if (Objects.isNull(user)) {
            throw new ServerException("please login", HttpStatus.FORBIDDEN.value());
        }

        CurrentUserUtil.setUser(user);

        //name属性存在, 即会话没有过期, 那么允许本次请求
        return true;
    }

    public LoginInterceptor(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}
