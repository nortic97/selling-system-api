package com.nortic.pos.es.plan.security;

import org.apache.tomcat.util.http.Method;

import java.util.List;

public final class SecurityConstants {

    private SecurityConstants() {
    }

    // API Routes
    public static final String API_BASE_PATH = "/api/v1/es/plan";

    public static final String PLAN_ID = API_BASE_PATH + "/{id}";

    public static final String PLAN_RESTORE = PLAN_ID + "/restore";

    // Permissions (matching Permission.java format)
    public static final String PERMISSION_READ = "plan.read";
    public static final String PERMISSION_CREATE = "plan.create";
    public static final String PERMISSION_UPDATE = "plan.update";
    public static final String PERMISSION_DELETE = "plan.delete";
    public static final String PERMISSION_PLAN_ALL = "plan.all";
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
