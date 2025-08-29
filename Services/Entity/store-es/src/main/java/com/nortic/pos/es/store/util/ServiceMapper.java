package com.nortic.pos.es.store.util;

import com.nortic.pos.models.dto.StoreDTO;
import com.nortic.pos.models.entity.StoreEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    StoreEntity dtoToEntity(StoreDTO storeDTO);
    StoreDTO entityToDTO(StoreEntity storeEntity);
}
