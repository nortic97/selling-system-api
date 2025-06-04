package com.nortic.pos.ts.platforminitializer.domain.port;

import com.nortic.pos.models.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserPort {
    UserEntity save(UserEntity user);
    Optional<UserEntity> findById(UUID id);
    boolean existsById(UUID id);
}
