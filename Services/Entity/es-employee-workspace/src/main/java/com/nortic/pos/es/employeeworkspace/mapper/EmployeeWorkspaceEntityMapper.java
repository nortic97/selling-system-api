package com.nortic.pos.es.employeeworkspace.mapper;

import com.nortic.pos.es.employeeworkspace.model.dto.CreateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employeeworkspace.model.dto.EmployeeWorkspaceDTO;
import com.nortic.pos.es.employeeworkspace.model.dto.PaginatedEmployeeWorkspaceResponseDTO;
import com.nortic.pos.es.employeeworkspace.model.dto.UpdateEmployeeWorkspaceRequestDTO;
import com.nortic.pos.es.employeeworkspace.model.entity.*;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@RequiredArgsConstructor
public abstract class EmployeeWorkspaceEntityMapper {

    @Mapping(target = "storeId", source = "storeId.id")
    @Mapping(target = "employeeId", source = "employeeId.id")
    @Mapping(target = "branchId", source = "branchId.id")
    @Mapping(target = "roleId", source = "roleId.id")
    public abstract EmployeeWorkspaceDTO toEmployeeWorkspaceDTO(EmployeeWorkspaceEntity employeeWorkspaceEntity);

    @Mapping(target = "storeId", source = "storeId", qualifiedByName = "uuidToStoreEntity")
    @Mapping(target = "employeeId", source = "employeeId", qualifiedByName = "uuidToEmployeeEntity")
    @Mapping(target = "branchId", source = "branchId", qualifiedByName = "uuidToBranchEntity")
    @Mapping(target = "roleId", source = "roleId", qualifiedByName = "uuidToRoleEntity")
    @Mapping(target = "isActive", constant = "true")
    public abstract EmployeeWorkspaceEntity toEmployeeWorkspaceEntity(CreateEmployeeWorkspaceRequestDTO requestDTO);

    @Mapping(target = "branchId", source = "branchId", qualifiedByName = "uuidToBranchEntity")
    @Mapping(target = "roleId", source = "roleId", qualifiedByName = "uuidToRoleEntity")
    public abstract EmployeeWorkspaceEntity toEmployeeWorkspaceEntity(UpdateEmployeeWorkspaceRequestDTO requestDTO);

    @Mapping(target = "lastAccessAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "storeId", ignore = true)
    @Mapping(target = "employeeId", ignore = true)
    @Mapping(target = "branchId", source = "branchId", qualifiedByName = "uuidToBranchEntity")
    @Mapping(target = "roleId", source = "roleId", qualifiedByName = "uuidToRoleEntity")
    public abstract void updateEmployeeWorkspaceFromDTO(UpdateEmployeeWorkspaceRequestDTO requestDTO, @MappingTarget EmployeeWorkspaceEntity employeeWorkspaceEntity);

    @Mapping(target = "number", expression = "java(page.getNumber() + 1)")
    public abstract PaginatedEmployeeWorkspaceResponseDTO toPaginatedEmployeeWorkspaceDTO(Page<EmployeeWorkspaceEntity> page);

    @Named("uuidToStoreEntity")
    protected StoreEntity uuidToStoreEntity(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        StoreEntity store = new StoreEntity();
        store.setId(uuid);
        return store;
    }

    @Named("uuidToEmployeeEntity")
    protected EmployeeEntity uuidToEmployeeEntity(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(uuid);
        return employee;
    }

    @Named("uuidToBranchEntity")
    protected BranchEntity uuidToBranchEntity(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        BranchEntity branch = new BranchEntity();
        branch.setId(uuid);
        return branch;
    }

    @Named("uuidToRoleEntity")
    protected RoleEntity uuidToRoleEntity(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        RoleEntity role = new RoleEntity();
        role.setId(uuid);
        return role;
    }
}
