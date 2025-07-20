package com.canach.commonutils.exception;

import lombok.Getter;

@Getter
public class JwtUserNotFoundException extends RuntimeException {

    public JwtUserNotFoundException(String errorMessage)  {
        super(errorMessage);
    }
}