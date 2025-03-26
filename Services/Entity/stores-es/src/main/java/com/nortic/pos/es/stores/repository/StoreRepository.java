package com.nortic.pos.es.stores.repository;

import com.nortic.pos.models.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {

}
