package com.nortic.posdash.ts.platforminitializer.utils;

import com.nortic.posdash.models.entity.RoleNavigationMenuEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MenuUtils {

    public static List<RoleNavigationMenuEntity.NavigationMenu> mergeAndCleanMenus(
            List<RoleNavigationMenuEntity.NavigationMenu> existingMenus,
            List<RoleNavigationMenuEntity.NavigationMenu> configuredMenus) {

        // Eliminar menús que ya no existen en la configuración base
        List<RoleNavigationMenuEntity.NavigationMenu> filteredMenus = existingMenus.stream()
                .filter(menu -> configuredMenus.stream().anyMatch(configMenu -> configMenu.getName().equals(menu.getName())))
                .collect(Collectors.toList());

        // Agregar o actualizar menús y submenús
        for (RoleNavigationMenuEntity.NavigationMenu configMenu : configuredMenus) {
            Optional<RoleNavigationMenuEntity.NavigationMenu> existingMenuOpt =
                    filteredMenus.stream().filter(menu -> menu.getName().equals(configMenu.getName())).findFirst();

            if (existingMenuOpt.isPresent()) {
                RoleNavigationMenuEntity.NavigationMenu existingMenu = existingMenuOpt.get();
                existingMenu.setSubmenus(mergeAndCleanSubMenus(existingMenu.getSubmenus(), configMenu.getSubmenus()));
            } else {
                filteredMenus.add(configMenu);
            }
        }

        return filteredMenus;
    }

    public static List<RoleNavigationMenuEntity.SubMenu> mergeAndCleanSubMenus(
            List<RoleNavigationMenuEntity.SubMenu> existingSubMenus,
            List<RoleNavigationMenuEntity.SubMenu> configuredSubMenus) {

        if (configuredSubMenus == null || configuredSubMenus.isEmpty()) {
            return existingSubMenus;
        }

        // Eliminar submenús que ya no están en la configuración base
        List<RoleNavigationMenuEntity.SubMenu> filteredSubMenus = existingSubMenus.stream()
                .filter(sub -> configuredSubMenus.stream().anyMatch(configSub -> configSub.getName().equals(sub.getName())))
                .collect(Collectors.toList());

        // Agregar nuevos submenús si no existen
        for (RoleNavigationMenuEntity.SubMenu configSubMenu : configuredSubMenus) {
            boolean exists = filteredSubMenus.stream().anyMatch(sub -> sub.getName().equals(configSubMenu.getName()));

            if (!exists) {
                filteredSubMenus.add(configSubMenu);
            }
        }

        return filteredSubMenus;
    }
}
