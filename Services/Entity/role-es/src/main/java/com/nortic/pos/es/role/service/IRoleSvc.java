package com.nortic.pos.es.role.service;

import com.nortic.pos.models.dto.RoleDTO;

import java.util.UUID;

public interface IRoleSvc {
    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO getRoleById(UUID id);
}
