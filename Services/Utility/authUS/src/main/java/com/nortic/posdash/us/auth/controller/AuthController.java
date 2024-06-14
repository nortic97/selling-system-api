package com.nortic.posdash.us.auth.controller;

import com.nortic.posdash.us.auth.service.AuthService;
import com.nortic.posdash.us.auth.utils.CustomException;
import com.nortic.posdash.us.auth.utils.ResponseBody;
import com.nortic.posdash.us.models.dto.AuthRequestDTO;
import com.nortic.posdash.us.models.enums.StatusEnum;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/us/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDTO authRequest) {
        log.info("Start auth Controller");
        try {
            log.info("End auth Controller");
            return ResponseEntity.ok(authService.generateToken(authRequest));
        } catch (CustomException ce) {
            log.error(ce.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseBody<>(ce.getMessage(), StatusEnum.ERROR).getBody());
        }
    }

    @Hidden
    @RequestMapping("/**")
    public ResponseEntity<?> handleInvalidRequests(HttpServletRequest request) {
        log.error("The url {} does not exist", request.getRequestURI());
        ResponseBody<Object> responseBody = new ResponseBody<>(new CustomException(request.getRequestURI()).getMessage(), StatusEnum.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody.getBody());
    }
}
