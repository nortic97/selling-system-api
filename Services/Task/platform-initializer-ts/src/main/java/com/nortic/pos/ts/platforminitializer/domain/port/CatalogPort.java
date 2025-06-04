package com.nortic.pos.ts.platforminitializer.domain.port;

import com.nortic.pos.models.document.CatalogDocument;

import java.util.List;
import java.util.Optional;

public interface CatalogPort {
    Optional<CatalogDocument> findById(String id);
    CatalogDocument save(CatalogDocument catalog);
    long count();
    List<CatalogDocument> saveAll(List<CatalogDocument> catalogs);
    boolean existsById(String id);
}
