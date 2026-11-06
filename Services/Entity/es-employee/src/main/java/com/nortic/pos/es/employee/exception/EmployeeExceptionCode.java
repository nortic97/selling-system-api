package com.nortic.pos.es.employee.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum EmployeeExceptionCode {

    VALIDATION_FAILED(
            "VALIDATION_FAILED",
            "Uno o más campos enviados en la solicitud no pasaron las validaciones.",
            HttpStatus.BAD_REQUEST
    ),

    DATA_INTEGRITY_VIOLATION(
            "DATA_INTEGRITY_VIOLATION",
            "The operation violates a data integrity constraint.",
            HttpStatus.CONFLICT
    ),

    UNAUTHORIZED(
            "UNAUTHORIZED",
            "Authentication required.",
            HttpStatus.UNAUTHORIZED
    ),

    USERNAME_ALREADY_EXISTS(
            "USERNAME_ALREADY_EXISTS",
            "username already exists.",
            HttpStatus.CONFLICT
    ),

    EMAIL_ALREADY_EXISTS(
            "EMAIL_ALREADY_EXISTS",
            "Email already exists.",
            HttpStatus.CONFLICT
    ),

    PHONE_ALREADY_EXISTS(
            "PHONE_ALREADY_EXISTS",
            "Phone already exists.",
            HttpStatus.CONFLICT
    ),

    DOCUMENT_ALREADY_EXISTS(
            "DOCUMENT_ALREADY_EXISTS",
            "Document already exists.",
            HttpStatus.CONFLICT
    ),

    EMPLOYEE_NOT_FOUND(
            "EMPLOYEE_NOT_FOUND",
            "Employee not found.",
            HttpStatus.NOT_FOUND
    ),

    INVALID_PASSWORD(
            "INVALID_PASSWORD",
            "Invalid password.",
            HttpStatus.CONFLICT
    ),

    PASSWORD_ALREADY_IN_USE(
            "PASSWORD_ALREADY_IN_USE",
            "New password cannot be the same as the current password.",
            HttpStatus.CONFLICT
    ),

    INTERNAL_SERVER_ERROR(
            "INTERNAL_SERVER_ERROR",
            "An unexpected error occurred.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),

    MISSING_PERMISSION(
            "MISSING_PERMISSION",
            "Missing required permission.",
            HttpStatus.FORBIDDEN
    ),

    MISSING_HEADER(
            "MISSING_HEADER",
            "A required header is missing.",
            HttpStatus.BAD_REQUEST
    ),

    STORE_NOT_FOUND(
            "STORE_NOT_FOUND",
            "Store not found.",
            HttpStatus.NOT_FOUND
    );

    private final String code;

    private final String message;

    private final HttpStatus httpStatus;

    EmployeeExceptionCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}