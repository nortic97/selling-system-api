package com.nortic.pos.ms.auth.infrastructure.adapter.input.rest.controller;

import com.nortic.pos.ms.auth.application.port.input.LoginUseCase;
import com.nortic.pos.ms.auth.infrastructure.adapter.input.rest.mapper.AuthRestMapper;
import com.nortic.pos.ms.auth.rest.gen.api.AuthenticationApi;
import com.nortic.pos.ms.auth.rest.gen.model.RestModelEmployeeLoginRequestDTO;
import com.nortic.pos.ms.auth.rest.gen.model.RestModelLoginRequestDTO;
import com.nortic.pos.ms.auth.rest.gen.model.RestModelLoginResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController implements AuthenticationApi {

    private final LoginUseCase loginUseCase;
    private final AuthRestMapper authRestMapper;

    @Override
    public ResponseEntity<RestModelLoginResponseDTO> employeeLogin(UUID correlationId, String consumerId, RestModelEmployeeLoginRequestDTO restModelEmployeeLoginRequestDTO) {
        log.info("Employee Auth Controller Start...");
        var loginCredentials = authRestMapper.toLoginCredentials(restModelEmployeeLoginRequestDTO);
        var authResponse = loginUseCase.loginEmployee(loginCredentials);
        log.info("Employee Auth Controller Finalized...");
        return ResponseEntity.ok().body(authRestMapper.toLoginResponse(authResponse));
    }

    @Override
    public ResponseEntity<RestModelLoginResponseDTO> login(UUID correlationId, String consumerId, RestModelLoginRequestDTO restModelLoginRequest) {
        log.info("Auth Controller Start...");
        var loginCredentials = authRestMapper.toLoginCredentials(restModelLoginRequest);
        var authResponse = loginUseCase.loginUser(loginCredentials);
        log.info("Auth Controller Finalized...");
        return ResponseEntity.ok().body(authRestMapper.toLoginResponse(authResponse));
    }


}