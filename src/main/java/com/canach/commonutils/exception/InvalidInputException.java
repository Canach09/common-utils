package com.canach.commonutils.exception;


import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.canach.commonutils.exception.enums.ErrorEnum;

public class InvalidInputException extends InternalApiException {

        public InvalidInputException(ErrorEnum errorEnum, String message, ErrorCategoryEnum errorCategory) {
        super(errorEnum.name(), message, errorCategory);
    }
}