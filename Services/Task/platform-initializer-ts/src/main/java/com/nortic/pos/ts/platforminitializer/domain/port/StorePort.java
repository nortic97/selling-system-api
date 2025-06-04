package com.nortic.pos.ts.platforminitializer.domain.port;

import com.nortic.pos.models.entity.StoreEntity;

import java.util.Optional;


public interface StorePort {
    StoreEntity save(StoreEntity store);
    Optional<StoreEntity> findByTaxId(String taxId);
    boolean existsByTaxId(String id);
}
