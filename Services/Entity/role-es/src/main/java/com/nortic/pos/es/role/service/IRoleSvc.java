package com.nortic.pos.es.role.service;

import com.nortic.pos.models.dto.RoleDTO;
import com.nortic.pos.models.dto.RolePaginationDTO;

import java.util.UUID;

public interface IRoleSvc {

    /**
     * Create a new role
     */
    RoleDTO createRole(RoleDTO roleDTO);

    /**
     * Retrieves a list of all roles in the system, paginated.
     *
     * @param page The page number to retrieve.
     * @param size The number of records to retrieve per page.
     * @return A list of roles.
     */
    RolePaginationDTO getAllRoles(
            Integer page,
            Integer size
    );

    /**
     * Retrieves a list of all deleted roles in the system, paginated.
     *
     * @param page The page number to retrieve.
     * @param size The number of records to retrieve per page.
     * @return A list of deleted roles.
     */
    RolePaginationDTO getAllDeletedRoles(
            Integer page,
            Integer size
    );

    /**
     * Get role by ID (only active roles)
     */
    RoleDTO getRoleById(UUID id);

    /**
     * Update role by ID
     */
    RoleDTO updateRole(
            UUID id,
            RoleDTO roleDTO
    );

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
    void changeRoleStatus(
            UUID id,
            Boolean status
    );
}