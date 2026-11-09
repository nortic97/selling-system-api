package com.nortic.pos.es.employeeworkspace.controller;

import com.nortic.pos.es.employee_workspace.rest.gen.api.EmployeeWorkspaceApi;
import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelCreateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelEmployeeWorkspaceResponseDTO;
import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelPaginatedEmployeeWorkspaceResponseDTO;
import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelUpdateEmployeeWorkspaceRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public class EmployeeWorkspaceController implements EmployeeWorkspaceApi {
    @Override
    public ResponseEntity<RestModelEmployeeWorkspaceResponseDTO> createEmployeeWorkspace(UUID correlationId, String consumerId, RestModelCreateEmployeeWorkspaceRequestDTO restModelCreateEmployeeWorkspaceRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteEmployeeWorkspace(UUID correlationId, UUID id, String consumerId) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelPaginatedEmployeeWorkspaceResponseDTO> getAllEmployeeWorkspaces(UUID correlationId, String consumerId, UUID storeId, Integer page, Integer size, List<String> sort) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelEmployeeWorkspaceResponseDTO> getEmployeeWorkspaceById(UUID correlationId, UUID id, String consumerId) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelEmployeeWorkspaceResponseDTO> restoreEmployeeWorkspace(UUID correlationId, UUID id, String consumerId) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelEmployeeWorkspaceResponseDTO> updateEmployeeWorkspace(UUID correlationId, UUID id, String consumerId, RestModelUpdateEmployeeWorkspaceRequestDTO restModelUpdateEmployeeWorkspaceRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelEmployeeWorkspaceResponseDTO> updateLastAccess(UUID correlationId, UUID id, String consumerId) {
        return null;
    }
}
