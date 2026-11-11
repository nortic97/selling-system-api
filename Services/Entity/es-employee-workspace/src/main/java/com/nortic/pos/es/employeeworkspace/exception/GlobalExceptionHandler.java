package com.nortic.pos.es.employeeworkspace.exception;

import com.nortic.pos.es.employeeworkspace.model.dto.ErrorResponseDTO;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeWorkspaceBusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmployeeWorkspaceBusinessException(
            EmployeeWorkspaceBusinessException exception
    ) {
        log.error(
                "Business exception occurred [code={}]: {}",
                exception.getEmployeeWorkspaceExceptionCode().getCode(),
                exception.getMessage(),
                exception
        );

        var response = ErrorResponseDTO
                .builder()
                .code(exception.getEmployeeWorkspaceExceptionCode().getCode())
                .message(exception.getEmployeeWorkspaceExceptionCode().getMessage())
                .timestamp(OffsetDateTime.now())
                .build();

        return ResponseEntity
                .status(exception.getEmployeeWorkspaceExceptionCode().getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        var response = ErrorResponseDTO
                .builder()
                .code(EmployeeWorkspaceExceptionCode.VALIDATION_FAILED.getCode())
                .message(EmployeeWorkspaceExceptionCode.VALIDATION_FAILED.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();

        return ResponseEntity
                .status(EmployeeWorkspaceExceptionCode.VALIDATION_FAILED.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(
            ConstraintViolationException exception
    ) {
        var response = ErrorResponseDTO
                .builder()
                .code(EmployeeWorkspaceExceptionCode.VALIDATION_FAILED.getCode())
                .message(EmployeeWorkspaceExceptionCode.VALIDATION_FAILED.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingRequestHeaderException(
            MissingRequestHeaderException exception
    ) {
        var response = ErrorResponseDTO
                .builder()
                .code(EmployeeWorkspaceExceptionCode.MISSING_HEADER.getCode())
                .message(exception.getMessage())
                .timestamp(OffsetDateTime.now())
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
                .code(EmployeeWorkspaceExceptionCode.VALIDATION_FAILED.getCode())
                .message(EmployeeWorkspaceExceptionCode.VALIDATION_FAILED.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ErrorResponseDTO> handleSecurityException(
            SecurityException exception
    ) {
        var response = ErrorResponseDTO
                .builder()
                .code(EmployeeWorkspaceExceptionCode.MISSING_PERMISSION.getCode())
                .message(exception.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();

        return ResponseEntity
                .status(EmployeeWorkspaceExceptionCode.MISSING_PERMISSION.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(
            DataIntegrityViolationException exception
    ) {
        log.error("Data integrity violation: {}", exception.getMessage(), exception);

        var response = ErrorResponseDTO.builder()
                .code(EmployeeWorkspaceExceptionCode.DATA_INTEGRITY_VIOLATION.getCode())
                .message(EmployeeWorkspaceExceptionCode.DATA_INTEGRITY_VIOLATION.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();

        return ResponseEntity
                .status(EmployeeWorkspaceExceptionCode.DATA_INTEGRITY_VIOLATION.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(
            Exception exception
    ) {
        log.error("Unhandled exception occurred: {}", exception.getMessage(), exception);

        var response = ErrorResponseDTO
                .builder()
                .code(EmployeeWorkspaceExceptionCode.INTERNAL_SERVER_ERROR.getCode())
                .message(EmployeeWorkspaceExceptionCode.INTERNAL_SERVER_ERROR.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
