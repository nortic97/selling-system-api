package com.nortic.pos.ts.platforminitializer.infrastructure.adapter.sql;

import com.nortic.pos.models.entity.RoleEntity;
import com.nortic.pos.ts.platforminitializer.domain.port.RolePort;
import com.nortic.pos.ts.platforminitializer.infrastructure.adapter.sql.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RoleAdapter implements RolePort {

    private final RoleRepository repository;

    @Autowired
    public RoleAdapter(RoleRepository repository) {
        this.repository = repository;
    }


    @Override
    public RoleEntity save(RoleEntity role) {
        return repository.save(role);
    }

    @Override
    public Optional<RoleEntity> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}
