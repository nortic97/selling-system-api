package com.nortic.pos.ms.auth.application.service;

import com.nortic.pos.ms.auth.application.port.input.LoginUseCase;
import com.nortic.pos.ms.auth.application.port.output.EmployeeClient;
import com.nortic.pos.ms.auth.application.port.output.RoleClient;
import com.nortic.pos.ms.auth.application.port.output.TokenProvider;
import com.nortic.pos.ms.auth.application.port.output.UserClient;
import com.nortic.pos.ms.auth.domain.model.AuthResponse;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;
import com.nortic.pos.ms.auth.domain.model.TokenExchange;
import com.nortic.pos.ms.auth.domain.model.enums.TokenTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final RoleClient roleClient;
    private final UserClient userClient;
    private final TokenProvider tokenProvider;
    private final EmployeeClient employeeClient;

    @Override
    public AuthResponse loginUser(LoginCredentials credentials) {
        log.info("Login Service Start...");
        var userAuthenticate = userClient.authenticate(credentials);
        var token = tokenProvider.generateUserJwt(userAuthenticate);
        var refreshToken = tokenProvider.generateRefreshToken(userAuthenticate.getId());
        log.info("Login Service Finalized...");
        return AuthResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .tokenTypeEnum(TokenTypeEnum.BEARER)
                .build();
    }

    @Override
    public AuthResponse loginEmployee(LoginCredentials credentials) {
        log.info("Login Employee Service Start...");
        var employeeAuthenticate = employeeClient.authenticate(credentials);
        var token = tokenProvider.generateEmployeeJwt(employeeAuthenticate);
        var refreshToken = tokenProvider.generateRefreshToken(employeeAuthenticate.getId());
        log.info("Login Employee Service Finalized...");
        return AuthResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .tokenTypeEnum(TokenTypeEnum.BEARER)
                .build();
    }

    @Override
    public AuthResponse tokenExchange(TokenExchange tokenExchange) {
        log.info("Token Exchange Service Start");
        var rolePermissions = roleClient.getRolePermission(tokenExchange.getRoleId());
        var enrichedToken = tokenProvider.tokenExchange(tokenExchange.getToken(), rolePermissions);
        var refreshToken = tokenProvider.generateRefreshToken(
                UUID.fromString(tokenProvider.getSubject(tokenExchange.getToken()))
        );
        log.info("Token Exchange Service Finalized");
        return AuthResponse.builder()
                .token(enrichedToken)
                .refreshToken(refreshToken)
                .tokenTypeEnum(TokenTypeEnum.BEARER)
                .build();
    }
}
