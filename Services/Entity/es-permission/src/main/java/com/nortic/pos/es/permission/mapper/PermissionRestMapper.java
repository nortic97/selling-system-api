package com.nortic.pos.es.permission.mapper;

import com.nortic.pos.es.permission.dto.request.ChangeStatusRequestDTO;
import com.nortic.pos.es.permission.dto.response.PaginatedPermissionResponseDTO;
import com.nortic.pos.es.permission.dto.response.PermissionDTO;
import com.nortic.pos.es.permission.rest.gen.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class PermissionRestMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract PermissionDTO toPermissionDTO(RestModelPermissionRequestDTO permission);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract PermissionDTO toPermissionDTO(RestModelUpdatePermissionRequestDTO permission);

    public abstract RestModelPermissionResponseDTO toPermissionResponseDTO(PermissionDTO permission);

    public abstract RestModelPaginatedPermissionResponseDTO toListPermissionsResponseDTO(PaginatedPermissionResponseDTO paginatedPermissionResponseDTO);

    public abstract ChangeStatusRequestDTO toChangeStatusRequestDTO(RestModelStatusRequest restModelStatusRequest);

}
