package com.nortic.pos.es.user.config;

import com.nortic.pos.es.user.dto.ErrorResponseDTO;
import com.nortic.pos.es.user.dto.FieldErrorDetailDTO;
import com.nortic.pos.es.user.exception.UserBusinessException;
import com.nortic.pos.es.user.exception.UserExceptionCode;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserBusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserBusinessException(
            UserBusinessException exception
    ) {

        var response = ErrorResponseDTO
                .builder()
                .code(exception.getUserExceptionCode().getCode())
                .message(exception.getUserExceptionCode().getMessage())
                .timestamp(OffsetDateTime.now())
                .details(exception.getDetails())
                .build();

        return ResponseEntity
                .status(exception.getUserExceptionCode().getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {

        var details = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::mapFieldError)
                .toList();

        var response = ErrorResponseDTO
                .builder()
                .code(UserExceptionCode.VALIDATION_FAILED.getCode())
                .message(UserExceptionCode.VALIDATION_FAILED.getMessage())
                .timestamp(OffsetDateTime.now())
                .details(details)
                .build();

        return ResponseEntity
                .status(UserExceptionCode.VALIDATION_FAILED.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(
            ConstraintViolationException exception
    ) {

        var details = exception
                .getConstraintViolations()
                .stream()
                .map(violation -> FieldErrorDetailDTO
                        .builder()
                        .field(
                                violation
                                        .getPropertyPath()
                                        .toString()
                        )
                        .message(violation.getMessage())
                        .build()
                )
                .toList();

        var response = ErrorResponseDTO
                .builder()
                .code(UserExceptionCode.VALIDATION_FAILED.getCode())
                .message(UserExceptionCode.VALIDATION_FAILED.getMessage())
                .timestamp(OffsetDateTime.now())
                .details(details)
                .build();

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingRequestHeaderException(
            MissingRequestHeaderException exception
    ) {

        var details = List.of(
                FieldErrorDetailDTO
                        .builder()
                        .field(exception.getHeaderName())
                        .message(exception.getMessage())
                        .build()
        );

        var response = ErrorResponseDTO
                .builder()
                .code(UserExceptionCode.MISSING_HEADER.getCode())
                .message(UserExceptionCode.MISSING_HEADER.getMessage())
                .timestamp(OffsetDateTime.now())
                .details(details)
                .build();

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(
            Exception exception
    ) {

        var response = ErrorResponseDTO
                .builder()
                .code(UserExceptionCode.INTERNAL_SERVER_ERROR.getCode())
                .message(UserExceptionCode.INTERNAL_SERVER_ERROR.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    private FieldErrorDetailDTO mapFieldError(
            FieldError fieldError
    ) {

        return FieldErrorDetailDTO
                .builder()
                .field(fieldError.getField())
                .message(fieldError.getDefaultMessage())
                .build();
    }
}