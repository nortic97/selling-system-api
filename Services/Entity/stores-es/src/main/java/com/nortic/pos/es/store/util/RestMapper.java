package com.nortic.pos.es.store.util;

import com.nortic.pos.es.store.grpc.gen.Store;
import com.nortic.pos.es.store.rest.gen.model.RestModelStore;
import com.nortic.pos.models.dto.StoreDTO;
import com.nortic.pos.models.entity.StoreEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestMapper {
    StoreDTO restModelToDTO(RestModelStore restModelStore);
    StoreEntity dtoToEntity(StoreDTO storeDTO);
    StoreDTO entityToDTO(StoreEntity storeEntity);
    RestModelStore dtoToRestModel(StoreDTO storeDTO);
}
