package com.nortic.pos.ts.platforminitializer.domain.port;


import com.nortic.pos.models.entity.BranchEntity;

import java.util.Optional;
import java.util.UUID;

public interface BranchPort {
    BranchEntity save(BranchEntity store);
    Optional<BranchEntity> findById(UUID id);
    boolean existsByName(String name);
    boolean existsById(UUID id);
}
