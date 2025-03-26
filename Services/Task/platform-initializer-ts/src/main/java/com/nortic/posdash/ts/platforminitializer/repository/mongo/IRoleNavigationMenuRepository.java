package com.nortic.posdash.ts.platforminitializer.repository.mongo;

import com.nortic.posdash.models.entity.RoleNavigationMenuEntity;

import java.util.Optional;

public interface IRoleNavigationMenuRepository {
    Optional<RoleNavigationMenuEntity> findByRoleId(String roleId);
    void upsertByRoleId(String roleId, RoleNavigationMenuEntity entity);
}
