package com.nortic.pos.es.employeeworkspace.controller;

import com.nortic.pos.es.employee_workspace.rest.gen.api.EmployeeWorkspaceApi;
import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelCreateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelEmployeeWorkspaceResponseDTO;
import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelPaginatedEmployeeWorkspaceResponseDTO;
import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelUpdateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employeeworkspace.mapper.EmployeeWorkspaceRestMapper;
import com.nortic.pos.es.employeeworkspace.model.dto.RequestContextDTO;
import com.nortic.pos.es.employeeworkspace.service.contract.EmployeeWorkspaceService;
import com.nortic.pos.es.employeeworkspace.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmployeeWorkspaceController implements EmployeeWorkspaceApi {

    private final EmployeeWorkspaceRestMapper employeeWorkspaceRestMapper;

    private final EmployeeWorkspaceService employeeWorkspaceService;

    @Override
    public ResponseEntity<RestModelPaginatedEmployeeWorkspaceResponseDTO> getAllEmployeeWorkspaces(UUID correlationId, String consumerId, UUID storeId, Integer page, Integer size, List<String> sort) {
        log.info("Getting all employee workspaces REST controller...");

        var requestContextDTO = RequestContextDTO.builder().correlationId(correlationId).consumerId(consumerId).storeId(storeId).build();

        var pageable = PageableUtil.buildPageable(page, size, sort);

        var paginatedEmployeeWorkspaces = employeeWorkspaceService.getAllEmployeeWorkspaces(requestContextDTO, pageable);

        log.info("Getting all employee workspaces REST controller finished...");

        return ResponseEntity.ok(employeeWorkspaceRestMapper.toRestModelPaginatedEmployeeWorkspaceResponseDTO(paginatedEmployeeWorkspaces));
    }

    @Override
    public ResponseEntity<RestModelEmployeeWorkspaceResponseDTO> createEmployeeWorkspace(UUID correlationId, String consumerId, RestModelCreateEmployeeWorkspaceRequestDTO restModelCreateEmployeeWorkspaceRequestDTO) {
        log.info("Creating employee workspace REST controller...");

        var requestContextDTO = RequestContextDTO.builder().correlationId(correlationId).consumerId(consumerId).build();

        var createEmployeeWorkspaceRequestDTO = employeeWorkspaceRestMapper.toCreateEmployeeWorkspaceRequestDTO(restModelCreateEmployeeWorkspaceRequestDTO);

        var createdEmployeeWorkspace = employeeWorkspaceService.createEmployeeWorkspace(requestContextDTO, createEmployeeWorkspaceRequestDTO);

        log.info("Creating employee workspace REST controller finished...");

        return ResponseEntity.status(HttpStatus.CREATED).body(employeeWorkspaceRestMapper.toRestModelEmployeeWorkspaceResponseDTO(createdEmployeeWorkspace));
    }

    @Override
    public ResponseEntity<RestModelEmployeeWorkspaceResponseDTO> getEmployeeWorkspaceById(UUID correlationId, UUID id, String consumerId) {
        log.info("Getting employee workspace by id REST controller...");

        var requestContextDTO = RequestContextDTO.builder().correlationId(correlationId).consumerId(consumerId).build();

        var employeeWorkspace = employeeWorkspaceService.getEmployeeWorkspaceById(requestContextDTO, id);

        log.info("Getting employee workspace by id REST controller finished...");

        return ResponseEntity.ok(employeeWorkspaceRestMapper.toRestModelEmployeeWorkspaceResponseDTO(employeeWorkspace));
    }

    @Override
    public ResponseEntity<RestModelEmployeeWorkspaceResponseDTO> updateEmployeeWorkspace(UUID correlationId, UUID id, String consumerId, RestModelUpdateEmployeeWorkspaceRequestDTO restModelUpdateEmployeeWorkspaceRequestDTO) {
        log.info("Updating employee workspace REST controller...");

        var requestContextDTO = RequestContextDTO.builder().correlationId(correlationId).consumerId(consumerId).build();

        var updateEmployeeWorkspaceRequestDTO = employeeWorkspaceRestMapper.toUpdateEmployeeWorkspaceRequestDTO(restModelUpdateEmployeeWorkspaceRequestDTO);

        var updatedEmployeeWorkspace = employeeWorkspaceService.updateEmployeeWorkspace(requestContextDTO, id, updateEmployeeWorkspaceRequestDTO);

        log.info("Updating employee workspace REST controller finished...");

        return ResponseEntity.ok(employeeWorkspaceRestMapper.toRestModelEmployeeWorkspaceResponseDTO(updatedEmployeeWorkspace));
    }

    @Override
    public ResponseEntity<Void> deleteEmployeeWorkspace(UUID correlationId, UUID id, String consumerId) {
        log.info("Deleting employee workspace REST controller...");

        var requestContextDTO = RequestContextDTO.builder().correlationId(correlationId).consumerId(consumerId).build();

        employeeWorkspaceService.deleteEmployeeWorkspace(requestContextDTO, id);

        log.info("Deleting employee workspace REST controller finished...");

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<RestModelEmployeeWorkspaceResponseDTO> restoreEmployeeWorkspace(UUID correlationId, UUID id, String consumerId) {
        log.info("Restoring employee workspace REST controller...");

        var requestContextDTO = RequestContextDTO.builder().correlationId(correlationId).consumerId(consumerId).build();

        var restoredEmployeeWorkspace = employeeWorkspaceService.restoreEmployeeWorkspace(requestContextDTO, id);

        log.info("Restoring employee workspace REST controller finished...");

        return ResponseEntity.ok(employeeWorkspaceRestMapper.toRestModelEmployeeWorkspaceResponseDTO(restoredEmployeeWorkspace));
    }

    @Override
    public ResponseEntity<RestModelEmployeeWorkspaceResponseDTO> updateLastAccess(UUID correlationId, UUID id, String consumerId) {
        log.info("Updating last access REST controller...");

        var requestContextDTO = RequestContextDTO.builder().correlationId(correlationId).consumerId(consumerId).build();

        var updatedEmployeeWorkspace = employeeWorkspaceService.updateLastAccess(requestContextDTO, id);

        log.info("Updating last access REST controller finished...");

        return ResponseEntity.ok(employeeWorkspaceRestMapper.toRestModelEmployeeWorkspaceResponseDTO(updatedEmployeeWorkspace));
    }
}
