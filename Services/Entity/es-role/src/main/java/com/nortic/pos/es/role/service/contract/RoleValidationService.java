package com.nortic.pos.es.role.service.contract;

import com.nortic.pos.es.role.model.dto.RoleDTO;
import com.nortic.pos.es.role.model.entity.RoleEntity;

import java.util.UUID;

public interface RoleValidationService {

    RoleEntity validateAndGetRole(UUID id);

    void validateNewRole(RoleDTO roleDTO);

    void validateRoleUpdate(UUID id, RoleDTO roleDTO);
}
