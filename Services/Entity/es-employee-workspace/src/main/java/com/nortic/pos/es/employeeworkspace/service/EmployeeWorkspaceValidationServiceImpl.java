package com.nortic.pos.es.employeeworkspace.service;

import com.nortic.pos.es.employeeworkspace.exception.EmployeeWorkspaceBusinessException;
import com.nortic.pos.es.employeeworkspace.exception.EmployeeWorkspaceExceptionCode;
import com.nortic.pos.es.employeeworkspace.model.dto.CreateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employeeworkspace.model.dto.UpdateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employeeworkspace.model.entity.EmployeeWorkspaceEntity;
import com.nortic.pos.es.employeeworkspace.repository.*;
import com.nortic.pos.es.employeeworkspace.service.contract.EmployeeWorkspaceValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeWorkspaceValidationServiceImpl implements EmployeeWorkspaceValidationService {

    private final EmployeeWorkspaceRepository employeeWorkspaceRepository;

    private final StoreRepository storeRepository;

    private final EmployeeRepository employeeRepository;

    private final BranchRepository branchRepository;

    private final RoleRepository roleRepository;

    @Override
    public EmployeeWorkspaceEntity validateAndGetEmployeeWorkspace(UUID id) {
        return employeeWorkspaceRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new EmployeeWorkspaceBusinessException(
                                EmployeeWorkspaceExceptionCode.EMPLOYEE_WORKSPACE_NOT_FOUND
                        )
                );
    }

    @Override
    public void validateNewEmployeeWorkspace(CreateEmployeeWorkspaceRequestDTO requestDTO) {
        validateStoreId(requestDTO.getStoreId());
        validateEmployeeId(requestDTO.getEmployeeId());
        validateBranchId(requestDTO.getBranchId());
        validateRoleId(requestDTO.getRoleId());

        if (employeeWorkspaceRepository.existsByEmployeeIdIdAndStoreIdIdAndBranchIdIdAndRoleIdIdAndIsActiveTrue(
                requestDTO.getEmployeeId(),
                requestDTO.getStoreId(),
                requestDTO.getBranchId(),
                requestDTO.getRoleId()
        )) {
            throw new EmployeeWorkspaceBusinessException(
                    EmployeeWorkspaceExceptionCode.EMPLOYEE_WORKSPACE_ALREADY_EXISTS
            );
        }

        if (requestDTO.getIsDefault()) {
            validateDefaultWorkspace(requestDTO.getEmployeeId(), requestDTO.getStoreId(), null);
        }
    }

    @Override
    public void validateEmployeeWorkspaceUpdate(UUID id, UpdateEmployeeWorkspaceRequestDTO requestDTO) {
        var existingWorkspace = validateAndGetEmployeeWorkspace(id);

        validateBranchId(requestDTO.getBranchId());
        validateRoleId(requestDTO.getRoleId());

        if (requestDTO.getIsDefault()) {
            validateDefaultWorkspace(
                    existingWorkspace.getEmployeeId().getId(),
                    existingWorkspace.getStoreId().getId(),
                    id
            );
        }
    }

    @Override
    public void validateDefaultWorkspace(UUID employeeId, UUID storeId, UUID excludeId) {
        if (employeeWorkspaceRepository.existsByEmployeeIdIdAndStoreIdIdAndIsDefaultTrueAndIsActiveTrueAndIdNot(
                employeeId,
                storeId,
                excludeId
        )) {
            throw new EmployeeWorkspaceBusinessException(
                    EmployeeWorkspaceExceptionCode.DEFAULT_WORKSPACE_ALREADY_EXISTS
            );
        }
    }

    @Override
    public void validateStoreId(UUID storeId) {
        if (storeId == null) {
            throw new EmployeeWorkspaceBusinessException(
                    EmployeeWorkspaceExceptionCode.VALIDATION_FAILED
            );
        }
        if (!storeRepository.existsById(storeId)) {
            throw new EmployeeWorkspaceBusinessException(
                    EmployeeWorkspaceExceptionCode.DATA_INTEGRITY_VIOLATION
            );
        }
    }

    @Override
    public void validateEmployeeId(UUID employeeId) {
        if (employeeId == null) {
            throw new EmployeeWorkspaceBusinessException(
                    EmployeeWorkspaceExceptionCode.VALIDATION_FAILED
            );
        }
        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeWorkspaceBusinessException(
                    EmployeeWorkspaceExceptionCode.DATA_INTEGRITY_VIOLATION
            );
        }
    }

    @Override
    public void validateBranchId(UUID branchId) {
        if (branchId == null) {
            throw new EmployeeWorkspaceBusinessException(
                    EmployeeWorkspaceExceptionCode.VALIDATION_FAILED
            );
        }
        if (!branchRepository.existsById(branchId)) {
            throw new EmployeeWorkspaceBusinessException(
                    EmployeeWorkspaceExceptionCode.DATA_INTEGRITY_VIOLATION
            );
        }
    }

    @Override
    public void validateRoleId(UUID roleId) {
        if (roleId == null) {
            throw new EmployeeWorkspaceBusinessException(
                    EmployeeWorkspaceExceptionCode.VALIDATION_FAILED
            );
        }
        if (!roleRepository.existsById(roleId)) {
            throw new EmployeeWorkspaceBusinessException(
                    EmployeeWorkspaceExceptionCode.DATA_INTEGRITY_VIOLATION
            );
        }
    }
}
