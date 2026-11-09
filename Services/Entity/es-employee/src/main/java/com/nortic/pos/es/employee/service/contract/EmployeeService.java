package com.nortic.pos.es.employee.service.contract;

import com.nortic.pos.es.employee.model.dto.*;

import java.util.UUID;

public interface EmployeeService {

    EmployeeDTO changeEmail(RequestContextDTO requestContextDTO, UUID id, ChangeEmailRequestDTO changeEmailRequestDTO);

    EmployeeDTO changePhone(RequestContextDTO requestContextDTO, UUID id, ChangePhoneRequestDTO changePhoneRequestDTO);

    EmployeeDTO changeEmployeePassword(RequestContextDTO requestContextDTO, UUID id, ChangePasswordRequestDTO changePasswordRequestDTO);

    EmployeeDTO changeEmployeeStatus(RequestContextDTO requestContextDTO, UUID id, ChangeEmployeeStatusRequestDTO changeStatusRequestDTO);

    EmployeeDTO changeUsername(RequestContextDTO requestContextDTO, UUID id, ChangeUsernameRequestDTO changeusernameRequestDTO);

    EmployeeDTO authenticate(RequestContextDTO requestContextDTO, AuthenticateRequestDTO authenticateRequestDTO);

    EmployeeDTO createEmployee(RequestContextDTO requestContextDTO, EmployeeDTO employeeDTO);

    void deleteEmployee(RequestContextDTO requestContextDTO, UUID id);

    PaginatedEmployeeResponseDTO getAllEmployees(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO);

    EmployeeDTO getEmployeeById(RequestContextDTO requestContextDTO, UUID id);

    PaginatedEmployeeResponseDTO queryEmployees(RequestContextDTO requestContextDTO, EmployeeQueryRequestDTO employeeQueryRequestDTO);

    EmployeeDTO restoreEmployee(RequestContextDTO requestContextDTO, UUID id);

    PaginatedEmployeeResponseDTO searchEmployees(RequestContextDTO requestContextDTO, EmployeeSearchRequestDTO employeeSearchRequestDTO);

    EmployeeDTO updateEmployee(RequestContextDTO requestContextDTO, UUID id, EmployeeDTO employeeDTO);
}
