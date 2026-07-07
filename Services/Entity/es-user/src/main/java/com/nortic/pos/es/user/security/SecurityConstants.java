package com.nortic.pos.es.user.security;

import org.apache.tomcat.util.http.Method;

import java.util.List;

public final class SecurityConstants {

    private SecurityConstants() {
    }

    // API Routes
    public static final String API_BASE_PATH = "/api/v1/es/user";

    public static final String USER_ID = API_BASE_PATH + "/{id}";
    public static final String USER_SEARCH = API_BASE_PATH + "/search";
    public static final String USER_QUERY = API_BASE_PATH + "/query";

    public static final String USER_RESTORE = USER_ID + "/restore";
    public static final String USER_CHANGE_PASSWORD = USER_ID + "/password";
    public static final String USER_CHANGE_USERNAME = USER_ID + "/username";
    public static final String USER_CHANGE_EMAIL = USER_ID + "/email";
    public static final String USER_CHANGE_PHONE = USER_ID + "/phone";
    public static final String USER_CHANGE_STATUS = USER_ID + "/status";

    // Permissions (matching Permission.java format)
    public static final String PERMISSION_USER_READ = "user.read";
    public static final String PERMISSION_USER_CREATE = "user.create";
    public static final String PERMISSION_USER_UPDATE = "user.update";
    public static final String PERMISSION_USER_DELETE = "user.delete";

    // Allowed Methods
    public static final List<String> USER_ALLOWED_METHODS = List.of(
            Method.GET,
            Method.POST,
            Method.PUT,
            Method.DELETE,
            Method.PATCH,
            Method.OPTIONS
    );

    // Allowed Headers
    public static  final List<String> USER_ALLOWED_HEADERS = List.of(
            "Authorization",
            "Content-Type",
            "Correlation-Id",
            "Consumer-Id"
    );

    // Allowed Origins
    public static final List<String> USER_ALLOWED_ORIGINS = List.of(
            "http://localhost:4200"
    );

    // Public endpoints (no authentication required)
    public static final String[] PUBLIC_ENDPOINTS = {
            "/actuator/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };
}
