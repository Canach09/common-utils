package com.canach.commonutils.rest;

import com.canach.commonutils.exception.BindingResultException;
import com.canach.commonutils.exception.InternalApiException;
import com.canach.commonutils.exception.ResourceNotFoundException;
import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.canach.commonutils.exception.enums.ErrorEnum;
import com.canach.commonutils.response.ErrorResponse;
import com.canach.commonutils.tracing.LogLevel;
import com.canach.commonutils.tracing.TracingUtils;
import com.canach.commonutils.utils.LogUtils;
import io.opentelemetry.api.trace.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Slf4j
public abstract class CommonManager {

    protected Tracer tracer;

    protected void validateBindingResult(BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            final FieldError firstError = bindingResult.getFieldErrors().get(0);
            final var errorDescription = String.format("field '%s': %s",
                    firstError.getField(),
                    firstError.getDefaultMessage()
            );

            throw new BindingResultException(ErrorEnum.BINDING_VALIDATION, errorDescription, ErrorCategoryEnum.VALIDATION);
        }
    }

    protected ResponseEntity<Object> handleInvocationException(Exception exc) {

        TracingUtils.logAndTraceAsJsonWithNewSpan(LogLevel.ERROR, tracer, LogUtils.GetStackTraceAsString(exc));
        ErrorResponse errorResponse;
        int statusCode;

//        if (exc instanceof PasswordManagerException pme) {
//            errorResponse = pme.getErrorResponse();
//            statusCode = HttpStatus.BAD_REQUEST.value();
//        } else

//        if (exc instanceof BadCredentialsException bce) {
//            errorResponse = new ErrorResponse(ErrorEnum.INVALID_CREDENTIALS.name(),
//                    bce.getMessage(), ErrorCategoryEnum.AUTHORIZATION);
//            statusCode = HttpStatus.UNAUTHORIZED.value();
//        } else

            if (exc instanceof InternalApiException iae) {
            errorResponse = iae.getErrorResponse();
            statusCode = HttpStatus.BAD_REQUEST.value();
        } else if (exc instanceof ResourceNotFoundException rnf) {
            errorResponse = rnf.getErrorResponse();
            statusCode = HttpStatus.NOT_FOUND.value();
        } else {
            errorResponse = new ErrorResponse(ErrorEnum.INTERNAL_ERROR.name(),
                    exc.getMessage(), ErrorCategoryEnum.INTERNAL);
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }

        TracingUtils.logAndTraceAsJsonWithNewSpan(LogLevel.ERROR, tracer, errorResponse);
        return ResponseEntity.status(statusCode).body(errorResponse);
    }

    protected void logInitialExecution(String method, Tracer tracer) {
        TracingUtils.logAndTraceWithNewSpan(LogLevel.INFO, tracer,
                String.format("Starting execution of %s", method));
    }

}
