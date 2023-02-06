package com.formease.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationData
(
    @NotBlank
    String email,
    @NotBlank
    String password
) {}
