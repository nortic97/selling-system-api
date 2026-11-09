package com.nortic.pos.es.employeeworkspace.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWorkspaceDTO {

    private UUID id;

    private UUID storeId;

    private UUID employeeId;

    private UUID branchId;

    private UUID roleId;

    private Boolean isDefault;

    private OffsetDateTime lastAccessAt;

    private Boolean isActive;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    private OffsetDateTime deletedAt;
}
