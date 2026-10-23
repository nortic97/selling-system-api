package com.nortic.pos.es.store.mapper;

import com.nortic.pos.es.store.model.dto.PaginatedStoreResponseDTO;
import com.nortic.pos.es.store.model.dto.StoreDTO;
import com.nortic.pos.es.store.model.entity.StoreEntity;
import com.nortic.pos.es.store.model.entity.UserEntity;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class StoreEntityMapper {

    @Mapping(target = "ownerId", expression = "java(mapOwner(storeDTO.getOwnerId()))")
    @Mapping(target = "taxId", source = "taxId", qualifiedByName = "normalizeString")
    @Mapping(target = "contactEmail", source = "contactEmail", qualifiedByName = "normalizeString")
    public abstract StoreEntity toStoreEntity(StoreDTO storeDTO);

    @Mapping(target = "ownerId", source = "ownerId.id")
    public abstract StoreDTO toStoreDTO(StoreEntity storeEntity);

    @Mapping(target = "number", expression = "java(page.getNumber() + 1)")
    public abstract PaginatedStoreResponseDTO toListStoreDTO(Page<StoreEntity> page);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "contactEmail", ignore = true)
    @Mapping(target = "taxId", source = "taxId", qualifiedByName = "normalizeString")
    public abstract void updateStoreFromDTO(StoreDTO storeDTO, @MappingTarget StoreEntity storeEntity);

    protected UserEntity mapOwner(UUID ownerId) {
        if (ownerId == null) {
            return null;
        }
        return UserEntity.builder().id(ownerId).build();
    }

    @Named("normalizeString")
    protected String normalizeString(String value) {
        if (value == null) {
            return null;
        }
        return value.trim().toLowerCase();
    }
}
