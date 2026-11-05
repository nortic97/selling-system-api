package com.nortic.pos.es.user.mapper;

import com.nortic.pos.es.user.model.dto.*;
import com.nortic.pos.es.user.rest.gen.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT
)
public abstract class UserRestMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract UserDTO toUserDTO(RestModelCreateUserRequestDTO user);

    @Mapping(target = "username", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract UserDTO toUserDTO(RestModelUpdateUserRequestDTO user);

    public abstract RestModelUserResponseDTO toRestModelUserResponseDTO(UserDTO user);

    public abstract RestModelPaginatedUserResponseDTO toRestModelPaginatedUserResponseDTO(PaginatedUserResponseDTO paginatedUserResponseDTO);

    public abstract ChangeEmailRequestDTO toChangeEmailRequestDTO(RestModelChangeEmailRequest restModelChangeEmailRequest);

    public abstract ChangePhoneRequestDTO toChangePhoneRequestDTO(RestModelChangePhoneRequest restModelChangePhoneRequest);

    public abstract ChangePasswordRequestDTO toChangePasswordRequestDTO(RestModelChangePasswordRequest restModelChangePasswordRequest);

    public abstract ChangeUserStatusRequestDTO toChangeUserStatusRequestDTO(RestModelChangeUserStatusRequest restModelChangeUserStatusRequest);

    public abstract ChangeUsernameRequestDTO toChangeUsernameRequestDTO(RestModelChangeUsernameRequest restModelChangeUsernameRequest);

    public abstract AuthenticateRequestDTO toAuthenticateRequestDTO(RestModelAuthenticateRequest restModelAuthenticateRequest);

    public abstract UserQueryRequestDTO toUserQueryRequestDTO(RestModelUserQueryRequestDTO restModelUserQueryRequestDTO);

    public abstract UserSearchRequestDTO toUserSearchRequestDTO(RestModelUserSearchRequestDTO restModelUserSearchRequestDTO);

}
