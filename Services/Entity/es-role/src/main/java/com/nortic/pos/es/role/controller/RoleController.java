package com.nortic.pos.es.role.controller;

import com.nortic.pos.es.role.mapper.RoleRestMapper;
import com.nortic.pos.es.role.model.dto.RequestContextDTO;
import com.nortic.pos.es.role.model.dto.SearchParamsDTO;
import com.nortic.pos.es.role.rest.gen.api.RoleApi;
import com.nortic.pos.es.role.rest.gen.model.*;
import com.nortic.pos.es.role.service.contract.RoleService;
import com.nortic.pos.es.role.util.PageableUtil;
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
public class RoleController implements RoleApi {

    private final RoleService roleService;
    private final RoleRestMapper roleRestMapper;

    @Override
    public ResponseEntity<RestModelRoleResponseDTO> changeStatus(UUID correlationId, String consumerId, UUID id, RestModelStatusRequest restModelStatusRequest) {
        log.info("Change status controller ... ");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var changeStatusRequestDTO = roleRestMapper.toChangeStatusRequestDTO(restModelStatusRequest);

        var roleDTO = roleService.changeStatus(requestContextDTO, id, changeStatusRequestDTO);

        log.info("Change status controller completed ... ");

        return ResponseEntity.ok(roleRestMapper.toRoleResponseDTO(roleDTO));
    }

    @Override
    public ResponseEntity<RestModelRoleResponseDTO> createRole(UUID correlationId, String consumerId, RestModelRoleRequestDTO restModelRoleRequestDTO) {

        log.info("Create role controller ...");

        var roleDTO = roleRestMapper.toRoleDTO(restModelRoleRequestDTO);

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var createdRoleDTO = roleService.createRole(requestContextDTO, roleDTO);

        log.info("Create role controller completed ...");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roleRestMapper.toRoleResponseDTO(createdRoleDTO));
    }

    @Override
    public ResponseEntity<Void> deleteRole(UUID correlationId, String consumerId, UUID id) {
        log.info("Delete role controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        roleService.deleteRole(requestContextDTO, id);

        log.info("Delete role controller completed ...");

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<RestModelPaginatedRoleResponseDTO> getAllRoles(UUID correlationId, String consumerId, Integer page, Integer size, @Nullable List<String> sort) {
        log.info("List roles controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var pagination = SearchParamsDTO.builder()
                .pageable(PageableUtil.buildPageable(page, size, sort))
                .build();

        var rolesDTO = roleService.listRoles(requestContextDTO, pagination);

        log.info("List roles controller completed ...");

        return ResponseEntity.ok(roleRestMapper.toListRolesResponseDTO(rolesDTO));
    }

    @Override
    public ResponseEntity<RestModelRoleResponseDTO> getRole(UUID correlationId, String consumerId, UUID id) {
        log.info("Get role controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var roleDTO = roleService.getRole(requestContextDTO, id);

        log.info("Get role controller completed ...");
        return ResponseEntity.ok(roleRestMapper.toRoleResponseDTO(roleDTO));
    }

    @Override
    public ResponseEntity<RestModelRoleResponseDTO> restoreRole(UUID correlationId, String consumerId, UUID id) {
        log.info("Restore role controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var roleDTO = roleService.restoreRole(requestContextDTO, id);

        log.info("Restore role controller completed ...");

        return ResponseEntity.ok(roleRestMapper.toRoleResponseDTO(roleDTO));
    }

    @Override
    public ResponseEntity<RestModelRoleResponseDTO> updateRole(UUID correlationId, String consumerId, UUID id, RestModelUpdateRoleRequestDTO restModelUpdateRoleRequestDTO) {
        log.info("Update role controller ... ");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var roleDTO = roleRestMapper.toUpdateRoleRequestDTO(restModelUpdateRoleRequestDTO);

        var updatedRoleDTO = roleService.updateRole(requestContextDTO, id, roleDTO);

        log.info("Update role controller completed ... ");

        return ResponseEntity.ok(roleRestMapper.toRoleResponseDTO(updatedRoleDTO));
    }
}
