package com.nortic.pos.ts.platforminitializer.infrastructure.adapter.mongo.repository;

import com.nortic.pos.models.document.PlatformNavigationMenuDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformNavigationMenuMongoRepository extends MongoRepository<PlatformNavigationMenuDocument, String> {
}
