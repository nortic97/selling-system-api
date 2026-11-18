package com.nortic.pos.ms.auth.infrastructure.adapter.output.jwt.constants;

import java.util.List;

public class JwtConstants {
    public static final String STORE_ID = "storeId";
    public static final String BRANCH_ID = "branchId";
    public static final String PERMISSIONS = "permissions";
    public static final String TYPE = "type";
    public static final String INTERNAL_TYPE_TOKEN = "internal";
    public static final String ACCESS_TYPE_TOKEN = "access";
    public static final List<String> INTERNAL_PERMISSIONS = List.of("system.all");
    public static final long EXPIRATION_HOURS = 24;
}
