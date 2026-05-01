package com.canach.commonutils.response;


import com.canach.commonutils.entitites.KeyValueObject;
import com.canach.commonutils.exception.enums.ErrorCategoryEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public record ErrorResponse(
        @NotBlank @JsonProperty("errorCode") String errorCode,
        @NotBlank @JsonProperty("errorMessage") String errorMessage,
        @NotNull @JsonProperty("errorCategory") ErrorCategoryEnum errorCategory,
        @NotNull @JsonProperty("errorDatetime") OffsetDateTime errorDatetime,
        @JsonProperty("errorDetails") List<KeyValueObject> errorDetails
) {
    public static ErrorResponse of(String errorCode, String errorMessage,
                                   ErrorCategoryEnum category, List<KeyValueObject> details) {
        return new ErrorResponse(
                errorCode,
                errorMessage,
                category,
                OffsetDateTime.now(ZoneOffset.UTC),
                details
        );
    }

    public static ErrorResponse of(String errorCode, String errorMessage,
                                   ErrorCategoryEnum category) {
        return new ErrorResponse(
                errorCode,
                errorMessage,
                category,
                OffsetDateTime.now(ZoneOffset.UTC),
                List.of()
        );
    }
}