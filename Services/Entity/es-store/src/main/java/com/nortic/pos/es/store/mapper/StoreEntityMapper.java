package com.nortic.pos.es.store.mapper;

import com.nortic.pos.es.store.dto.response.PaginatedStoreResponseDTO;
import com.nortic.pos.es.store.dto.response.StoreDTO;
import com.nortic.pos.es.store.entity.StoreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT
)
public abstract class StoreEntityMapper {

    @Mapping(target = "name", qualifiedByName = "toLowerCase")
    @Mapping(target = "email", qualifiedByName = "toLowerCase")
    @Mapping(target = "ownerId.id", source = "ownerId")
    public abstract StoreEntity toStoreEntity(StoreDTO storeDTO);

    @Mapping(target = "ownerId", source = "ownerId.id")
    public abstract StoreDTO toStoreDTO(StoreEntity storeEntity);

    @Mapping(target = "number", expression = "java(page.getNumber() + 1)")
    public abstract PaginatedStoreResponseDTO toListStoreDTO(Page<StoreEntity> page);

    @Named("toLowerCase")
    protected String toLowerCase(String value) {
        return value != null ? value.toLowerCase() : null;
    }

}
