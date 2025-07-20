package com.canach.commonutils.exception;


import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.canach.commonutils.response.ErrorResponse;
import lombok.Getter;

@Getter
public class InternalApiException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public InternalApiException(String errorCode, String errorMessage, ErrorCategoryEnum errorCategory) {
        super(errorMessage);
        errorResponse = new ErrorResponse(errorCode, errorMessage, errorCategory);
    }

}