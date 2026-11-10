package com.nortic.pos.es.employeeworkspace.service;

import com.nortic.pos.es.employeeworkspace.exception.EmployeeWorkspaceBusinessException;
import com.nortic.pos.es.employeeworkspace.exception.EmployeeWorkspaceExceptionCode;
import com.nortic.pos.es.employeeworkspace.mapper.EmployeeWorkspaceEntityMapper;
import com.nortic.pos.es.employeeworkspace.model.dto.*;
import com.nortic.pos.es.employeeworkspace.model.entity.EmployeeWorkspaceEntity;
import com.nortic.pos.es.employeeworkspace.repository.EmployeeWorkspaceRepository;
import com.nortic.pos.es.employeeworkspace.service.contract.EmployeeWorkspaceService;
import com.nortic.pos.es.employeeworkspace.service.contract.EmployeeWorkspaceValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeWorkspaceServiceImpl implements EmployeeWorkspaceService {

    private final EmployeeWorkspaceRepository employeeWorkspaceRepository;

    private final EmployeeWorkspaceEntityMapper employeeWorkspaceEntityMapper;

    private final EmployeeWorkspaceValidationService employeeWorkspaceValidationService;

    @Override
    public PaginatedEmployeeWorkspaceResponseDTO getAllEmployeeWorkspaces(
            RequestContextDTO requestContextDTO,
            Pageable pageable
    ) {
        log.info("Getting all employee workspaces service...");

        employeeWorkspaceValidationService.validateStoreId(requestContextDTO.getStoreId());

        Page<EmployeeWorkspaceEntity> employeeWorkspaces = employeeWorkspaceRepository
                .findByStoreIdIdAndIsActiveTrue(requestContextDTO.getStoreId(), pageable);

        log.info("Getting all employee workspaces service complete.");

        return employeeWorkspaceEntityMapper.toPaginatedEmployeeWorkspaceDTO(employeeWorkspaces);
    }

    @Override
    @Transactional
    public EmployeeWorkspaceDTO createEmployeeWorkspace(
            RequestContextDTO requestContextDTO,
            CreateEmployeeWorkspaceRequestDTO requestDTO
    ) {
        log.info("Creating employee workspace service...");

        employeeWorkspaceValidationService.validateNewEmployeeWorkspace(requestDTO);

        var employeeWorkspace = employeeWorkspaceEntityMapper.toEmployeeWorkspaceEntity(requestDTO);

        var savedEmployeeWorkspace = employeeWorkspaceRepository.save(employeeWorkspace);

        log.info("Employee workspace created with id {}", savedEmployeeWorkspace.getId());

        return employeeWorkspaceEntityMapper.toEmployeeWorkspaceDTO(savedEmployeeWorkspace);
    }

    @Override
    public EmployeeWorkspaceDTO getEmployeeWorkspaceById(
            RequestContextDTO requestContextDTO,
            UUID id
    ) {
        log.info("Getting employee workspace by id service...");

        var employeeWorkspace = employeeWorkspaceValidationService.validateAndGetEmployeeWorkspace(id);

        log.info("Getting employee workspace by id service complete.");

        return employeeWorkspaceEntityMapper.toEmployeeWorkspaceDTO(employeeWorkspace);
    }

    @Override
    @Transactional
    public EmployeeWorkspaceDTO updateEmployeeWorkspace(
            RequestContextDTO requestContextDTO,
            UUID id,
            UpdateEmployeeWorkspaceRequestDTO requestDTO
    ) {
        log.info("Updating employee workspace service...");

        var employeeWorkspace = employeeWorkspaceValidationService.validateAndGetEmployeeWorkspace(id);

        employeeWorkspaceValidationService.validateBranchId(requestDTO.getBranchId());
        employeeWorkspaceValidationService.validateRoleId(requestDTO.getRoleId());

        employeeWorkspaceValidationService.validateEmployeeWorkspaceUpdate(id, requestDTO);

        employeeWorkspaceEntityMapper.updateEmployeeWorkspaceFromDTO(requestDTO, employeeWorkspace);

        log.info("Updating employee workspace service complete.");

        return employeeWorkspaceEntityMapper.toEmployeeWorkspaceDTO(employeeWorkspace);
    }

    @Override
    @Transactional
    public void deleteEmployeeWorkspace(
            RequestContextDTO requestContextDTO,
            UUID id
    ) {
        log.info("Deleting employee workspace service...");

        var employeeWorkspace = employeeWorkspaceValidationService.validateAndGetEmployeeWorkspace(id);

        employeeWorkspaceRepository.delete(employeeWorkspace);

        log.info("Employee workspace deleted with id {}", id);
    }

    @Override
    @Transactional
    public EmployeeWorkspaceDTO restoreEmployeeWorkspace(
            RequestContextDTO requestContextDTO,
            UUID id
    ) {
        log.info("Restoring employee workspace service...");

        var employeeWorkspace = employeeWorkspaceRepository.findByIdIncludingDeleted(id)
                .orElseThrow(() -> new EmployeeWorkspaceBusinessException(
                        EmployeeWorkspaceExceptionCode.EMPLOYEE_WORKSPACE_NOT_FOUND
                ));

        employeeWorkspace.setIsActive(true);
        employeeWorkspace.setDeletedAt(null);

        var restoredEmployeeWorkspace = employeeWorkspaceRepository.save(employeeWorkspace);

        log.info("Restoring employee workspace service complete.");

        return employeeWorkspaceEntityMapper.toEmployeeWorkspaceDTO(restoredEmployeeWorkspace);
    }

    @Override
    @Transactional
    public EmployeeWorkspaceDTO updateLastAccess(
            RequestContextDTO requestContextDTO,
            UUID id
    ) {
        log.info("Updating last access service...");

        var employeeWorkspace = employeeWorkspaceValidationService.validateAndGetEmployeeWorkspace(id);

        employeeWorkspace.setLastAccessAt(OffsetDateTime.now());

        log.info("Updating last access service complete.");

        return employeeWorkspaceEntityMapper.toEmployeeWorkspaceDTO(employeeWorkspace);
    }
}
