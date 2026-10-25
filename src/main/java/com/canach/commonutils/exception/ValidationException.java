package com.canach.commonutils.exception;

import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.canach.commonutils.exception.enums.ErrorEnum;

public class ValidationException extends InternalApiException {

        public ValidationException(ErrorEnum errorEnum, String message, ErrorCategoryEnum errorCategory) {
        super(errorEnum, message, errorCategory);
    }
}