package com.nortic.pos.es.role.security;

import org.apache.tomcat.util.http.Method;

import java.util.List;

public final class SecurityConstants {

    private SecurityConstants() {
    }

    // API Routes
    public static final String API_BASE_PATH = "/api/v1/es/role";

    public static final String ROLE_ID = API_BASE_PATH + "/{id}";

    public static final String ROLE_RESTORE = ROLE_ID + "/restore";
    public static final String ROLE_CHANGE_STATUS = ROLE_ID + "/status";

    public static final String ROLE_PERMISSION = ROLE_ID + "/permission";
    public static final String ROLE_PERMISSION_ID = ROLE_PERMISSION + "/{permissionId}";

    // Permissions (matching Permission.java format)
    public static final String ROLE_READ = "role.read";
    public static final String ROLE_CREATE = "role.create";
    public static final String ROLE_UPDATE = "role.update";
    public static final String ROLE_DELETE = "role.delete";
    public static final String PERMISSION_ROLE_ALL = "role.all";
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
