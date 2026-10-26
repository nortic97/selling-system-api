package com.nortic.pos.es.role.service.contract;

import com.nortic.pos.es.role.model.dto.*;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    RoleDTO getRole(RequestContextDTO requestContextDTO, UUID id);

    RoleDTO createRole(RequestContextDTO requestContextDTO, RoleDTO roleDTO);

    PaginatedRoleResponseDTO listRoles(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO);

    void deleteRole(RequestContextDTO requestContextDTO, UUID id);

    RoleDTO restoreRole(RequestContextDTO requestContextDTO, UUID id);

    RoleDTO updateRole(RequestContextDTO requestContextDTO, UUID id, RoleDTO roleDTO);

    RoleDTO changeStatus(RequestContextDTO requestContextDTO, UUID id, ChangeStatusRequestDTO changeStatusRequestDTO);

    List<PermissionResponseDTO> getRolePermission(RequestContextDTO requestContextDTO, UUID roleId);

    List<PermissionResponseDTO> addPermissionToRole(RequestContextDTO requestContextDTO, UUID roleId, PermissionRequestDTO permissionRequestDTO);

    void removePermissionsFromRole(RequestContextDTO requestContextDTO, UUID roleId, PermissionRequestDTO permissionRequestDTO);

    void removePermissionFromRole(RequestContextDTO requestContextDTO, UUID roleId, UUID permissionId);
}
