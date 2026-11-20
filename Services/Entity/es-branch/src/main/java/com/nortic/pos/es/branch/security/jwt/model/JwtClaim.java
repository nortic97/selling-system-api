package com.nortic.pos.es.branch.security.jwt.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtClaim {
    PERMISSIONS("permissions");

    private final String value;
}
