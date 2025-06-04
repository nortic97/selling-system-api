package com.nortic.pos.ts.platforminitializer.infrastructure.adapter.sql;

import com.nortic.pos.models.entity.BranchEntity;
import com.nortic.pos.ts.platforminitializer.domain.port.BranchPort;
import com.nortic.pos.ts.platforminitializer.infrastructure.adapter.sql.repository.BranchRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class BranchAdapter implements BranchPort {

    private final BranchRepository repository;

    public BranchAdapter(BranchRepository repository) {
        this.repository = repository;
    }

    @Override
    public BranchEntity save(BranchEntity store) {
        return repository.save(store);
    }

    @Override
    public Optional<BranchEntity> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}
