package com.canach.commonutils.exception;

public class JwtInvalidTokenException extends RuntimeException {

    public JwtInvalidTokenException(String errorMessage) {
        super(errorMessage);
    }
}