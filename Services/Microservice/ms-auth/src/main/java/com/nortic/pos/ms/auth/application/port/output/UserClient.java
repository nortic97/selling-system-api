package com.nortic.pos.ms.auth.application.port.output;

import com.nortic.pos.ms.auth.domain.model.LoginCredentials;
import com.nortic.pos.ms.auth.domain.model.User;

public interface UserClient {
    User authenticate(LoginCredentials loginCredentials);
}
