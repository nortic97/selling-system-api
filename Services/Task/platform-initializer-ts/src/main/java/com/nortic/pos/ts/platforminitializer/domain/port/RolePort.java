package com.nortic.pos.ts.platforminitializer.domain.port;

import com.nortic.pos.models.entity.RoleEntity;

import java.util.Optional;
import java.util.UUID;

public interface RolePort {
    RoleEntity save(RoleEntity role);
    Optional<RoleEntity> findById(UUID id);
    boolean existsById(UUID id);
}
