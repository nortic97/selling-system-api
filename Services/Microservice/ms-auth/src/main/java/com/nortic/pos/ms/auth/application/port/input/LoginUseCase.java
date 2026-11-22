package com.nortic.pos.ms.auth.application.port.input;

import com.nortic.pos.ms.auth.domain.model.AuthResponse;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;

public interface LoginUseCase {
    AuthResponse loginUser(LoginCredentials credentials);
    AuthResponse loginEmployee(LoginCredentials credentials);
}
