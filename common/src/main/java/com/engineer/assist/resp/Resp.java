package com.engineer.assist.resp;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Data
@Accessors(chain = true)
public class Resp<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> Resp<T> buildSuccess(T t){
        Resp<T> resp = new Resp<T>();

        resp.setCode(HttpStatus.OK.value());
        resp.setData(t);

        return resp;
    }

    public static <T> Resp<T> buildSuccess(){
        Resp<T> resp = new Resp<T>();

        resp.setCode(HttpStatus.OK.value());

        return resp;
    }

    public static <T> Resp<T> buildFail(String msg){
        Resp<T> resp = new Resp<T>();

        resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        resp.setMsg(msg);

        return resp;
    }
}
