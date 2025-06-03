package com.nortic.posdash.ts.platforminitializer.repository.mongo;

import com.nortic.pos.models.document.PlatformNavigationMenuDocument;

import java.util.Optional;

public interface IRoleNavigationMenuRepository {
    Optional<PlatformNavigationMenuDocument> findByRoleId(String roleId);
    void upsertByRoleId(String roleId, PlatformNavigationMenuDocument entity);
}
