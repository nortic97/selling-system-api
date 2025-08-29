package com.nortic.pos.es.role.rest.controller;


import com.nortic.pos.es.role.controller.RolesApi;
import com.nortic.pos.es.role.model.RestModelRole;
import com.nortic.pos.es.role.model.RestModelRolePage;
import com.nortic.pos.es.role.model.RestModelRoleRequest;
import com.nortic.pos.es.role.model.RestModelStatusRequest;
import com.nortic.pos.es.role.service.RoleSvcImpl;
import com.nortic.pos.es.role.util.RestMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
public class RestControllerImpl implements RolesApi {

    private final RoleSvcImpl roleSvc;
    private final RestMapper restMapper;


    @Override
    public ResponseEntity<Void> changeRoleStatus(UUID id, RestModelStatusRequest restModelStatusRequest) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelRole> createRole(RestModelRoleRequest restModelRoleRequest) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelRolePage> getDeletedRoles(UUID storeId, String name, Integer page, Integer size, List<String> sort) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelRole> getRoleById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelRolePage> getRoles(UUID storeId, String name, Boolean isActive, Integer page, Integer size, List<String> sort) {
        return null;
    }

    @Override
    public ResponseEntity<Void> permanentDeleteRole(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> softDeleteRole(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelRole> updateRole(UUID id, RestModelRoleRequest restModelRoleRequest) {
        return null;
    }
}
