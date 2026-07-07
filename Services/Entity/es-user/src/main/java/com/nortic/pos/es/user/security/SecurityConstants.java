package com.nortic.pos.es.user.security;

public final class SecurityConstants {

    private SecurityConstants() {
    }

    // API Routes
    public static final String API_BASE_PATH = "/api/v1/es/user";
    
    public static final String USER_GET_ALL = API_BASE_PATH;
    public static final String USER_CREATE = API_BASE_PATH;
    public static final String USER_SEARCH = API_BASE_PATH + "/search";
    public static final String USER_QUERY = API_BASE_PATH + "/query";
    public static final String USER_BY_ID = API_BASE_PATH + "/{id}";
    public static final String USER_UPDATE = API_BASE_PATH + "/{id}";
    public static final String USER_DELETE = API_BASE_PATH + "/{id}";
    public static final String USER_RESTORE = API_BASE_PATH + "/{id}/restore";
    public static final String USER_CHANGE_PASSWORD = API_BASE_PATH + "/{id}/password";
    public static final String USER_CHANGE_USERNAME = API_BASE_PATH + "/{id}/username";
    public static final String USER_CHANGE_EMAIL = API_BASE_PATH + "/{id}/email";
    public static final String USER_CHANGE_PHONE = API_BASE_PATH + "/{id}/phone";
    public static final String USER_CHANGE_STATUS = API_BASE_PATH + "/{id}/status";

    // Permissions (matching Permission.java format)
    public static final String PERMISSION_USER_READ = "user.read";
    public static final String PERMISSION_USER_CREATE = "user.create";
    public static final String PERMISSION_USER_UPDATE = "user.update";
    public static final String PERMISSION_USER_DELETE = "user.delete";
    public static final String PERMISSION_USER_RESTORE = "user.restore";
    public static final String PERMISSION_USER_CHANGE_PASSWORD = "user.change_password";
    public static final String PERMISSION_USER_CHANGE_USERNAME = "user.change_username";
    public static final String PERMISSION_USER_CHANGE_EMAIL = "user.change_email";
    public static final String PERMISSION_USER_CHANGE_PHONE = "user.change_phone";
    public static final String PERMISSION_USER_CHANGE_STATUS = "user.change_status";

    // Public endpoints (no authentication required)
    public static final String[] PUBLIC_ENDPOINTS = {
            "/actuator/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };
}
