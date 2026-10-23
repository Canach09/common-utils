package com.canach.commonutils.exception;

import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.canach.commonutils.exception.enums.ErrorEnum;

public class UnauthorizedException extends InternalApiException {

        public UnauthorizedException(ErrorEnum errorEnum, String message, ErrorCategoryEnum errorCategory) {
        super(errorEnum.name(), message, errorCategory);
    }
}