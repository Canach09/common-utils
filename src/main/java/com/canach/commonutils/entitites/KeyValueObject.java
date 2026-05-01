package com.canach.commonutils.entitites;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record KeyValueObject(
        @NotBlank @JsonProperty("key") String key,
        @NotBlank @JsonProperty("value") String value
        ) {
}