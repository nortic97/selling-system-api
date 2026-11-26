package com.nortic.pos.ms.auth.application.port.input;

import com.nortic.pos.ms.auth.domain.model.AuthResponse;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;
import com.nortic.pos.ms.auth.domain.model.TokenExchange;

public interface LoginUseCase {
    AuthResponse loginUser(LoginCredentials credentials);
    AuthResponse loginEmployee(LoginCredentials credentials);
    AuthResponse tokenExchange(TokenExchange tokenExchange);
}
