package com.nortic.pos.es.role.mapper;

import com.nortic.pos.es.role.model.dto.PaginatedRoleResponseDTO;
import com.nortic.pos.es.role.model.dto.RoleDTO;
import com.nortic.pos.es.role.model.entity.RoleEntity;
import com.nortic.pos.es.role.model.entity.StoreEntity;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class RoleEntityMapper {

    @Mapping(target = "storeId", expression = "java(mapStore(roleDTO.getStoreId()))")
    @Mapping(target = "name", source = "name", qualifiedByName = "normalizeString")
    public abstract RoleEntity toRoleEntity(RoleDTO roleDTO);

    @Mapping(target = "storeId", source = "storeId.id")
    public abstract RoleDTO toRoleDTO(RoleEntity roleEntity);

    @Mapping(target = "number", expression = "java(page.getNumber() + 1)")
    public abstract PaginatedRoleResponseDTO toListRoleDTO(Page<RoleEntity> page);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "storeId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "name", source = "name", qualifiedByName = "normalizeString")
    public abstract void updateRoleFromDTO(RoleDTO roleDTO, @MappingTarget RoleEntity roleEntity);

    protected StoreEntity mapStore(UUID storeId) {
        if (storeId == null) {
            return null;
        }
        return StoreEntity.builder().id(storeId).build();
    }

    @Named("normalizeString")
    protected String normalizeString(String value) {
        if (value == null) {
            return null;
        }
        return value.trim();
    }
}
