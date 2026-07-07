package com.nortic.pos.es.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserExceptionCode {

    VALIDATION_FAILED(
            "VALIDATION_FAILED",
            "Uno o más campos enviados en la solicitud no pasaron las validaciones.",
            HttpStatus.BAD_REQUEST
    ),

    UNAUTHORIZED(
            "UNAUTHORIZED",
            "Authentication required.",
            HttpStatus.UNAUTHORIZED
    ),

    USERNAME_ALREADY_EXISTS(
            "USERNAME_ALREADY_EXISTS",
            "Username already exists.",
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

    USER_NOT_FOUND(
            "USER_NOT_FOUND",
            "User not found.",
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
    );

    private final String code;

    private final String message;

    private final HttpStatus httpStatus;
}