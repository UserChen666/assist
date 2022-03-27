package com.engineer.assist.config;

import com.engineer.assist.exception.ServerException;
import com.engineer.assist.resp.Resp;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class WebConfig {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Resp<Object> exceptionHandler(Exception e){

        if (e instanceof ServerException) {
            ServerException se = (ServerException) e;

            return Resp.buildFail(se.getMessage(),se.getHttpCode());
        }
        return Resp.buildFail(e.getMessage());
    }
}
