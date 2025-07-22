package com.canach.commonutils.exception.handler;

import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.canach.commonutils.exception.enums.ErrorEnum;
import com.canach.commonutils.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.resource.NoResourceFoundException;


public abstract class BaseGlobalExceptionHandler {

    protected ResponseEntity<ErrorResponse> handleNotFound(NoResourceFoundException ex) {
        final var error = new ErrorResponse(
                ErrorEnum.RESOURCE_NOT_FOUND.name(),
                ex.getMessage(),
                ErrorCategoryEnum.VALIDATION
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        final var error = new ErrorResponse(
                ErrorEnum.METHOD_NOT_SUPPORTED.name(),
                ex.getMessage() + " for this path",
                ErrorCategoryEnum.VALIDATION
        );
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    protected ResponseEntity<ErrorResponse> handleMessageNotReadable(HttpMessageNotReadableException ex) {
        final var error = new ErrorResponse(
                ErrorEnum.INVALID_INPUT.name(),
                ex.getMessage(),
                ErrorCategoryEnum.VALIDATION
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        final var error = new ErrorResponse(
                ErrorEnum.INTERNAL_ERROR.name(),
                ex.getMessage(),
                ErrorCategoryEnum.INTERNAL
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ResponseEntity<ErrorResponse> handleNotFound(HttpRequestMethodNotSupportedException ex) {
//        final var error = new ErrorResponse(ErrorEnum.METHOD_NOT_SUPPORTED.name(), ex.getMessage() + " for this path",
//                ErrorCategoryEnum.VALIDATION);
//        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
//    }

}
