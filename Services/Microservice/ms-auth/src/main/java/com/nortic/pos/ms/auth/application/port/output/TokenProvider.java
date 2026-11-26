package com.nortic.pos.ms.auth.application.port.output;

import com.nortic.pos.ms.auth.domain.model.Employee;
import com.nortic.pos.ms.auth.domain.model.Permission;
import com.nortic.pos.ms.auth.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface TokenProvider {
    String generateUserJwt(User user);
    String generateEmployeeJwt(Employee employee);
    String generateRefreshToken(UUID subjectId);
    String generateInternalJwt();
    String tokenExchange(String Token, List<Permission> permission);
    String getSubject(String token);
}
