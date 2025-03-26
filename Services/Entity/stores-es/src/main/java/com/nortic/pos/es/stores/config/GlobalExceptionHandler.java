package com.nortic.pos.es.stores.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
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

@RestControllerAdvice
public class GlobalExceptionHandler {

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
    public ResponseEntity<Map<String, Object>> handleNotFound(Exception ex, HttpServletRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("code", 404);
        response.put("status", "Request Not Found");
        response.put("message", "The requested resource " + request.getRequestURI() + " was not found.");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("code", 500);
        response.put("status", "Internal Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
