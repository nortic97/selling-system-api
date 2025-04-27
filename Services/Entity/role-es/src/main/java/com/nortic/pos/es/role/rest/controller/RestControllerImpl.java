package com.nortic.pos.es.role.rest.controller;


import com.nortic.pos.es.role.rest.gen.api.RoleApi;
import com.nortic.pos.es.role.rest.gen.model.RestModelChangeRoleStatusRequest;
import com.nortic.pos.es.role.rest.gen.model.RestModelRole;
import com.nortic.pos.es.role.rest.gen.model.RestModelRolePagination;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RestControllerImpl implements RoleApi {
    @Override
    public ResponseEntity<Void> changeRoleStatus(UUID id, RestModelChangeRoleStatusRequest restModelChangeRoleStatusRequest) {
        return RoleApi.super.changeRoleStatus(id, restModelChangeRoleStatusRequest);
    }

    @Override
    public ResponseEntity<RestModelRole> createRole(RestModelRole restModelRole) {
        return RoleApi.super.createRole(restModelRole);
    }

    @Override
    public ResponseEntity<Void> deleteRole(UUID id) {
        return RoleApi.super.deleteRole(id);
    }

    @Override
    public ResponseEntity<RestModelRole> getRoleById(UUID id) {
        return RoleApi.super.getRoleById(id);
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
