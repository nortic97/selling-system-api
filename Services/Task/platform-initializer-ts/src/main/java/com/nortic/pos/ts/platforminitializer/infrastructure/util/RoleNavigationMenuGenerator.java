package com.nortic.pos.ts.platforminitializer.infrastructure.util;

import com.nortic.pos.models.document.NavigationMenuDocument;
import com.nortic.pos.models.document.RoleNavigationMenuDocument;

import java.util.List;

public class RoleNavigationMenuGenerator {
    public static RoleNavigationMenuDocument generateRoleNavigationMenu(List<NavigationMenuDocument> menus) {
        return RoleNavigationMenuDocument.builder()
                .id(Constants.GLOBAL_UUID.toString())
                .navigationMenus(menus)
                .build();
    }
}
