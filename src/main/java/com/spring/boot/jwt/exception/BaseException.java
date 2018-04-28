package com.spring.boot.jwt.exception;

public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
