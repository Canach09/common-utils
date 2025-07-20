package com.canach.commonutils.exception.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    RESOURCE_ALREADY_EXISTS("%s with id %s already exists"),
    BINDING_VALIDATION("binding error %s"),
    INVALID_QUERY_IDENTIFYING_VALUE("Valid identifiers types are: {%s}"),
    INVALID_QUERY_IDENTIFYING_FORMAT("identifier format must be identifierType|value"),
    INVALID_CREDENTIALS("invalid credentials"),
    INTERNAL_ERROR("internal error occurred"),
    RESOURCE_NOT_FOUND("%s with %s not found"),
    UNAUTHORIZED("Unauthorized"),
    INVALID_INPUT("invalid input: '%s'"),
    METHOD_NOT_SUPPORTED("method not supported");

    private final String description;

    ErrorEnum(String description) {
        this.description = description;
    }

}
