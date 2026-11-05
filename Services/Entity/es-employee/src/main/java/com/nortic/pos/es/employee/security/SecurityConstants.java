package com.nortic.pos.es.employee.security;

import org.apache.tomcat.util.http.Method;

import java.util.List;

public final class SecurityConstants {

    private SecurityConstants() {
    }

    // API Routes
    public static final String API_BASE_PATH = "/api/v1/es/employee";

    public static final String EMPLOYEE_ID = API_BASE_PATH + "/{id}";
    public static final String EMPLOYEE_SEARCH = API_BASE_PATH + "/search";
    public static final String EMPLOYEE_QUERY = API_BASE_PATH + "/query";
    public static final String EMPLOYEE_AUTHENTICATE = API_BASE_PATH + "/authenticate";

    public static final String EMPLOYEE_RESTORE = EMPLOYEE_ID + "/restore";
    public static final String EMPLOYEE_CHANGE_PASSWORD = EMPLOYEE_ID + "/password";
    public static final String EMPLOYEE_CHANGE_USERNAME = EMPLOYEE_ID + "/username";
    public static final String EMPLOYEE_CHANGE_EMAIL = EMPLOYEE_ID + "/email";
    public static final String EMPLOYEE_CHANGE_PHONE = EMPLOYEE_ID + "/phone";
    public static final String EMPLOYEE_CHANGE_STATUS = EMPLOYEE_ID + "/status";

    // Permissions (matching Permission.java format)
    public static final String PERMISSION_EMPLOYEE_READ = "employee.read";
    public static final String PERMISSION_EMPLOYEE_CREATE = "employee.create";
    public static final String PERMISSION_EMPLOYEE_UPDATE = "employee.update";
    public static final String PERMISSION_EMPLOYEE_DELETE = "employee.delete";
    public static final String PERMISSION_EMPLOYEE_ALL = "employee.all";
    public static final String PERMISSION_SYSTEM_ALL = "system.all";

    // Allowed Methods
    public static final List<String> EMPLOYEE_ALLOWED_METHODS = List.of(
            Method.GET,
            Method.POST,
            Method.PUT,
            Method.DELETE,
            Method.PATCH,
            Method.OPTIONS
    );

    // Allowed Headers
    public static final List<String> EMPLOYEE_ALLOWED_HEADERS = List.of(
            "Authorization",
            "Content-Type",
            "Correlation-Id",
            "Consumer-Id"
    );

    // Allowed Origins
    public static final List<String> EMPLOYEE_ALLOWED_ORIGINS = List.of(
            "http://localhost:4200"
    );

    // Public endpoints (no authentication required)
    public static final String[] PUBLIC_ENDPOINTS = {
            "/actuator/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            EMPLOYEE_AUTHENTICATE
    };
}
