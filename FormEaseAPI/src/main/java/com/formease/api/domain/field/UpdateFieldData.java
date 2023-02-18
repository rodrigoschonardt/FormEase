package com.formease.api.domain.field;

import jakarta.validation.constraints.NotNull;

public record UpdateFieldData
(
    @NotNull
    Long id,
    String label,
    Boolean required,
    Field.Types type
)
{}