package com.formease.api.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddUserData
(
    @NotBlank
    String email,
    @NotBlank
    String name,
    @NotBlank
    String password,
    @NotNull
    User.States state
) {}
