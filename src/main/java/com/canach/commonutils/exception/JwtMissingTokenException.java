package com.canach.commonutils.exception;

public class JwtMissingTokenException extends RuntimeException {

    public JwtMissingTokenException(String errorMessage)  {
        super(errorMessage);
    }
}