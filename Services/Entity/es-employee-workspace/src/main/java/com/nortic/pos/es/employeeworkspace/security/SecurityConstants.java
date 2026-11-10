package com.nortic.pos.es.employeeworkspace.security;

import org.apache.tomcat.util.http.Method;

import java.util.List;

public final class SecurityConstants {

    private SecurityConstants() {
    }

    // API Routes
    public static final String API_BASE_PATH = "/api/v1/es/employee-workspace";

    public static final String EMPLOYEE_WORKSPACE_ID = API_BASE_PATH + "/{id}";
    public static final String EMPLOYEE_WORKSPACE_LAST_ACCESS = EMPLOYEE_WORKSPACE_ID + "/last-access";
    public static final String EMPLOYEE_WORKSPACE_RESTORE = EMPLOYEE_WORKSPACE_ID + "/restore";

    // Permissions (matching Permission.java format)
    public static final String PERMISSION_EMPLOYEE_WORKSPACE_READ = "employee_workspace.read";
    public static final String PERMISSION_EMPLOYEE_WORKSPACE_CREATE = "employee_workspace.create";
    public static final String PERMISSION_EMPLOYEE_WORKSPACE_UPDATE = "employee_workspace.update";
    public static final String PERMISSION_EMPLOYEE_WORKSPACE_DELETE = "employee_workspace.delete";
    public static final String PERMISSION_EMPLOYEE_WORKSPACE_ALL = "employee_workspace.all";
    public static final String PERMISSION_SYSTEM_ALL = "system.all";

    // Allowed Methods
    public static final List<String> EMPLOYEE_WORKSPACE_ALLOWED_METHODS = List.of(
            Method.GET,
            Method.POST,
            Method.PUT,
            Method.DELETE,
            Method.PATCH,
            Method.OPTIONS
    );

    // Allowed Headers
    public static final List<String> EMPLOYEE_WORKSPACE_ALLOWED_HEADERS = List.of(
            "Authorization",
            "Content-Type",
            "Correlation-Id",
            "Consumer-Id"
    );

    // Allowed Origins
    public static final List<String> EMPLOYEE_WORKSPACE_ALLOWED_ORIGINS = List.of(
            "http://localhost:4200"
    );

    // Public endpoints (no authentication required)
    public static final String[] PUBLIC_ENDPOINTS = {
            "/actuator/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };
}
