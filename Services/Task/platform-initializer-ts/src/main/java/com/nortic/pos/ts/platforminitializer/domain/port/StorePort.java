package com.nortic.pos.ts.platforminitializer.domain.port;

import com.nortic.pos.models.entity.StoreEntity;


public interface StorePort {
    StoreEntity save(StoreEntity store);
    boolean existsByTaxId(String id);
}
