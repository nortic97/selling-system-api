package com.nortic.pos.es.user.config;

import com.nortic.pos.es.user.dto.ErrorResponseDTO;
import com.nortic.pos.es.user.dto.FieldErrorDetailDTO;
import com.nortic.pos.es.user.exception.UserBusinessException;
import com.nortic.pos.es.user.exception.UserExceptionCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(
            MethodArgumentNotValidException exception
    ) {

        log.error(
                "Validation error detected",
                exception
        );

        var details = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::buildErrorDetail)
                .toList();

        var response = ErrorResponseDTO
                .builder()
                .code(UserExceptionCode.VALIDATION_FAILED.getCode())
                .message(UserExceptionCode.VALIDATION_FAILED.getMessage())
                .timestamp(OffsetDateTime.now())
                .details(details)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(UserBusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(
            UserBusinessException exception
    ) {

        log.error(
                "Business exception detected",
                exception
        );

        var response = ErrorResponseDTO
                .builder()
                .code(exception.getUserExceptionCode().getCode())
                .message(exception.getUserExceptionCode().getMessage())
                .timestamp(OffsetDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingRequestHeaderException(
            MissingRequestHeaderException exception
    ) {

        log.error(
                "Missing request header detected",
                exception
        );

        var details = java.util.List.of(
                FieldErrorDetailDTO
                        .builder()
                        .field(exception.getHeaderName())
                        .rejectedValue(null)
                        .message(String.format(
                                "Required header '%s' is missing",
                                exception.getHeaderName()
                        ))
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
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    private FieldErrorDetailDTO buildErrorDetail(
            FieldError fieldError
    ) {

        return FieldErrorDetailDTO
                .builder()
                .field(fieldError.getField())
                .rejectedValue(
                        fieldError.getRejectedValue() == null
                                ? "null"
                                : fieldError.getRejectedValue().toString()
                )
                .message(fieldError.getDefaultMessage())
                .build();
    }
}
