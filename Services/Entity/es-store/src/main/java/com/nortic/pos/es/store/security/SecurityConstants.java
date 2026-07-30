package com.nortic.pos.es.store.security;

import org.apache.tomcat.util.http.Method;

import java.util.List;

public final class SecurityConstants {

    private SecurityConstants() {
    }

    // API Routes
    public static final String API_BASE_PATH = "/api/v1/es/store";

    public static final String STORE_ID = API_BASE_PATH + "/{id}";

    public static final String STORE_RESTORE = STORE_ID + "/restore";
    public static final String STORE_CHANGE_EMAIL = STORE_ID + "/email";
    public static final String STORE_CHANGE_PHONE = STORE_ID + "/phone";
    public static final String STORE_CHANGE_STATUS = STORE_ID + "/status";

    // Permissions (matching Permission.java format)
    public static final String PERMISSION_READ = "store.read";
    public static final String PERMISSION_CREATE = "store.create";
    public static final String PERMISSION_UPDATE = "store.update";
    public static final String PERMISSION_DELETE = "store.delete";

    // Allowed Methods
    public static final List<String> ALLOWED_METHODS = List.of(
            Method.GET,
            Method.POST,
            Method.PUT,
            Method.DELETE,
            Method.PATCH,
            Method.OPTIONS
    );

    // Allowed Headers
    public static  final List<String> ALLOWED_HEADERS = List.of(
            "Authorization",
            "Content-Type",
            "Correlation-Id",
            "Consumer-Id"
    );

    // Allowed Origins
    public static final List<String> ALLOWED_ORIGINS = List.of(
            "http://localhost:4200"
    );

    // Public endpoints (no authentication required)
    public static final String[] PUBLIC_ENDPOINTS = {
            "/actuator/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };
}
