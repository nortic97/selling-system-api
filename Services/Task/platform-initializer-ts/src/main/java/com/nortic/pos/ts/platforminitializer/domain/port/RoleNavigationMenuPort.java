package com.nortic.pos.ts.platforminitializer.domain.port;

import com.nortic.pos.models.document.RoleNavigationMenuDocument;

public interface RoleNavigationMenuPort {
    RoleNavigationMenuDocument save(RoleNavigationMenuDocument roleNavigationMenuDocument);
    boolean existsById(String id);
}
