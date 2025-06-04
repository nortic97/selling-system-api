package com.nortic.pos.ts.platforminitializer.infrastructure.adapter.sql;

import com.nortic.pos.models.entity.UserEntity;
import com.nortic.pos.ts.platforminitializer.domain.port.UserPort;
import com.nortic.pos.ts.platforminitializer.infrastructure.adapter.sql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserAdapter implements UserPort {

    private final UserRepository repository;

    @Autowired
    UserAdapter(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    @Override
    public Optional<UserEntity> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}
