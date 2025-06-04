package com.nortic.pos.ts.platforminitializer.infrastructure.adapter.sql;

import com.nortic.pos.models.entity.StoreEntity;
import com.nortic.pos.ts.platforminitializer.domain.port.StorePort;
import com.nortic.pos.ts.platforminitializer.infrastructure.adapter.sql.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StoreAdapter implements StorePort {

    private final StoreRepository repository;

    @Autowired
    public StoreAdapter(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public StoreEntity save(StoreEntity store) {
        return repository.save(store);
    }

    @Override
    public Optional<StoreEntity> findByTaxId(String taxId) {
        return repository.findByTaxId(taxId);
    }

    @Override
    public boolean existsByTaxId(String id) {
        return repository.existsByTaxId(id);
    }
}
