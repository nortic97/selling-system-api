package com.nortic.pos.es.employeeworkspace.service.contract;

import com.nortic.pos.es.employeeworkspace.model.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EmployeeWorkspaceService {

    PaginatedEmployeeWorkspaceResponseDTO getAllEmployeeWorkspaces(
            RequestContextDTO requestContextDTO,
            Pageable pageable
    );

    EmployeeWorkspaceDTO createEmployeeWorkspace(
            RequestContextDTO requestContextDTO,
            CreateEmployeeWorkspaceRequestDTO requestDTO
    );

    EmployeeWorkspaceDTO getEmployeeWorkspaceById(
            RequestContextDTO requestContextDTO,
            UUID id
    );

    EmployeeWorkspaceDTO updateEmployeeWorkspace(
            RequestContextDTO requestContextDTO,
            UUID id,
            UpdateEmployeeWorkspaceRequestDTO requestDTO
    );

    void deleteEmployeeWorkspace(
            RequestContextDTO requestContextDTO,
            UUID id
    );

    EmployeeWorkspaceDTO restoreEmployeeWorkspace(
            RequestContextDTO requestContextDTO,
            UUID id
    );

    EmployeeWorkspaceDTO updateLastAccess(
            RequestContextDTO requestContextDTO,
            UUID id
    );
}
