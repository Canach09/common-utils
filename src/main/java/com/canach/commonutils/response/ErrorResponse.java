package com.canach.commonutils.response;


import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record ErrorResponse(
        @NotBlank @JsonProperty("errorCode") String errorCode,
        @NotBlank @JsonProperty("errorMessage") String errorMessage,
        @NotBlank @JsonProperty("errorCategory") ErrorCategoryEnum errorCategory
) {}