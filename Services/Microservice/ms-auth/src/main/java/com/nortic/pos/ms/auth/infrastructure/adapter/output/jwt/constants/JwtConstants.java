package com.nortic.pos.ms.auth.infrastructure.adapter.output.jwt.constants;

import java.util.List;

public class JwtConstants {
    public static final String INTERNAL_TYPE_TOKEN = "internal";
    public static final String ACCESS_TYPE_TOKEN = "access";
    public static final String REFRESH_TYPE_TOKEN = "refresh";
    public static final List<String> INTERNAL_PERMISSIONS = List.of("system.all");
    public static final long ACCESS_EXPIRATION_MINUTES = 30;
    public static final long INTERNAL_EXPIRATION_MINUTES = 5;
    public static final long REFRESH_EXPIRATION_DAYS = 7;
}
