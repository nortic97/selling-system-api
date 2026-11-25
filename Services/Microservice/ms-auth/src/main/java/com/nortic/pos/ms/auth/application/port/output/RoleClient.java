package com.nortic.pos.ms.auth.application.port.output;

import com.nortic.pos.ms.auth.domain.model.PermissionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface RoleClient {
    List<PermissionResponseDTO> getRolePermission(UUID roleId);
}
