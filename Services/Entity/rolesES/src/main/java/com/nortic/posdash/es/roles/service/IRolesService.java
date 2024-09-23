package com.nortic.posdash.es.roles.service;

import com.nortic.posdash.es.roles.utils.CustomException;
import com.nortic.posdash.models.dto.RoleDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface IRolesService {
    /**
     * Add new role to database.
     */
    RoleDTO addRole(RoleDTO role) throws CustomException;

    /**
     * Get Role by ID from DB.
     */
    RoleDTO getRoleById(UUID id) throws CustomException;

    /**
     * Get all roles from DB.
     */
    List<RoleDTO> getAllRoles() throws CustomException;

    /**
     * Get all roles from DB with pagination.
     */
    Page<RoleDTO> getAllRoles(int page, int size) throws CustomException;

    /**
     * Get all roles mark as deleted from BD.
     */
    Page<RoleDTO> getAllDeletedRoles(int page, int size) throws CustomException;

    /**
     * Update role by ID form DB.
     */
    RoleDTO updateRole(UUID id, RoleDTO roleDTO) throws CustomException;

    /**
     * Mark a role as Deleted.
     */
    void deleteRoleById(UUID id) throws CustomException;

    /**
     * Delete role permanently of BD.
     */
    void permanentlyDeleteRoleById(UUID id) throws CustomException;

    /**
     * Update role and menu relationship.
     *
     * @param roleId  UUID key search role information.
     * @param menuIds List with UUID's key for search Menu information.
     * @return RoleEntity
     * @throws CustomException Error Information.
     */
    RoleDTO updateRoleMenus(UUID roleId, List<UUID> menuIds) throws CustomException;

    /**
     * This method update role submenus.
     *
     * @param roleId     UUID key for role.
     * @param subMenuIds UUID's of submenus for associate to role.
     * @return RoleDTO
     * @throws CustomException Error Information.
     */
    RoleDTO updateRoleSubMenus(UUID roleId, List<UUID> subMenuIds) throws CustomException;
}
