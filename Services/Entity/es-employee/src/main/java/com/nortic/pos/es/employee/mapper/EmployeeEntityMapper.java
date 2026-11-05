package com.nortic.pos.es.employee.mapper;

import com.nortic.pos.es.employee.model.dto.PaginatedEmployeeResponseDTO;
import com.nortic.pos.es.employee.model.dto.EmployeeDTO;
import com.nortic.pos.es.employee.model.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@RequiredArgsConstructor
public abstract class EmployeeEntityMapper {

    public abstract EmployeeDTO toEmployeeDTO(EmployeeEntity employeeEntity);

    @Mapping(
            target = "username",
            expression = "java(normalizeString(employeeDTO.getusername()))"
    )
    @Mapping(
            target = "email",
            expression = "java(normalizeString(employeeDTO.getEmail()))"
    )
    @Mapping(
            target = "firstName",
            expression = "java(normalizeString(employeeDTO.getFirstName()))"
    )
    @Mapping(
            target = "lastName",
            expression = "java(normalizeString(employeeDTO.getLastName()))"
    )
    public abstract EmployeeEntity toEmployeeEntity(EmployeeDTO employeeDTO);

    @Mapping(target = "number", expression = "java(page.getNumber() + 1)")
    public abstract PaginatedEmployeeResponseDTO toPaginatedEmployeeDTO(Page<EmployeeEntity> page);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    public abstract void updateEmployeeFromDTO(EmployeeDTO employeeDTO, @MappingTarget EmployeeEntity employeeEntity);

    protected String normalizeString(String value) {

        if (value == null) {
            return null;
        }

        return value.trim().toLowerCase();

    }
}
