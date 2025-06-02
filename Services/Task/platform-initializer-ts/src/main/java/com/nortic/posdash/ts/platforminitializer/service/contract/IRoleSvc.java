package com.nortic.posdash.ts.platforminitializer.service.contract;

import com.nortic.posdash.models.entity.RoleEntity;

public interface IRoleSvc {
    void insertRoleIfNotExist(String roleName);
    RoleEntity getRoleByRoleName(String roleName);
}
