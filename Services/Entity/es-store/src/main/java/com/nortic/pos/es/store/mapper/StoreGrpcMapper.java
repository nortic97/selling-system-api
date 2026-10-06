package com.nortic.pos.es.store.mapper;

import com.google.protobuf.Timestamp;
import com.nortic.pos.es.store.dto.request.ChangeEmailRequestDTO;
import com.nortic.pos.es.store.dto.request.ChangePhoneRequestDTO;
import com.nortic.pos.es.store.dto.request.ChangeStatusRequestDTO;
import com.nortic.pos.es.store.dto.response.PaginatedStoreResponseDTO;
import com.nortic.pos.es.store.dto.response.StoreDTO;
import com.nortic.pos.es.store.grpc.StoreProto;
import org.mapstruct.*;

import java.time.OffsetDateTime;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public abstract class StoreGrpcMapper {

    public StoreProto.PaginatedStoreResponse toPaginatedStoreResponse(PaginatedStoreResponseDTO dto) {

        var builder = StoreProto.PaginatedStoreResponse.newBuilder();

        builder.setNumber(dto.getNumber());
        builder.setSize(dto.getSize());
        builder.setTotalElements(dto.getTotalElements());
        builder.setTotalPages(dto.getTotalPages());
        builder.setLast(dto.getLast());
        builder.setSize(dto.getSize());

        if (dto.getContent() != null) {
            builder.addAllContent(dto.getContent().stream().map(this::toStoreResponse).toList());
        }

        return builder.build();
    }

    @Mapping(target = "unknownFields", ignore = true)
    @Mapping(target = "mergeUpdatedAt", ignore = true)
    @Mapping(target = "mergeUnknownFields", ignore = true)
    @Mapping(target = "mergeFrom", ignore = true)
    @Mapping(target = "mergeDeletedAt", ignore = true)
    @Mapping(target = "mergeCreatedAt", ignore = true)
    @Mapping(target = "idBytes", ignore = true)
    @Mapping(target = "ownerIdBytes", ignore = true)
    @Mapping(target = "taxIdBytes", ignore = true)
    @Mapping(target = "nameBytes", ignore = true)
    @Mapping(target = "descriptionBytes", ignore = true)
    @Mapping(target = "sloganBytes", ignore = true)
    @Mapping(target = "logoBytes", ignore = true)
    @Mapping(target = "addressBytes", ignore = true)
    @Mapping(target = "countryBytes", ignore = true)
    @Mapping(target = "stateProvinceBytes", ignore = true)
    @Mapping(target = "cityBytes", ignore = true)
    @Mapping(target = "phoneNumberBytes", ignore = true)
    @Mapping(target = "emailBytes", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    @Mapping(target = "createdAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "updatedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "deletedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    public abstract StoreProto.StoreResponse toStoreResponse(StoreDTO dto);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract StoreDTO toStoreDTO(StoreProto.StoreRequest request);

    @Named("offsetDateTimeToTimestamp")
    protected Timestamp offsetDateTimeToTimestamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return Timestamp.newBuilder().setSeconds(offsetDateTime.toEpochSecond()).setNanos(offsetDateTime.getNano()).build();
    }

    public abstract ChangeStatusRequestDTO toChangeStatusRequestDTO(StoreProto.ChangeStatusRequest request);

    public abstract ChangePhoneRequestDTO toChangePhoneRequestDTO(StoreProto.ChangePhoneRequest request);

    public abstract ChangeEmailRequestDTO toChangeEmailRequestDTO(StoreProto.ChangeEmailRequest request);

    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract StoreDTO toStoreDTO(StoreProto.UpdateStoreRequest request);
}
