package com.nortic.pos.es.role.mapper;

import com.google.protobuf.Timestamp;
import com.nortic.pos.es.role.grpc.RoleProto;
import com.nortic.pos.es.role.model.dto.ChangeStatusRequestDTO;
import com.nortic.pos.es.role.model.dto.PaginatedRoleResponseDTO;
import com.nortic.pos.es.role.model.dto.RoleDTO;
import org.mapstruct.*;

import java.time.OffsetDateTime;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public abstract class RoleGrpcMapper {

    public RoleProto.PaginatedRoleResponse toPaginatedRoleResponse(PaginatedRoleResponseDTO dto) {

        var builder = RoleProto.PaginatedRoleResponse.newBuilder();

        builder.setNumber(dto.getNumber());
        builder.setSize(dto.getSize());
        builder.setTotalElements(dto.getTotalElements());
        builder.setTotalPages(dto.getTotalPages());
        builder.setLast(dto.getLast());

        if (dto.getContent() != null) {
            builder.addAllContent(dto.getContent().stream().map(this::toRoleResponse).toList());
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
    @Mapping(target = "storeIdBytes", ignore = true)
    @Mapping(target = "nameBytes", ignore = true)
    @Mapping(target = "descriptionBytes", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    @Mapping(target = "createdAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "updatedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(target = "deletedAt", qualifiedByName = "offsetDateTimeToTimestamp")
    public abstract RoleProto.RoleResponse toRoleResponse(RoleDTO dto);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract RoleDTO toRoleDTO(RoleProto.RoleRequest request);

    @Named("offsetDateTimeToTimestamp")
    protected Timestamp offsetDateTimeToTimestamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return Timestamp.newBuilder().setSeconds(offsetDateTime.toEpochSecond()).setNanos(offsetDateTime.getNano()).build();
    }

    public abstract ChangeStatusRequestDTO toChangeStatusRequestDTO(RoleProto.ChangeStatusRequest request);

    @Mapping(target = "storeId", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract RoleDTO toRoleDTO(RoleProto.UpdateRoleRequest request);
}
