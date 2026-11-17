package com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.mapper;

import com.nortic.pos.es.user.grpc.UserProto;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class UserGrpcMapper {

    @Mapping(target = "usernameBytes", ignore = true)
    @Mapping(target = "unknownFields", ignore = true)
    @Mapping(target = "passwordBytes", ignore = true)
    @Mapping(target = "mergeUnknownFields", ignore = true)
    @Mapping(target = "mergeFrom", ignore = true)
    @Mapping(target = "clearOneof", ignore = true)
    @Mapping(target = "clearField", ignore = true)
    @Mapping(target = "allFields", ignore = true)
    public abstract UserProto.AuthenticateRequest toAuthenticateRequest(LoginCredentials credentials);

}
