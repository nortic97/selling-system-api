package com.nortic.pos.es.role.service.contract;

import com.nortic.pos.es.role.model.dto.*;

import java.util.UUID;

public interface RoleService {

    RoleDTO getRole(RequestContextDTO requestContextDTO, UUID id);

    RoleDTO createRole(RequestContextDTO requestContextDTO, RoleDTO roleDTO);

    PaginatedRoleResponseDTO listRoles(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO);

    void deleteRole(RequestContextDTO requestContextDTO, UUID id);

    RoleDTO restoreRole(RequestContextDTO requestContextDTO, UUID id);

    RoleDTO updateRole(RequestContextDTO requestContextDTO, UUID id, RoleDTO roleDTO);

    RoleDTO changeStatus(RequestContextDTO requestContextDTO, UUID id, ChangeStatusRequestDTO changeStatusRequestDTO);
}
