package com.canach.commonutils.exception;

import lombok.Getter;

@Getter
public class JwtMissingTokenException extends RuntimeException {


    public JwtMissingTokenException(String errorMessage)  {
        super(errorMessage);
    }
}