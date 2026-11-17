package com.nortic.pos.ms.auth.application.service;

import com.nortic.pos.ms.auth.application.port.input.LoginUseCase;
import com.nortic.pos.ms.auth.domain.model.AuthResponse;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    @Override
    public AuthResponse login(LoginCredentials credentials) {
        log.info("Login Service Start...");
        log.info("Login Service Finalized...");
        return AuthResponse.builder().build();
    }
}
