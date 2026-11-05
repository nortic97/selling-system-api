package com.nortic.pos.es.permission.mapper;

import com.nortic.pos.es.permission.model.dto.PaginatedPermissionResponseDTO;
import com.nortic.pos.es.permission.model.dto.PermissionDTO;
import com.nortic.pos.es.permission.model.entity.PermissionEntity;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class PermissionEntityMapper {

    @Mapping(target = "code", qualifiedByName = "toLowerCase")
    @Mapping(target = "module", qualifiedByName = "toLowerCase")
    public abstract PermissionEntity toPermissionEntity(PermissionDTO permissionDTO);

    public abstract PermissionDTO toPermissionDTO(PermissionEntity permissionEntity);

    @Mapping(target = "number", expression = "java(page.getNumber() + 1)")
    public abstract PaginatedPermissionResponseDTO toListPermissionDTO(Page<PermissionEntity> page);

    public abstract void updatePermissionFromDTO(PermissionDTO permissionDTO, @MappingTarget PermissionEntity permissionEntity);

    @Named("toLowerCase")
    protected String toLowerCase(String value) {
        return value != null ? value.toLowerCase() : null;
    }

}
