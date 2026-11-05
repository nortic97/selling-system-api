package com.nortic.pos.es.employee.security.jwt.model;

import lombok.Getter;

@Getter
public final class Permission {

    public static final String EMPLOYEE_READ = "employee.read";
    public static final String EMPLOYEE_CREATE = "employee.create";
    public static final String EMPLOYEE_UPDATE = "employee.update";
    public static final String EMPLOYEE_DELETE = "employee.delete";
    public static final String EMPLOYEE_RESTORE = "employee.restore";
    public static final String EMPLOYEE_CHANGE_PASSWORD = "employee.change_password";
    public static final String EMPLOYEE_CHANGE_USERNAME = "employee.change_username";
    public static final String EMPLOYEE_CHANGE_EMAIL = "employee.change_email";
    public static final String EMPLOYEE_CHANGE_PHONE = "employee.change_phone";
    public static final String EMPLOYEE_CHANGE_STATUS = "employee.change_status";
}