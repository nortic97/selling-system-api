package com.nortic.pos.es.employee.mapper;

import com.nortic.pos.es.employee.model.dto.AuthenticateRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangeEmailRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangeEmployeeStatusRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangePasswordRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangePhoneRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangeusernameRequestDTO;
import com.nortic.pos.es.employee.model.dto.EmployeeDTO;
import com.nortic.pos.es.employee.model.dto.EmployeeQueryRequestDTO;
import com.nortic.pos.es.employee.model.dto.EmployeeSearchRequestDTO;
import com.nortic.pos.es.employee.model.dto.PaginatedEmployeeResponseDTO;
import com.nortic.pos.es.employee.rest.gen.model.RestModelAuthenticateRequest;
import com.nortic.pos.es.employee.rest.gen.model.RestModelChangeEmailRequest;
import com.nortic.pos.es.employee.rest.gen.model.RestModelChangeEmployeeStatusRequest;
import com.nortic.pos.es.employee.rest.gen.model.RestModelChangePasswordRequest;
import com.nortic.pos.es.employee.rest.gen.model.RestModelChangePhoneRequest;
import com.nortic.pos.es.employee.rest.gen.model.RestModelCreateEmployeeRequestDTO;
import com.nortic.pos.es.employee.rest.gen.model.RestModelEmployeeQueryRequestDTO;
import com.nortic.pos.es.employee.rest.gen.model.RestModelEmployeeResponseDTO;
import com.nortic.pos.es.employee.rest.gen.model.RestModelEmployeeSearchRequestDTO;
import com.nortic.pos.es.employee.rest.gen.model.RestModelPaginatedEmployeeResponseDTO;
import com.nortic.pos.es.employee.rest.gen.model.RestModelUpdateEmployeeRequestDTO;
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

    public abstract ChangeUsernameRequestDTO toChangeUsernameRequestDTO(RestModelChangeUsernameRequest restModelChangeUsernameRequest);

    public abstract AuthenticateRequestDTO toAuthenticateRequestDTO(RestModelAuthenticateRequest restModelAuthenticateRequest);

    public abstract EmployeeQueryRequestDTO toEmployeeQueryRequestDTO(RestModelEmployeeQueryRequestDTO restModelEmployeeQueryRequestDTO);

    public abstract EmployeeSearchRequestDTO toEmployeeSearchRequestDTO(RestModelEmployeeSearchRequestDTO restModelEmployeeSearchRequestDTO);

}
