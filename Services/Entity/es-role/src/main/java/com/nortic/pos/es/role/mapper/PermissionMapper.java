package com.nortic.pos.es.role.mapper;

import com.nortic.pos.es.role.model.dto.PermissionResponseDTO;
import com.nortic.pos.es.role.model.entity.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PermissionMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "module", source = "module")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    public abstract PermissionResponseDTO toPermissionResponseDTO(PermissionEntity permissionEntity);

    public abstract List<PermissionResponseDTO> toPermissionResponseDTOList(List<PermissionEntity> permissionEntities);
}
