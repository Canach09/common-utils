package com.canach.commonutils.exception;

import lombok.Getter;

@Getter
public class JwtInvalidTokenException extends RuntimeException {

    public JwtInvalidTokenException(String errorMessage) {
        super(errorMessage);
    }
}