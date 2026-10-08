package com.nortic.pos.es.permission.service.contract;

import com.nortic.pos.es.permission.dto.SearchParamsDTO;
import com.nortic.pos.es.permission.dto.request.ChangeStatusRequestDTO;
import com.nortic.pos.es.permission.dto.request.RequestContextDTO;
import com.nortic.pos.es.permission.dto.response.PaginatedPermissionResponseDTO;
import com.nortic.pos.es.permission.dto.response.PermissionDTO;

import java.util.UUID;

public interface PermissionService {

    PermissionDTO getPermission(RequestContextDTO requestContextDTO, UUID id);

    PermissionDTO createPermission(RequestContextDTO requestContextDTO, PermissionDTO permissionDTO);

    PaginatedPermissionResponseDTO listPermissions(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO);

    void deletePermission(RequestContextDTO requestContextDTO, UUID id);

    PermissionDTO restorePermission(RequestContextDTO requestContextDTO, UUID id);

    PermissionDTO updatePermission(RequestContextDTO requestContextDTO, UUID id, PermissionDTO permissionDTO);

    PermissionDTO changeStatus(RequestContextDTO requestContextDTO, UUID id, ChangeStatusRequestDTO changeStatusRequestDTO);

}
