package com.canach.commonutils.exception.enums;

public enum ErrorEnum {

    BINDING_VALIDATION(400, "binding error %s"),
    INVALID_INPUT(400, "invalid input: '%s'"),
    VALIDATION_ERROR(400, "validation error: '%s'"),
    BUSINESS_ERROR(400, "business error: '%s'"),
//    INVALID_QUERY_IDENTIFYING_VALUE(400, "valid identifiers types are: {%s}"),
//    INVALID_QUERY_IDENTIFYING_FORMAT(400, "identifier format must be: identifierType|value"),
    AUTHENTICATION_FAILED(401, "invalid credentials"),
    FORBIDDEN(403, "operation not allowed"),
    RESOURCE_NOT_FOUND(404, "%s with %s not found"),
    METHOD_NOT_SUPPORTED(405, "method not supported"),
    RESOURCE_ALREADY_EXISTS(409, "%s with id %s already exists"),
    RESOURCE_IN_USE(409, "%s %s cannot be deleted: %s"),
    INTERNAL_ERROR(500, "internal error occurred");

    private final int httpStatus;
    private final String description;

    ErrorEnum(int httpStatus, String description) {
        this.httpStatus = httpStatus;
        this.description = description;
    }

    public int getHttpStatus() { return httpStatus; }
    public String getDescription() { return description; }
}
