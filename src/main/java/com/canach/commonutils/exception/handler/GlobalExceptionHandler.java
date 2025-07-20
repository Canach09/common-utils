package com.canach.commonutils.exception.handler;

import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.canach.commonutils.exception.enums.ErrorEnum;
import com.canach.commonutils.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NoResourceFoundException ex) {
        final var error = new ErrorResponse(ErrorEnum.RESOURCE_NOT_FOUND.name(), ex.getMessage(), ErrorCategoryEnum.VALIDATION);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(HttpRequestMethodNotSupportedException ex) {
        final var error = new ErrorResponse(ErrorEnum.METHOD_NOT_SUPPORTED.name(), ex.getMessage() + " for this path",
                ErrorCategoryEnum.VALIDATION);
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(HttpMessageNotReadableException ex) {
        final var error = new ErrorResponse(ErrorEnum.INVALID_INPUT.name(), ex.getMessage(),
                ErrorCategoryEnum.VALIDATION);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ResponseEntity<ErrorResponse> handleNotFound(HttpRequestMethodNotSupportedException ex) {
//        final var error = new ErrorResponse(ErrorEnum.METHOD_NOT_SUPPORTED.name(), ex.getMessage() + " for this path",
//                ErrorCategoryEnum.VALIDATION);
//        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
//    }

}
