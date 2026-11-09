package com.nortic.pos.es.employee.service.contract;

import com.nortic.pos.es.employee.model.dto.EmployeeDTO;
import com.nortic.pos.es.employee.model.entity.EmployeeEntity;

import java.util.UUID;

public interface EmployeeValidationService {

    void validatePasswordChange(
            String currentPassword,
            String newPassword,
            String encodedPassword
    );

    EmployeeEntity validateAndGetEmployee(UUID id);

    void validateEmail(UUID id, String email);

    void validatePhone(UUID id, String phone);

    void validateNewEmployee(EmployeeDTO employeeDTO);

    void validateUsername(UUID id, String username);

    void validateStoreId(UUID storeId);

}
