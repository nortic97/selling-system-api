package com.nortic.pos.es.permission.mapper;

import com.google.protobuf.Timestamp;
import com.nortic.pos.es.permission.grpc.PermissionProto;
import com.nortic.pos.es.permission.model.dto.ChangeStatusRequestDTO;
import com.nortic.pos.es.permission.model.dto.PaginatedPermissionResponseDTO;
import com.nortic.pos.es.permission.model.dto.PermissionDTO;
import org.mapstruct.*;

import java.time.OffsetDateTime;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public abstract class PermissionGrpcMapper {

    public PermissionProto.PaginatedPermissionResponse toPaginatedPermissionResponse(PaginatedPermissionResponseDTO dto) {

        var builder = PermissionProto.PaginatedPermissionResponse.newBuilder();

        builder.setNumber(dto.getNumber());
        builder.setSize(dto.getSize());
        builder.setTotalElements(dto.getTotalElements());
        builder.setTotalPages(dto.getTotalPages());
        builder.setLast(dto.getLast());
        builder.setFirst(dto.getFirst());

        if (dto.getContent() != null) {
            builder.addAllContent(dto.getContent().stream().map(this::toPermissionResponse).toList());
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
    @Mapping(target = "codeBytes", ignore = true)
    @Mapping(target = "moduleBytes", ignore = true)
    @Mapping(target = "nameBytes", ignore = true)
    @Mapping(target = "descriptionBytes", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    @Mapping(target = "createdAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "updatedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "deletedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    public abstract PermissionProto.PermissionResponse toPermissionResponse(PermissionDTO dto);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    public abstract PermissionDTO toPermissionDTO(PermissionProto.PermissionRequest request);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    public abstract PermissionDTO toPermissionDTO(PermissionProto.UpdatePermissionRequest request);

    @Mapping(target = "active", source = "isActive")
    public abstract ChangeStatusRequestDTO toChangeStatusRequestDTO(PermissionProto.ChangeStatusRequest request);

    @Named("offsetDateTimeToTimestamp")
    protected Timestamp offsetDateTimeToTimestamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return Timestamp.newBuilder().setSeconds(offsetDateTime.toEpochSecond()).setNanos(offsetDateTime.getNano()).build();
    }
}
