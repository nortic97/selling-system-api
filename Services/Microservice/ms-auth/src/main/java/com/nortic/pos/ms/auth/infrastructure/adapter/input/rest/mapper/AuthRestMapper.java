package com.nortic.pos.ms.auth.infrastructure.adapter.input.rest.mapper;

import com.nortic.pos.ms.auth.domain.model.AuthResponse;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;
import com.nortic.pos.ms.auth.rest.gen.model.RestModelLoginRequestDTO;
import com.nortic.pos.ms.auth.rest.gen.model.RestModelLoginResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class AuthRestMapper {
    @Mapping(target = "accessTypeEnum", source = "accessType")
    public abstract LoginCredentials toLoginCredentials(RestModelLoginRequestDTO restModelLoginRequest);
    @Mapping(target = "tokenType", source = "tokenTypeEnum")
    public abstract RestModelLoginResponseDTO toLoginResponse(AuthResponse authResponse);
}
