package com.canach.commonutils.exception;


import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.canach.commonutils.exception.enums.ErrorEnum;
import com.canach.commonutils.response.ErrorResponse;

public class InternalApiException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public InternalApiException(ErrorEnum errorCode, String errorMessage, ErrorCategoryEnum errorCategory) {
        super(errorMessage);
        errorResponse = ErrorResponse.of(errorCode, errorMessage, errorCategory);
    }

    protected ErrorResponse getErrorResponse() {
        return errorResponse;
    }

}