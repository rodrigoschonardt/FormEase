package com.formease.api.domain.user;

import jakarta.validation.constraints.NotNull;

public record UpdateUserData
(
    @NotNull
    Long id,
    String name,
    String password,
    User.States state
) {}
