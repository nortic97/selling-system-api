package com.nortic.pos.es.role.rest.controller;


import com.nortic.pos.es.role.rest.gen.api.RoleApi;
import com.nortic.pos.es.role.rest.gen.model.RestModelChangeRoleStatusRequest;
import com.nortic.pos.es.role.rest.gen.model.RestModelRole;
import com.nortic.pos.es.role.rest.gen.model.RestModelRolePagination;
import com.nortic.pos.es.role.service.RoleSvcImpl;
import com.nortic.pos.es.role.util.RestMapper;
import com.nortic.pos.models.dto.RoleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class RestControllerImpl implements RoleApi {

    private final RoleSvcImpl roleSvc;
    private final RestMapper restMapper;

    @Autowired
    RestControllerImpl(RoleSvcImpl roleSvc, RestMapper restMapper) {
        this.roleSvc = roleSvc;
        this.restMapper = restMapper;
    }

    @Override
    public ResponseEntity<Void> changeRoleStatus(UUID id, RestModelChangeRoleStatusRequest restModelChangeRoleStatusRequest) {
        return RoleApi.super.changeRoleStatus(id, restModelChangeRoleStatusRequest);
    }

    @Override
    public ResponseEntity<RestModelRole> createRole(RestModelRole restModelRole) {
        log.info("Creating role controller");
        RoleDTO role = restMapper.restModelToDTO(restModelRole);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restMapper.dtoToRestModel(roleSvc.createRole(role)));
    }

    @Override
    public ResponseEntity<Void> deleteRole(UUID id) {
        return RoleApi.super.deleteRole(id);
    }

    @Override
    public ResponseEntity<RestModelRole> getRoleById(UUID id) {
        log.info("Retrieving role controller");
        return ResponseEntity.ok(restMapper.dtoToRestModel(roleSvc.getRoleById(id)));
    }

    @Override
    public ResponseEntity<RestModelRolePagination> listRolesOfStore(UUID storeId, Integer page, Integer limit, String name, Boolean isActive) {
        return RoleApi.super.listRolesOfStore(storeId, page, limit, name, isActive);
    }

    @Override
    public ResponseEntity<Void> softDeleteRole(UUID id) {
        return RoleApi.super.softDeleteRole(id);
    }

    @Override
    public ResponseEntity<RestModelRole> updateRole(UUID id, RestModelRole restModelRole) {
        return RoleApi.super.updateRole(id, restModelRole);
    }
}
