package com.nortic.pos.es.feature.security.jwt.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtClaim {
    STORE_ID("storeId"),
    BRANCH_ID("branchId"),
    TOKEN_TYPE("type"),
    PERMISSIONS("permissions");

    private final String value;
}
