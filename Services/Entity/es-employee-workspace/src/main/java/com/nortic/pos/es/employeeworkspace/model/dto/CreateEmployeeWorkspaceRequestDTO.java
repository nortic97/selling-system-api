package com.nortic.pos.es.employeeworkspace.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeWorkspaceRequestDTO {

    private UUID storeId;

    private UUID employeeId;

    private UUID branchId;

    private UUID roleId;

    @Builder.Default
    private Boolean isDefault = false;
}
