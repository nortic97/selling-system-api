package com.nortic.pos.es.store.repository;

import com.nortic.pos.es.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {

    @Query("SELECT s FROM StoreEntity s WHERE s.taxId = :taxId AND s.deletedAt IS NULL")
    Optional<StoreEntity> findByTaxId(@Param("taxId") String taxId);

    @Query("SELECT s FROM StoreEntity s WHERE s.id = :id AND s.deletedAt IS NULL")
    Optional<StoreEntity> findByIdAndDeletedAtIsNull(@Param("id") UUID id);

    @Query("SELECT s FROM StoreEntity s WHERE s.deletedAt IS NULL")
    java.util.List<StoreEntity> findAllActive();
}
