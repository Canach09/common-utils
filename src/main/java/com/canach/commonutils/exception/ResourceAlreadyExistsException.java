package com.canach.commonutils.exception;


import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.canach.commonutils.exception.enums.ErrorEnum;

public class ResourceAlreadyExistsException extends InternalApiException {

        public ResourceAlreadyExistsException(ErrorEnum errorEnum, String message, ErrorCategoryEnum errorCategory) {
        super(errorEnum.name(), message, errorCategory);
    }
}