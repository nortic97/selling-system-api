package com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.mapper;

import com.google.protobuf.Timestamp;
import com.nortic.pos.es.user.grpc.UserProto;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;
import com.nortic.pos.ms.auth.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class UserGrpcMapper {

    public abstract User toUser(UserProto.UserResponse userResponse);

    @Mapping(target = "usernameBytes", ignore = true)
    @Mapping(target = "unknownFields", ignore = true)
    @Mapping(target = "passwordBytes", ignore = true)
    @Mapping(target = "mergeUnknownFields", ignore = true)
    @Mapping(target = "mergeFrom", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    public abstract UserProto.AuthenticateRequest toAuthenticateRequest(LoginCredentials credentials);

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
