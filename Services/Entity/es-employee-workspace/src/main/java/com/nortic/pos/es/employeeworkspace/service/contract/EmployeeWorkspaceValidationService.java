package com.nortic.pos.es.employeeworkspace.service.contract;

import com.nortic.pos.es.employeeworkspace.model.dto.CreateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employeeworkspace.model.dto.UpdateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employeeworkspace.model.entity.EmployeeWorkspaceEntity;

import java.util.UUID;

public interface EmployeeWorkspaceValidationService {

    EmployeeWorkspaceEntity validateAndGetEmployeeWorkspace(UUID id);

    void validateNewEmployeeWorkspace(CreateEmployeeWorkspaceRequestDTO requestDTO);

    void validateEmployeeWorkspaceUpdate(UUID id, UpdateEmployeeWorkspaceRequestDTO requestDTO);

    void validateDefaultWorkspace(UUID employeeId, UUID storeId, UUID excludeId);

    void validateStoreId(UUID storeId);

    void validateEmployeeId(UUID employeeId);

    void validateBranchId(UUID branchId);

    void validateRoleId(UUID roleId);
}
