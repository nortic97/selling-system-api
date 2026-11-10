package com.nortic.pos.es.employeeworkspace.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeWorkspaceRequestDTO {

    @NotBlank(message = "branchId is required")
    private UUID branchId;

    @NotBlank(message = "roleId is required")
    private UUID roleId;

    @NotNull(message = "isDefault is required")
    private Boolean isDefault;
}
