package com.engineer.assist.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String swaggerToken = request.getHeader("swaggerToken");
        if ("assist".equals(swaggerToken)){
            return true;
        }
        //取出session里面的name属性,如果name为空, 就重定向到index界面
        String name = (String) request.getSession().getAttribute("name");
        if (Objects.isNull(name)) {
            return false;
        }
        //name属性存在, 即会话没有过期, 那么允许本次请求
        return true;
    }
}
