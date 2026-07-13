package com.nortic.pos.es.store.repository;

import com.nortic.pos.es.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {

    boolean existsByTaxId(String taxId);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    boolean existsByOwnerIdId(UUID ownerId);
}
