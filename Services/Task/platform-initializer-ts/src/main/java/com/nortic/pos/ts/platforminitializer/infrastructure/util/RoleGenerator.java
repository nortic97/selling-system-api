package com.nortic.pos.ts.platforminitializer.infrastructure.util;

import com.nortic.pos.models.entity.RoleEntity;
import com.nortic.pos.models.entity.StoreEntity;

public class RoleGenerator {
    public static RoleEntity generateInitialRole(StoreEntity store) {
        return RoleEntity.builder()
                .id(Constants.GLOBAL_UUID)
                .store(store)
                .name("ADMIN")
                .description("Platform ADMIN Role")
                .build();
    }
}
