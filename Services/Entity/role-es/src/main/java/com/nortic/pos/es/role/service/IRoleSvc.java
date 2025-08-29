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
    RoleDTO getRole(UUID id);

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

    /**
     * Get all ACTIVE roles with dynamic filtering and pagination
     * All parameters are optional - null values are ignored in filtering
     *
     * @param pageable Pagination and sorting information
     * @param storeId Filter by store ID (optional - null returns all stores)
     * @param name Filter by role name (optional - null returns all names)
     * @param isActive Filter by active status (optional - null returns all statuses)
     * @return Page of active roles matching the filters
     */
    Page<RoleDTO> getRoles(
            Pageable pageable,
            UUID storeId,
            String name,
            Boolean isActive
    );

    /**
     * Get all DELETED roles with dynamic filtering and pagination
     * Same filtering capabilities as getRoles for consistency
     *
     * @param pageable Pagination and sorting information
     * @param storeId Filter by store ID (optional - null returns all stores)
     * @param name Filter by role name (optional - null returns all names)
     * @return Page of deleted roles matching the filters
     */
    Page<RoleDTO> getDeletedRoles(
            Pageable pageable,
            UUID storeId,
            String name
    );
}