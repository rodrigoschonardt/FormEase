package com.formease.api.domain.form;

import com.formease.api.domain.user.UserDetailsData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddFormData
(
    @NotBlank
    String name,
    String info,
    @NotNull
    Long ownerId
) {}
