package com.nortic.pos.es.permission.controller;

import com.nortic.pos.es.permission.dto.SearchParamsDTO;
import com.nortic.pos.es.permission.dto.request.RequestContextDTO;
import com.nortic.pos.es.permission.mapper.PermissionRestMapper;
import com.nortic.pos.es.permission.rest.gen.api.PermissionApi;
import com.nortic.pos.es.permission.rest.gen.model.*;
import com.nortic.pos.es.permission.service.contract.PermissionService;
import com.nortic.pos.es.permission.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PermissionController implements PermissionApi {

    private final PermissionService permissionService;
    private final PermissionRestMapper permissionRestMapper;

    @Override
    public ResponseEntity<RestModelPermissionResponseDTO> changeStatus(UUID correlationId, String consumerId, UUID id, RestModelStatusRequest restModelStatusRequest) {
        log.info("Change status controller ... ");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var changeStatusRequestDTO = permissionRestMapper.toChangeStatusRequestDTO(restModelStatusRequest);

        var permissionDTO = permissionService.changeStatus(requestContextDTO, id, changeStatusRequestDTO);

        log.info("Change status controller completed ... ");

        return ResponseEntity.ok(permissionRestMapper.toPermissionResponseDTO(permissionDTO));
    }

    @Override
    public ResponseEntity<RestModelPermissionResponseDTO> createPermission(UUID correlationId, String consumerId, RestModelPermissionRequestDTO restModelPermissionRequestDTO) {

        log.info("Create permission controller ...");

        var permissionDTO = permissionRestMapper.toPermissionDTO(restModelPermissionRequestDTO);

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var createdPermissionDTO = permissionService.createPermission(requestContextDTO, permissionDTO);

        log.info("Create permission controller completed ...");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(permissionRestMapper.toPermissionResponseDTO(createdPermissionDTO));
    }

    @Override
    public ResponseEntity<Void> deletePermission(UUID correlationId, String consumerId, UUID id) {
        log.info("Delete permission controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        permissionService.deletePermission(requestContextDTO, id);

        log.info("Delete permission controller completed ...");

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<RestModelPaginatedPermissionResponseDTO> getAllPermissions(UUID correlationId, String consumerId, Integer page, Integer size, @Nullable List<String> sort) {
        log.info("List permissions controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var pagination = SearchParamsDTO.builder()
                .pageable(PageableUtil.buildPageable(page, size, sort))
                .build();

        var permissionsDTO = permissionService.listPermissions(requestContextDTO, pagination);

        log.info("List permissions controller completed ...");

        return ResponseEntity.ok(permissionRestMapper.toListPermissionsResponseDTO(permissionsDTO));
    }

    @Override
    public ResponseEntity<RestModelPermissionResponseDTO> getPermission(UUID correlationId, String consumerId, UUID id) {
        log.info("Get permission controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var permissionDTO = permissionService.getPermission(requestContextDTO, id);

        log.info("Get permission controller completed ...");
        return ResponseEntity.ok(permissionRestMapper.toPermissionResponseDTO(permissionDTO));
    }

    @Override
    public ResponseEntity<RestModelPermissionResponseDTO> restorePermission(UUID correlationId, String consumerId, UUID id) {
        log.info("Restore permission controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var permissionDTO = permissionService.restorePermission(requestContextDTO, id);

        log.info("Restore permission controller completed ...");

        return ResponseEntity.ok(permissionRestMapper.toPermissionResponseDTO(permissionDTO));
    }

    @Override
    public ResponseEntity<RestModelPermissionResponseDTO> updatePermission(UUID correlationId, String consumerId, UUID id, RestModelUpdatePermissionRequestDTO restModelUpdatePermissionRequestDTO) {
        log.info("Update permission controller ... ");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var permissionDTO = permissionRestMapper.toPermissionDTO(restModelUpdatePermissionRequestDTO);

        var updatedPermissionDTO = permissionService.updatePermission(requestContextDTO, id, permissionDTO);

        log.info("Update permission controller completed ... ");

        return ResponseEntity.ok(permissionRestMapper.toPermissionResponseDTO(updatedPermissionDTO));
    }
}
