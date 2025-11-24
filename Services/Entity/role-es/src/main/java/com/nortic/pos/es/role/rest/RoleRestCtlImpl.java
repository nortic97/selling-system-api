package com.nortic.pos.es.role.rest;


import com.nortic.pos.es.role.controller.RolesApi;
import com.nortic.pos.es.role.model.*;
import com.nortic.pos.es.role.service.RoleSvcImpl;
import com.nortic.pos.es.role.util.RestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RoleRestCtlImpl implements RolesApi {

    private final RoleSvcImpl roleSvc;
    private final RestMapper restMapper;


    @Override
    public ResponseEntity<Void> changeRoleStatus(
            UUID id,
            RestModelStatusRequest restModelStatusRequest
    ) {
        log.info("Change Role Status Rest Controller start...");
        roleSvc.changeRoleStatus(
                id,
                restModelStatusRequest.getIsActive()
        );
        log.info("Change Role Status Rest Controller end...");
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<RestModelRole> createRole(RestModelRoleRequest restModelRoleRequest) {
        log.info("Create Role Rest Controller start...");
        var role = roleSvc.createRole(restMapper.roleRequestToDTO(restModelRoleRequest));
        log.info("Create Role Rest Controller end...");
        return ResponseEntity.ok(restMapper.dtoToRes(role));
    }

    @Override
    public ResponseEntity<RestModelPaginatedResponse> getAllDeletedRoles(
            Integer page,
            Integer size
    ) {
        log.info("Get All Deleted Roles Rest Controller start...");
        var deletedRolePaginated = roleSvc.getAllDeletedRoles(
                page,
                size
        );
        log.info("Get All Deleted Roles Rest Controller end...");
        return ResponseEntity.ok(restMapper.paginationToRes(deletedRolePaginated));
    }

    @Override
    public ResponseEntity<RestModelPaginatedResponse> getAllRoles(
            Integer page,
            Integer size
    ) {
        log.info("Get All Roles Rest Controller start...");
        var rolePaginated = roleSvc.getAllRoles(
                page,
                size
        );
        log.info("Get All Roles Rest Controller end...");
        return ResponseEntity.ok(restMapper.paginationToRes(rolePaginated));
    }

    @Override
    public ResponseEntity<RestModelRole> getRoleById(UUID id) {
        log.info("Get Role By Id Rest Controller Start...");

        var role = restMapper.dtoToRes(roleSvc.getRoleById(id));

        log.info("Get Role By Id Rest Controller End...");

        return ResponseEntity.ok(role);
    }

    @Override
    public ResponseEntity<Void> permanentDeleteRole(UUID id) {
        log.info("Permanent Delete Role Rest Controller start...");
        roleSvc.deleteRole(id);
        log.info("Permanent Delete Role Rest Controller end...");
        return ResponseEntity.noContent()
                             .build();
    }

    @Override
    public ResponseEntity<RestModelPaginatedResponse> searchDeletedRoles(RestModelRoleSearchRequest restModelRoleSearchRequest) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelPaginatedResponse> searchRoles(RestModelRoleSearchRequest restModelRoleSearchRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> softDeleteRole(UUID id) {
        log.info("Soft Delete Role Rest Controller start...");
        roleSvc.softDeleteRole(id);
        log.info("Soft Delete Role Rest Controller end...");
        return ResponseEntity.noContent()
                             .build();
    }

    @Override
    public ResponseEntity<RestModelRole> updateRole(
            UUID id,
            RestModelRoleRequest restModelRoleRequest
    ) {
        log.info("Update Role Rest Controller start...");
        var roleDTO = restMapper.roleRequestToDTO(restModelRoleRequest);
        var updatedRole = roleSvc.updateRole(
                id,
                roleDTO
        );
        log.info("Update Role Rest Controller end...");
        return ResponseEntity.ok(restMapper.dtoToRes(updatedRole));
    }
}
