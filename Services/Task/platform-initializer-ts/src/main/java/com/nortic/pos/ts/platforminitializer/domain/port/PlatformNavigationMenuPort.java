package com.nortic.pos.ts.platforminitializer.domain.port;

import com.nortic.pos.models.document.PlatformNavigationMenuDocument;

public interface PlatformNavigationMenuPort {
    PlatformNavigationMenuDocument save(PlatformNavigationMenuDocument platformNavigationMenu);
    boolean existsById(String id);
}
