package com.nortic.pos.es.role.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    VALIDATION_FAILED(
            "VALIDATION_FAILED",
            "One or more fields in the request failed validation.",
            HttpStatus.BAD_REQUEST
    ),

    STORE_NOT_FOUND(
            "STORE_NOT_FOUND",
            "Store not found.",
            HttpStatus.NOT_FOUND
    ),

    ROLE_NOT_FOUND(
            "ROLE_NOT_FOUND",
            "Role not found.",
            HttpStatus.NOT_FOUND
    ),

    PERMISSION_NOT_FOUND(
            "PERMISSION_NOT_FOUND",
            "Permission not found.",
            HttpStatus.NOT_FOUND
    ),

    ROLE_NAME_ALREADY_EXISTS(
            "ROLE_NAME_ALREADY_EXISTS",
            "Role name already exists for this store.",
            HttpStatus.CONFLICT
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
    );

    private final String code;

    private final String message;

    private final HttpStatus httpStatus;
}
