package com.nortic.pos.ts.platforminitializer.infrastructure.adapter.mongo;

import com.nortic.pos.models.document.CatalogDocument;
import com.nortic.pos.ts.platforminitializer.domain.port.CatalogPort;
import com.nortic.pos.ts.platforminitializer.infrastructure.adapter.mongo.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CatalogAdapter implements CatalogPort {

    private final CatalogRepository repository;

    @Autowired
    public CatalogAdapter(CatalogRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<CatalogDocument> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public CatalogDocument save(CatalogDocument catalog) {
        return repository.save(catalog);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<CatalogDocument> saveAll(List<CatalogDocument> catalogs) {
        return repository.saveAll(catalogs);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }
}
