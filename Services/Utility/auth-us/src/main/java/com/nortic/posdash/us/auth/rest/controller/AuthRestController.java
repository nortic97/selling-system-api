package com.nortic.posdash.us.auth.rest.controller;

import com.nortic.posdash.us.auth.rest.gen.api.AuthApi;
import com.nortic.posdash.us.auth.rest.gen.model.AuthRequest;
import com.nortic.posdash.us.auth.rest.gen.model.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController implements AuthApi {
    @Override
    public ResponseEntity<AuthResponse> auth(AuthRequest authRequest) {
        return new ResponseEntity<>(new AuthResponse(), HttpStatus.OK);
    }
}
