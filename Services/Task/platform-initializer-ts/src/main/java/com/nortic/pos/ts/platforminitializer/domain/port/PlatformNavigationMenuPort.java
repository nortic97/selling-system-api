package com.nortic.pos.ts.platforminitializer.domain.port;

import com.nortic.pos.models.document.PlatformNavigationMenuDocument;

import java.util.Optional;

public interface PlatformNavigationMenuPort {
    Optional<PlatformNavigationMenuDocument> findById(String id);
    PlatformNavigationMenuDocument save(PlatformNavigationMenuDocument platformNavigationMenu);
    boolean existsById(String id);
}
