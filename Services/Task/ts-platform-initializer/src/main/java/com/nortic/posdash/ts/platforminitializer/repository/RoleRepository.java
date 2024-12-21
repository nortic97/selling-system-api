package com.nortic.posdash.ts.platforminitializer.repository;

import com.nortic.posdash.models.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    boolean existsByName(String name);
    RoleEntity findByName(String name);
}
