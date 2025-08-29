package com.nortic.pos.es.store.util;

import com.nortic.pos.es.store.model.RestModelStore;
import com.nortic.pos.models.dto.StoreDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestMapper {
    StoreDTO restModelToDTO(RestModelStore restModelStore);
    RestModelStore dtoToRestModel(StoreDTO storeDTO);
}
