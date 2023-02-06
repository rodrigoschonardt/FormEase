package com.formease.api.infra.security;

import java.time.Instant;

public record JwtTokenData(
        String token,
        Instant expiration
) {}
