package com.nortic.pos.es.employeeworkspace.mapper;

import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelCreateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelEmployeeWorkspaceResponseDTO;
import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelPaginatedEmployeeWorkspaceResponseDTO;
import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelUpdateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employeeworkspace.model.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT
)
public abstract class EmployeeWorkspaceRestMapper {

    public abstract CreateEmployeeWorkspaceRequestDTO toCreateEmployeeWorkspaceRequestDTO(RestModelCreateEmployeeWorkspaceRequestDTO restModelCreateEmployeeWorkspaceRequestDTO);

    public abstract UpdateEmployeeWorkspaceRequestDTO toUpdateEmployeeWorkspaceRequestDTO(RestModelUpdateEmployeeWorkspaceRequestDTO restModelUpdateEmployeeWorkspaceRequestDTO);

    public abstract RestModelEmployeeWorkspaceResponseDTO toRestModelEmployeeWorkspaceResponseDTO(EmployeeWorkspaceDTO employeeWorkspaceDTO);

    public abstract RestModelPaginatedEmployeeWorkspaceResponseDTO toRestModelPaginatedEmployeeWorkspaceResponseDTO(PaginatedEmployeeWorkspaceResponseDTO paginatedEmployeeWorkspaceResponseDTO);

    @Mapping(target = "storeId", source = "storeId")
    @Mapping(target = "page", source = "page")
    @Mapping(target = "size", source = "size")
    @Mapping(target = "sort", source = "sort")
    public abstract GetEmployeeWorkspacesByStoreRequestDTO toGetEmployeeWorkspacesByStoreRequestDTO(String storeId, Integer page, Integer size, java.util.List<String> sort);

    @Mapping(target = "employeeId", source = "employeeId")
    @Mapping(target = "page", source = "page")
    @Mapping(target = "size", source = "size")
    @Mapping(target = "sort", source = "sort")
    public abstract GetEmployeeWorkspacesByEmployeeRequestDTO toGetEmployeeWorkspacesByEmployeeRequestDTO(String employeeId, Integer page, Integer size, java.util.List<String> sort);
}
