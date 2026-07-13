package com.nortic.pos.es.store.mapper;

import com.nortic.pos.es.store.dto.response.StoreDTO;
import com.nortic.pos.es.store.entity.StoreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

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

    @Named("toLowerCase")
    protected String toLowerCase(String value) {
        return value != null ? value.toLowerCase() : null;
    }

}
