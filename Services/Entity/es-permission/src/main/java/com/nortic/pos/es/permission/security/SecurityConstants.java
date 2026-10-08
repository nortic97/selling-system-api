package com.nortic.pos.es.permission.security;

import org.apache.tomcat.util.http.Method;

import java.util.List;

public final class SecurityConstants {

    private SecurityConstants() {
    }

    // API Routes
    public static final String API_BASE_PATH = "/api/v1/es/permission";

    public static final String PERMISSION_ID = API_BASE_PATH + "/{id}";

    public static final String PERMISSION_STATUS = PERMISSION_ID + "/status";

    public static final String PERMISSION_RESTORE = PERMISSION_ID + "/restore";

    // Permissions
    public static final String PERMISSION_READ = "permission.read";
    public static final String PERMISSION_CREATE = "permission.create";
    public static final String PERMISSION_UPDATE = "permission.update";
    public static final String PERMISSION_DELETE = "permission.delete";
    public static final String PERMISSION_PERMISSION_ALL = "permission.all";
    public static final String PERMISSION_SYSTEM_ALL = "system.all";

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
    public static final List<String> ALLOWED_HEADERS = List.of(
            "Authorization",
            "Content-Type",
            "Correlation-Id",
            "Consumer-Id"
    );

    // Allowed Origins
    public static final List<String> ALLOWED_ORIGINS = List.of(
            "http://localhost:4200",
            "http://localhost:8080",
            "http://localhost:8081"
    );

    // Public endpoints (no authentication required)
    public static final String[] PUBLIC_ENDPOINTS = {
            "/actuator/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };
}
