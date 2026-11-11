package com.nortic.pos.es.employee.mapper;

import com.nortic.pos.es.employee.model.dto.*;
import com.nortic.pos.es.employee.rest.gen.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT
)
public abstract class EmployeeRestMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract EmployeeDTO toEmployeeDTO(RestModelCreateEmployeeRequestDTO employee);

    @Mapping(target = "storeId", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract EmployeeDTO toEmployeeDTO(RestModelUpdateEmployeeRequestDTO employee);

    public abstract RestModelEmployeeResponseDTO toRestModelEmployeeResponseDTO(EmployeeDTO employee);

    public abstract RestModelPaginatedEmployeeResponseDTO toRestModelPaginatedEmployeeResponseDTO(PaginatedEmployeeResponseDTO paginatedEmployeeResponseDTO);

    public abstract ChangeEmailRequestDTO toChangeEmailRequestDTO(RestModelChangeEmailRequest restModelChangeEmailRequest);

    public abstract ChangePhoneRequestDTO toChangePhoneRequestDTO(RestModelChangePhoneRequest restModelChangePhoneRequest);

    public abstract ChangePasswordRequestDTO toChangePasswordRequestDTO(RestModelChangePasswordRequest restModelChangePasswordRequest);

    public abstract ChangeEmployeeStatusRequestDTO toChangeEmployeeStatusRequestDTO(RestModelChangeEmployeeStatusRequest restModelChangeEmployeeStatusRequest);

    public abstract ChangeUsernameRequestDTO toChangeusernameRequestDTO(RestModelChangeUsernameRequest restModelChangeusernameRequest);

    public abstract AuthenticateRequestDTO toAuthenticateRequestDTO(RestModelAuthenticateRequest restModelAuthenticateRequest);

    public abstract EmployeeQueryRequestDTO toEmployeeQueryRequestDTO(RestModelEmployeeQueryRequestDTO restModelEmployeeQueryRequestDTO);

    public abstract EmployeeSearchRequestDTO toEmployeeSearchRequestDTO(RestModelEmployeeSearchRequestDTO restModelEmployeeSearchRequestDTO);

}
