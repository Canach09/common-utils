package com.canach.commonutils.exception;

import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.canach.commonutils.exception.enums.ErrorEnum;
import com.canach.commonutils.response.ErrorResponse;

public class ResourceNotFoundException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public ResourceNotFoundException(ErrorEnum errorEnum, String errorMessage, ErrorCategoryEnum errorCategory) {
        super(errorMessage);
        errorResponse = ErrorResponse.of(errorEnum, errorMessage, errorCategory);
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}