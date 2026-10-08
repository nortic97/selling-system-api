package com.nortic.pos.es.permission.service.contract;

import com.nortic.pos.es.permission.dto.response.PermissionDTO;
import com.nortic.pos.es.permission.model.entity.PermissionEntity;

import java.util.UUID;

public interface PermissionValidationService {

    PermissionEntity validateAndGetPermission(UUID id);

    void validateNewPermission(PermissionDTO permissionDTO);

    void validateCode(String code, UUID id);

}
