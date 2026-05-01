package com.canach.commonutils.exception;


import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.canach.commonutils.exception.enums.ErrorEnum;
import com.canach.commonutils.response.ErrorResponse;
//import org.springframework.data.crossstore.ChangeSetPersister;

//@Getter
//public class ResourceNotFoundException extends ChangeSetPersister.NotFoundException {
//
//    private final ErrorResponse errorResponse;
//
//    public ResourceNotFoundException(ErrorEnum errorEnum, String errorMessage, ErrorCategoryEnum errorCategory) {
//        super();
//        errorResponse = ErrorResponse.of(errorEnum.name(), errorMessage, errorCategory);
//    }
//}

public class ResourceNotFoundException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public ResourceNotFoundException(ErrorEnum errorEnum, String errorMessage, ErrorCategoryEnum errorCategory) {
        super(errorMessage);
        errorResponse = ErrorResponse.of(errorEnum.name(), errorMessage, errorCategory);
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}