package com.nortic.pos.es.store.mapper;

import com.nortic.pos.es.store.model.dto.ChangeEmailRequestDTO;
import com.nortic.pos.es.store.model.dto.ChangePhoneRequestDTO;
import com.nortic.pos.es.store.model.dto.ChangeStatusRequestDTO;
import com.nortic.pos.es.store.model.dto.PaginatedStoreResponseDTO;
import com.nortic.pos.es.store.model.dto.StoreDTO;
import com.nortic.pos.es.store.rest.gen.model.RestModelEmailRequestDTO;
import com.nortic.pos.es.store.rest.gen.model.RestModelPaginatedStoreResponseDTO;
import com.nortic.pos.es.store.rest.gen.model.RestModelPhoneRequestDTO;
import com.nortic.pos.es.store.rest.gen.model.RestModelStatusRequest;
import com.nortic.pos.es.store.rest.gen.model.RestModelStoreRequestDTO;
import com.nortic.pos.es.store.rest.gen.model.RestModelStoreResponseDTO;
import com.nortic.pos.es.store.rest.gen.model.RestModelUpdateStoreRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class StoreRestMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract StoreDTO toStoreDTO(RestModelStoreRequestDTO store);

    public abstract RestModelStoreResponseDTO toStoreResponseDTO(StoreDTO store);

    public abstract RestModelPaginatedStoreResponseDTO toListStoresResponseDTO(PaginatedStoreResponseDTO paginatedStoreResponseDTO);

    public abstract ChangeEmailRequestDTO toChangeEmailRequestDTO(RestModelEmailRequestDTO restModelEmailRequestDTO);

    public abstract ChangePhoneRequestDTO toChangePhoneRequestDTO(RestModelPhoneRequestDTO restModelPhoneRequestDTO);

    public abstract ChangeStatusRequestDTO toChangeStatusRequestDTO(RestModelStatusRequest restModelStatusRequest);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contactEmail", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract StoreDTO toUpdateStoreRequestDTO(RestModelUpdateStoreRequestDTO restModelUpdateStoreRequestDTO);
}
