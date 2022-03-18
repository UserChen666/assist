package com.engineer.assist.config;

import com.engineer.assist.resp.Resp;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class WebConfig {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Resp<Object> exceptionHandler(Exception e){

        return Resp.buildFail(e.getMessage());
    }
}
