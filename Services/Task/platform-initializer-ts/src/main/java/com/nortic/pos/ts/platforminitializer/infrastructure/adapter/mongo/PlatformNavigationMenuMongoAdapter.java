package com.nortic.pos.ts.platforminitializer.infrastructure.adapter.mongo;

import com.nortic.pos.models.document.PlatformNavigationMenuDocument;
import com.nortic.pos.ts.platforminitializer.domain.port.PlatformNavigationMenuPort;
import com.nortic.pos.ts.platforminitializer.infrastructure.adapter.mongo.repository.PlatformNavigationMenuMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlatformNavigationMenuMongoAdapter implements PlatformNavigationMenuPort {

    private final PlatformNavigationMenuMongoRepository repository;

    @Autowired
    public PlatformNavigationMenuMongoAdapter(PlatformNavigationMenuMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public PlatformNavigationMenuDocument save(PlatformNavigationMenuDocument platformNavigationMenu) {
        return repository.save(platformNavigationMenu);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }
}
