package com.canach.commonutils.exception;


import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.canach.commonutils.exception.enums.ErrorEnum;

public class BusinessException extends InternalApiException {

        public BusinessException(ErrorEnum errorEnum, String message, ErrorCategoryEnum errorCategory) {
        super(errorEnum.name(), message, errorCategory);
    }
}