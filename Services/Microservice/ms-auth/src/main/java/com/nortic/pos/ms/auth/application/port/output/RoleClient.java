package com.nortic.pos.ms.auth.application.port.output;

import com.nortic.pos.ms.auth.domain.model.Permission;

import java.util.List;
import java.util.UUID;

public interface RoleClient {
    List<Permission> getRolePermission(UUID roleId);
}
