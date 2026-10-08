package com.nortic.pos.es.permission.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

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

    CODE_ALREADY_EXISTS(
            "CODE_ALREADY_EXISTS",
            "Permission code already exists.",
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

    PERMISSION_NOT_FOUND(
            "PERMISSION_NOT_FOUND",
            "Permission not found.",
            HttpStatus.NOT_FOUND
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
