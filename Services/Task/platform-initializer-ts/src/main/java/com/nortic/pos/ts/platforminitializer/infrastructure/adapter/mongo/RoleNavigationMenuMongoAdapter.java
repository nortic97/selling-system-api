package com.nortic.pos.ts.platforminitializer.infrastructure.adapter.mongo;

import com.nortic.pos.models.document.RoleNavigationMenuDocument;
import com.nortic.pos.ts.platforminitializer.domain.port.RoleNavigationMenuPort;
import com.nortic.pos.ts.platforminitializer.infrastructure.adapter.mongo.repository.RoleNavigationMenuMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleNavigationMenuMongoAdapter implements RoleNavigationMenuPort {

    private final RoleNavigationMenuMongoRepository repository;

    @Autowired
    RoleNavigationMenuMongoAdapter(RoleNavigationMenuMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoleNavigationMenuDocument save(RoleNavigationMenuDocument roleNavigationMenuDocument) {
        return repository.save(roleNavigationMenuDocument);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }
}
