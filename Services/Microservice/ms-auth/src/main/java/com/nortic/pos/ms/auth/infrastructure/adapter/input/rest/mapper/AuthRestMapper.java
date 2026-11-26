package com.nortic.pos.ms.auth.infrastructure.adapter.input.rest.mapper;

import com.nortic.pos.ms.auth.domain.model.AuthResponse;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;
import com.nortic.pos.ms.auth.domain.model.TokenExchange;
import com.nortic.pos.ms.auth.rest.gen.model.RestModelEmployeeLoginRequestDTO;
import com.nortic.pos.ms.auth.rest.gen.model.RestModelLoginRequestDTO;
import com.nortic.pos.ms.auth.rest.gen.model.RestModelLoginResponseDTO;
import com.nortic.pos.ms.auth.rest.gen.model.RestModelTokenExchangeRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class AuthRestMapper {
    @Mapping(target = "storeId", ignore = true)
    @Mapping(target = "accessTypeEnum", source = "accessType")
    public abstract LoginCredentials toLoginCredentials(RestModelLoginRequestDTO restModelLoginRequest);
    @Mapping(target = "accessTypeEnum", source = "accessType")
    public abstract LoginCredentials toLoginCredentials(RestModelEmployeeLoginRequestDTO restModelLoginRequest);
    @Mapping(target = "tokenType", source = "tokenTypeEnum")
    public abstract RestModelLoginResponseDTO toLoginResponse(AuthResponse authResponse);
    public abstract TokenExchange toTokenExchange(RestModelTokenExchangeRequestDTO restModelTokenExchangeRequestDTO);
}
