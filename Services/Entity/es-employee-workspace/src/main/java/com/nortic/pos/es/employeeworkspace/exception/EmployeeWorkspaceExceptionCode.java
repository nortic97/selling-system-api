package com.nortic.pos.es.employeeworkspace.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EmployeeWorkspaceExceptionCode {

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

    EMPLOYEE_WORKSPACE_NOT_FOUND(
            "EMPLOYEE_WORKSPACE_NOT_FOUND",
            "Employee workspace not found.",
            HttpStatus.NOT_FOUND
    ),

    EMPLOYEE_WORKSPACE_ALREADY_EXISTS(
            "EMPLOYEE_WORKSPACE_ALREADY_EXISTS",
            "Employee workspace with the same employee, store, branch and role already exists.",
            HttpStatus.CONFLICT
    ),

    DEFAULT_WORKSPACE_ALREADY_EXISTS(
            "DEFAULT_WORKSPACE_ALREADY_EXISTS",
            "Employee already has a default workspace for this store.",
            HttpStatus.CONFLICT
    ),

    INTERNAL_SERVER_ERROR(
            "INTERNAL_SERVER_ERROR",
            "An unexpected error occurred.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),

    MISSING_HEADER(
            "MISSING_HEADER",
            "A required header is missing.",
            HttpStatus.BAD_REQUEST
    ),

    UNAUTHORIZED(
            "UNAUTHORIZED",
            "Authentication required.",
            HttpStatus.UNAUTHORIZED
    ),

    MISSING_PERMISSION(
            "MISSING_PERMISSION",
            "Permission required.",
            HttpStatus.FORBIDDEN
    );

    private final String code;

    private final String message;

    private final HttpStatus httpStatus;
}
