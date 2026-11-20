package com.nortic.pos.ms.auth.application.port.output;

import com.nortic.pos.ms.auth.domain.model.Employee;
import com.nortic.pos.ms.auth.domain.model.User;

import java.util.UUID;

public interface TokenProvider {
    String generateUserJwt(User user);
    String generateEmployeeJwt();
    String generateRefreshToken(UUID subjectId);
    String generateInternalJwt();
}
