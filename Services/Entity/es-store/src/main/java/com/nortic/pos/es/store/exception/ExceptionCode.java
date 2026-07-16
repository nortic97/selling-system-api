package com.nortic.pos.es.store.exception;

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

    OWNER_NOT_FOUND(
            "OWNER_NOT_FOUND",
            "Owner not found.",
            HttpStatus.NOT_FOUND
    ),

    TAX_ID_ALREADY_EXISTS(
            "TAX_ID_ALREADY_EXISTS",
            "Tax ID already exists.",
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

    STORE_NOT_FOUND(
            "STORE_NOT_FOUND",
            "Store not found.",
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