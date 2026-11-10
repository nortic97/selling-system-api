package com.nortic.pos.es.employeeworkspace.mapper;

import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelCreateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelEmployeeWorkspaceResponseDTO;
import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelPaginatedEmployeeWorkspaceResponseDTO;
import com.nortic.pos.es.employee_workspace.rest.gen.model.RestModelUpdateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employeeworkspace.model.dto.CreateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employeeworkspace.model.dto.EmployeeWorkspaceDTO;
import com.nortic.pos.es.employeeworkspace.model.dto.PaginatedEmployeeWorkspaceResponseDTO;
import com.nortic.pos.es.employeeworkspace.model.dto.UpdateEmployeeWorkspaceRequestDTO;
import org.mapstruct.Mapper;
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
}
