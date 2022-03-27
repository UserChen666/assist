package com.engineer.assist.exception;

public class ServerException extends Exception{

    private Integer httpCode;

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message,Integer httpCode) {
        super(message);
        this.httpCode = httpCode;
    }

    public Integer getHttpCode() {
        return httpCode;
    }
}
