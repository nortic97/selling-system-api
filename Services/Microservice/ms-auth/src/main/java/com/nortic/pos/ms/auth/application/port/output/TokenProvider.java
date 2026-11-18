package com.nortic.pos.ms.auth.application.port.output;

import com.nortic.pos.ms.auth.domain.model.User;

public interface TokenProvider {
    String generateUserJwt(User user);
    String generateInternalJwt();
}
