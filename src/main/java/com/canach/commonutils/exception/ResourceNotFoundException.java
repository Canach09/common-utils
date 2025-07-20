package com.canach.commonutils.exception;


import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.canach.commonutils.exception.enums.ErrorEnum;
import com.canach.commonutils.response.ErrorResponse;
import lombok.Getter;
import org.springframework.data.crossstore.ChangeSetPersister;

@Getter
public class ResourceNotFoundException extends ChangeSetPersister.NotFoundException {

    private final ErrorResponse errorResponse;

    public ResourceNotFoundException(ErrorEnum errorEnum, String errorMessage, ErrorCategoryEnum errorCategory) {
        super();
        errorResponse = new ErrorResponse(errorEnum.name(), errorMessage, errorCategory);
    }
}