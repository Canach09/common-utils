package com.canach.commonutils.exception;

public class JwtUserNotFoundException extends RuntimeException {

    public JwtUserNotFoundException(String errorMessage)  {
        super(errorMessage);
    }
}