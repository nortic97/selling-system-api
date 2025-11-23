package com.nortic.pos.es.role.service;

import com.nortic.pos.models.dto.RoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IRoleSvc {

    /**
     * Create a new role
     */
    RoleDTO createRole(RoleDTO roleDTO);

    /**
     * Get role by ID (only active roles)
     */
    RoleDTO getRoleById(UUID id);

    /**
     * Update role by ID
     */
    RoleDTO updateRole(UUID id, RoleDTO roleDTO);

    /**
     * Permanently delete role by ID
     */
    void deleteRole(UUID id);

    /**
     * Soft delete role by ID
     */
    void softDeleteRole(UUID id);

    /**
     * Change role active status
     */
    void changeRoleStatus(UUID id, Boolean status);
}