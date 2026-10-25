package com.nortic.pos.es.role.mapper;

import com.nortic.pos.es.role.model.dto.ChangeStatusRequestDTO;
import com.nortic.pos.es.role.model.dto.PaginatedRoleResponseDTO;
import com.nortic.pos.es.role.model.dto.RoleDTO;
import com.nortic.pos.es.role.rest.gen.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class RoleRestMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract RoleDTO toRoleDTO(RestModelRoleRequestDTO role);

    public abstract RestModelRoleResponseDTO toRoleResponseDTO(RoleDTO role);

    public abstract RestModelPaginatedRoleResponseDTO toListRolesResponseDTO(PaginatedRoleResponseDTO paginatedRoleResponseDTO);

    public abstract ChangeStatusRequestDTO toChangeStatusRequestDTO(RestModelStatusRequest restModelStatusRequest);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "storeId", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract RoleDTO toUpdateRoleRequestDTO(RestModelUpdateRoleRequestDTO restModelUpdateRoleRequestDTO);
}
