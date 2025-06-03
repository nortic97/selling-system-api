package com.nortic.pos.ts.platforminitializer.infrastructure.adapter.sql.repository;

import com.nortic.pos.models.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {
    boolean existsByTaxId(String id);
}
