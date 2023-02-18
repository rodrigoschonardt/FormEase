package com.formease.api.domain.field;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddFieldData
(
    @NotBlank
    String label,
    @NotNull
    Boolean required,
    @NotNull
    Long formId,
    @NotNull
    Field.Types type
) {}
