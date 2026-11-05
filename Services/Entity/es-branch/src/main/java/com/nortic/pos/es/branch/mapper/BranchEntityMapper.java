package com.nortic.pos.es.branch.mapper;

import com.nortic.pos.es.branch.model.dto.BranchDTO;
import com.nortic.pos.es.branch.model.dto.PaginatedBranchResponseDTO;
import com.nortic.pos.es.branch.model.entity.BranchEntity;
import com.nortic.pos.es.branch.model.entity.StoreEntity;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class BranchEntityMapper {

    @Mapping(target = "storeId", expression = "java(mapStore(branchDTO.getStoreId()))")
    @Mapping(target = "email", source = "email", qualifiedByName = "normalizeString")
    public abstract BranchEntity toBranchEntity(BranchDTO branchDTO);

    @Mapping(target = "storeId", source = "storeId.id")
    public abstract BranchDTO toBranchDTO(BranchEntity branchEntity);

    @Mapping(target = "number", expression = "java(page.getNumber() + 1)")
    public abstract PaginatedBranchResponseDTO toListBranchDTO(Page<BranchEntity> page);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "storeId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "email", source = "email", qualifiedByName = "normalizeString")
    public abstract void updateBranchFromDTO(BranchDTO branchDTO, @MappingTarget BranchEntity branchEntity);

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
        return value.trim().toLowerCase();
    }
}
