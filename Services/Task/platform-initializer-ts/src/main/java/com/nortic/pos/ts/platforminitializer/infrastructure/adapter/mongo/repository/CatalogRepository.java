package com.nortic.pos.ts.platforminitializer.infrastructure.adapter.mongo.repository;

import com.nortic.pos.models.document.CatalogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CatalogRepository extends MongoRepository<CatalogDocument, String> {
}
