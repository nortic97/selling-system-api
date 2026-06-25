package com.nortic.pos.es.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserExceptionCode {

    VALIDATION_FAILED(
            "VALIDATION_FAILED",
                    "Uno o más campos enviados en la solicitud no pasaron las validaciones."
    ),

    USERNAME_ALREADY_EXISTS(
            "USERNAME_ALREADY_EXISTS",
                    "Username already exists."
    ),

    EMAIL_ALREADY_EXISTS(
            "EMAIL_ALREADY_EXISTS",
                    "Email already exists."
    ),

    PHONE_ALREADY_EXISTS(
            "PHONE_ALREADY_EXISTS",
                    "Phone already exists."
    ),

    DOCUMENT_ALREADY_EXISTS(
            "DOCUMENT_ALREADY_EXISTS",
                    "Document already exists."
    ),

    USER_NOT_FOUND(
            "USER_NOT_FOUND",
                    "User not found."
    ),

    INTERNAL_SERVER_ERROR(
            "INTERNAL_SERVER_ERROR",
                    "An unexpected error occurred."
    ),

    MISSING_HEADER(
            "MISSING_HEADER",
                    "A required header is missing."
    );

    private final String code;

    private final String message;
}
