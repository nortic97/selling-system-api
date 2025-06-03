package com.nortic.posdash.ts.platforminitializer.service.impl;

import com.nortic.pos.models.entity.RoleEntity;
import com.nortic.posdash.ts.platforminitializer.repository.mongo.RoleNavigationMenuRepositoryImpl;
import com.nortic.posdash.ts.platforminitializer.service.contract.IRoleNavigationMenuSvc;
import com.nortic.posdash.ts.platforminitializer.utils.Constants;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.nortic.posdash.ts.platforminitializer.utils.GenerateMenu.*;
import static com.nortic.posdash.ts.platforminitializer.utils.MenuUtils.*;

@Service
public class RoleNavigationMenuSvcImpl implements IRoleNavigationMenuSvc {

    private static final Logger log = LoggerFactory.getLogger(RoleNavigationMenuSvcImpl.class);

    private final RoleSvcImpl roleSvc;
    private final RoleNavigationMenuRepositoryImpl roleNavigationMenuRepository;

    @Autowired
    public RoleNavigationMenuSvcImpl(RoleSvcImpl roleSvc, RoleNavigationMenuRepositoryImpl roleNavigationMenuRepository) {
        this.roleSvc = roleSvc;
        this.roleNavigationMenuRepository = roleNavigationMenuRepository;
    }

    @Override
    @Transactional
    public void insertRoleNavigationMenuIfNotExist() {

        log.info("Inserting role navigation menu.");

        // Obtener el rol ADMIN
        RoleEntity roleEntity = roleSvc.getRoleByRoleName(Constants.ROLE_ADMIN);
        if (roleEntity == null) {
            throw new RuntimeException("Role not found");
        }

        // Generar el menú de navegación configurado
        List<RoleNavigationMenuEntity.NavigationMenu> configuredMenus = generateNavigationMenu();

        // Buscar el menú de navegación existente para este rol
        Optional<RoleNavigationMenuEntity> existingRoleMenuOpt = roleNavigationMenuRepository.findByRoleId(roleEntity.getId().toString());

        if (existingRoleMenuOpt.isPresent()) {
            // Si el menú ya existe, actualizarlo
            RoleNavigationMenuEntity existingRoleMenu = existingRoleMenuOpt.get();
            List<RoleNavigationMenuEntity.NavigationMenu> existingMenus = existingRoleMenu.getNavigationMenus();
            List<RoleNavigationMenuEntity.NavigationMenu> updatedMenus = mergeAndCleanMenus(existingMenus, configuredMenus);

            if (!existingMenus.equals(updatedMenus)) {
                existingRoleMenu.setNavigationMenus(updatedMenus);
                roleNavigationMenuRepository.upsertByRoleId(roleEntity.getId().toString(), existingRoleMenu);
                log.info("Role navigation menu updated.");
            } else {
                log.info("No changes detected in the role navigation menu.");
            }
        } else {
            // Si el menú no existe, crear uno nuevo
            RoleNavigationMenuEntity newRoleMenuEntity = new RoleNavigationMenuEntity();
            newRoleMenuEntity.setRoleId(roleEntity.getId().toString());
            newRoleMenuEntity.setNavigationMenus(configuredMenus);

            roleNavigationMenuRepository.upsertByRoleId(roleEntity.getId().toString(), newRoleMenuEntity);
            log.info("New role navigation menu created.");
        }

        log.info("Role navigation menu updated or created.");


    }
}
