package com.nortic.pos.es.role.util;

import com.nortic.pos.es.role.model.RestModelRole;
import com.nortic.pos.es.role.model.RestModelRoleRequest;
import com.nortic.pos.models.dto.RoleDTO;
import com.nortic.pos.models.dto.StoreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.OffsetDateTime;
import java.util.UUID;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface RestMapper {

    @Mapping(target = "storeId", source = "store", qualifiedByName = "mapStoreToUUID")
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(target = "deletedAt", source = "deletedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    RestModelRole dtoToRes(RoleDTO roleDTO);

    @Mapping(target = "store", source = "storeId", qualifiedByName = "mapUUIDToStore")
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(target = "deletedAt", source = "deletedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    RoleDTO resToDto(RestModelRole restModelRole);

    @Mapping(target = "store", source = "storeId", qualifiedByName = "mapUUIDToStore")
    RoleDTO roleRequestToDTO(RestModelRoleRequest restModelRoleRequest);

    @Named("mapStoreToUUID")
    default UUID mapStoreToUUID(StoreDTO store) {
        return store != null ? store.getId() : null;
    }

    @Named("mapUUIDToStore")
    default StoreDTO mapUUIDToStore(UUID storeId) {
        if (storeId == null) {
            return null;
        }
        return StoreDTO.builder()
                .id(storeId)
                .build();
    }
}
