package com.nortic.pos.ms.auth.infrastructure.adapter.input.rest.controller;

import com.nortic.pos.ms.auth.application.service.LoginService;
import com.nortic.pos.ms.auth.infrastructure.adapter.input.rest.mapper.AuthRestMapper;
import com.nortic.pos.ms.auth.rest.gen.api.AuthenticationApi;
import com.nortic.pos.ms.auth.rest.gen.model.RestModelLoginRequest;
import com.nortic.pos.ms.auth.rest.gen.model.RestModelLoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController implements AuthenticationApi {

    private LoginService loginService;
    private AuthRestMapper authRestMapper;

    @Override
    public ResponseEntity<RestModelLoginResponse> login(UUID correlationId, String consumerId, RestModelLoginRequest restModelLoginRequest) {
        log.info("Auth Controller Start...");
        var loginCredentials = authRestMapper.toLoginCredentials(restModelLoginRequest);
        var l = loginService.login(loginCredentials);
        log.info("Auth Controller Finalized...");
        return ResponseEntity.ok().body(authRestMapper.toLoginResponse(l));
    }
}
