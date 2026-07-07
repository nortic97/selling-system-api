package com.nortic.pos.es.user.security.jwt.model;

import lombok.Getter;

@Getter
public final class Permission {

    public static final String USER_READ = "user.read";
    public static final String USER_CREATE = "user.create";
    public static final String USER_UPDATE = "user.update";
    public static final String USER_DELETE = "user.delete";
    public static final String USER_RESTORE = "user.restore";
    public static final String USER_CHANGE_PASSWORD = "user.change_password";
    public static final String USER_CHANGE_USERNAME = "user.change_username";
    public static final String USER_CHANGE_EMAIL = "user.change_email";
    public static final String USER_CHANGE_PHONE = "user.change_phone";
    public static final String USER_CHANGE_STATUS = "user.change_status";
}