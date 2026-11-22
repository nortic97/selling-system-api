package com.nortic.pos.ms.auth.application.port.output;

import com.nortic.pos.ms.auth.domain.model.Employee;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;

public interface EmployeeClient {
    Employee authenticate(LoginCredentials loginCredentials);
}
