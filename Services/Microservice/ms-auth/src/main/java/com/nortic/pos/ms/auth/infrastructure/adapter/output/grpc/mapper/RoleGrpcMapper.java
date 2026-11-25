package com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.mapper;

import com.google.protobuf.Timestamp;
import com.nortic.pos.es.role.grpc.RoleProto;
import com.nortic.pos.es.role.grpc.RoleServiceGrpc;
import com.nortic.pos.ms.auth.domain.model.PermissionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class RoleGrpcMapper {

    @Mapping(target = "unknownFields", ignore = true)
    @Mapping(target = "mergeUnknownFields", ignore = true)
    @Mapping(target = "mergeFrom", ignore = true)
    @Mapping(target = "idBytes", ignore = true)
    @Mapping(target = "id", source = "roleId")
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    public abstract RoleProto.RoleIdRequest toPermissionIdRequest(UUID roleId);

    @Named("toPermissionResponseDTO")
    protected abstract PermissionResponseDTO mapPermission(
            RoleProto.PermissionResponse permissionResponse
    );

    public List<PermissionResponseDTO> toPermissionResponseDTO(
            Iterator<RoleProto.PermissionResponse> permissions
    ) {
        List<PermissionResponseDTO> result = new ArrayList<>();

        permissions.forEachRemaining(
                permission -> result.add(mapPermission(permission))
        );

        return result;
    }

    protected OffsetDateTime map(Timestamp value) {
        if (value == null) {
            return null;
        }

        return Instant.ofEpochSecond(
                value.getSeconds(),
                value.getNanos()
        ).atOffset(ZoneOffset.UTC);
    }
}
