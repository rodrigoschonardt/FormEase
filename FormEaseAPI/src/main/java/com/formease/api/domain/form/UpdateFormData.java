package com.formease.api.domain.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateFormData
(
    @NotNull
    Long id,
    String name,
    String info,
    Form.States state
)
{
}
