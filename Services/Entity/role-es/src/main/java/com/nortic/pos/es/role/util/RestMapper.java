package com.nortic.pos.es.role.util;

import com.nortic.pos.es.role.model.*;
import com.nortic.pos.models.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RestMapper {

    @Mapping(target = "storeId", source = "store", qualifiedByName = "mapSotoreToUUID")
    RestModelRole toDTO(RoleDTO roleDTO);

    @Named("mapSotoreToUUID")
    default UUID mapSotoreToUUID(StoreDTO store){
        return store != null ? store.getId() : null;
    }
}
