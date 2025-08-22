package com.nortic.pos.es.users.config;

import com.nortic.pos.es.users.util.CustomException;
import com.nortic.pos.models.dto.HttpErrorDTO;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<HttpErrorDTO> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new HttpErrorDTO(HttpStatus.CONFLICT.value(), "Data integrity violation. Please check your input."));
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<HttpErrorDTO> handlePersistenceException(PersistenceException ex) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new HttpErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "A database error occurred. Please try again later."));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<HttpErrorDTO> handleDataAccessException(DataAccessException ex) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new HttpErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred while accessing the database."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("code", 400);
        errors.put("status", "Bad Request");

        List<Map<String, String>> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> Map.of(
                        "field", error.getField(),
                        "message", Objects.requireNonNull(error.getDefaultMessage())
                ))
                .collect(Collectors.toList());

        errors.put("errors", fieldErrors);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("code", 400);
        errors.put("status", "Bad Request");

        List<Map<String, String>> violations = ex.getConstraintViolations()
                .stream()
                .map(violation -> Map.of(
                        "field", violation.getPropertyPath().toString(),
                        "message", violation.getMessage()
                ))
                .collect(Collectors.toList());

        errors.put("errors", violations);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<HttpErrorDTO> handleNotFound(Exception ex, HttpServletRequest request) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new HttpErrorDTO(HttpStatus.NOT_FOUND.value(), "The requested resource " + request.getRequestURI() + " was not found."));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<HttpErrorDTO> handleCustomException(CustomException ex) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(ex.getErrorCode())
                .body(new HttpErrorDTO(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpErrorDTO> handleGeneralException(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new HttpErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"));
    }
}
