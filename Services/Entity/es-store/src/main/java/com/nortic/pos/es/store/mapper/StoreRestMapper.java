package com.nortic.pos.es.store.mapper;

import com.nortic.pos.es.store.dto.response.StoreDTO;
import com.nortic.pos.es.store.rest.gen.model.RestModelStoreRequestDTO;
import com.nortic.pos.es.store.rest.gen.model.RestModelStoreResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT
)
public abstract class StoreRestMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract StoreDTO toStoreDTO(RestModelStoreRequestDTO store);

    public abstract RestModelStoreResponseDTO toStoreResponseDTO(StoreDTO store);

}
