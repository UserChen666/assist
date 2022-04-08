package com.engineer.assist.config;

import com.engineer.assist.exception.ServerException;
import com.engineer.assist.resp.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class WebConfig {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Resp<Object> exceptionHandler(Exception e){
        log.error(e.getMessage());
        if (e instanceof ServerException) {
            ServerException se = (ServerException) e;

            Integer httpCode = se.getHttpCode();
            if(httpCode == null) {
                httpCode = 300;
            }
            return Resp.buildFail(se.getMessage(),httpCode);
        }
        return Resp.buildFail(e.getMessage());
    }
}
