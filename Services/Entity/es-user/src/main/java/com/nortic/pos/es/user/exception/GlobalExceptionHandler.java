package com.nortic.pos.es.user.exception;

import com.nortic.pos.es.user.model.dto.ErrorResponseDTO;
import com.nortic.pos.es.user.model.dto.FieldErrorDetailResponseDTO;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
        log.error(
                "Business exception occurred [code={}]: {}",
                exception.getUserExceptionCode().getCode(),
                exception.getMessage(),
                exception
        );

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
                .map(violation -> FieldErrorDetailResponseDTO
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
                FieldErrorDetailResponseDTO
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exception
    ) {
        var response = ErrorResponseDTO
                .builder()
                .code(UserExceptionCode.VALIDATION_FAILED.getCode())
                .message(UserExceptionCode.VALIDATION_FAILED.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(
            Exception exception
    ) {

        log.error("Unhandled exception occurred: {}", exception.getMessage(), exception);

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

    private FieldErrorDetailResponseDTO mapFieldError(
            FieldError fieldError
    ) {

        return FieldErrorDetailResponseDTO
                .builder()
                .field(fieldError.getField())
                .message(fieldError.getDefaultMessage())
                .build();
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ErrorResponseDTO> handleSecurityException(
            SecurityException exception
    ) {
        var response = ErrorResponseDTO
                .builder()
                .code(UserExceptionCode.MISSING_PERMISSION.getCode())
                .message(exception.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();

        return ResponseEntity
                .status(UserExceptionCode.MISSING_PERMISSION.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(
            DataIntegrityViolationException exception
    ) {

        log.error(
                "Data integrity violation: {}",
                exception.getMostSpecificCause().getMessage(),
                exception
        );

        var response = ErrorResponseDTO.builder()
                .code(UserExceptionCode.DATA_INTEGRITY_VIOLATION.getCode())
                .message(UserExceptionCode.DATA_INTEGRITY_VIOLATION.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();

        return ResponseEntity
                .status(UserExceptionCode.DATA_INTEGRITY_VIOLATION.getHttpStatus())
                .body(response);
    }
}