package com.nortic.pos.ms.auth.infrastructure.adapter.input.rest.mapper;

import com.nortic.pos.ms.auth.domain.model.AuthResponse;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;
import com.nortic.pos.ms.auth.rest.gen.model.RestModelLoginRequest;
import com.nortic.pos.ms.auth.rest.gen.model.RestModelLoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class AuthRestMapper {
    public abstract LoginCredentials toLoginCredentials(RestModelLoginRequest restModelLoginRequest);
    public abstract RestModelLoginResponse toLoginResponse(AuthResponse authResponse);
}
