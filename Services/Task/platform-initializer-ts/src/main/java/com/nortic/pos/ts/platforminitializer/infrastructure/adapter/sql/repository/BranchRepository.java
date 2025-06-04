package com.nortic.pos.ts.platforminitializer.infrastructure.adapter.sql.repository;

import com.nortic.pos.models.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, UUID> {
    Optional<BranchEntity> findById(UUID id);
    boolean existsById(UUID id);
    boolean existsByName(String name);
}
