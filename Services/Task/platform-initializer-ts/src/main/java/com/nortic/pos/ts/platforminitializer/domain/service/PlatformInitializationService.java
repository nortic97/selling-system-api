package com.nortic.pos.ts.platforminitializer.domain.service;

import com.nortic.pos.models.document.CatalogDocument;
import com.nortic.pos.models.document.PlatformNavigationMenuDocument;
import com.nortic.pos.models.entity.BranchEntity;
import com.nortic.pos.models.entity.RoleEntity;
import com.nortic.pos.models.entity.StoreEntity;
import com.nortic.pos.models.entity.UserEntity;
import com.nortic.pos.ts.platforminitializer.domain.port.*;
import com.nortic.pos.ts.platforminitializer.infrastructure.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PlatformInitializationService {

    private final PlatformNavigationMenuPort platformNavigationMenuPort;
    private final StorePort storePort;
    private final BranchPort branchPort;
    private final RolePort rolePort;
    private final UserPort userPort;
    private final RoleNavigationMenuPort roleNavigationMenuPort;
    private final CatalogPort catalogPort;

    private PlatformNavigationMenuDocument platformNavigationMenu = null;
    private StoreEntity store = null;
    private BranchEntity branch = null;
    private RoleEntity role = null;
    private UserEntity user = null;

    public PlatformInitializationService(
            PlatformNavigationMenuPort platformNavigationMenuPort,
            StorePort storePort,
            BranchPort branchPort,
            RolePort rolePort,
            UserPort userPort,
            RoleNavigationMenuPort roleNavigationMenuPort,
            CatalogPort catalogPort
          ) {
        this.platformNavigationMenuPort = platformNavigationMenuPort;
        this.storePort = storePort;
        this.branchPort = branchPort;
        this.rolePort = rolePort;
        this.userPort = userPort;
        this.roleNavigationMenuPort = roleNavigationMenuPort;
        this.catalogPort = catalogPort;
    }

    public void initializePlatformData() {
        log.info("Iniciando la inicialización de datos de la plataforma...");

        initializePlatformNavigationMenus();
        initializeStore();
        initializeBranch();
        initializeRole();
        initializeUser();
        initializeRoleNavigationMenus();
        initializeCatalogs();

        log.info("Inicialización de datos de la plataforma completada.");
    }

    private void initializePlatformNavigationMenus() {
        String platformMenuId = Constants.PLATFORM_NAVIGATION_MENU_VERSION;
        if (platformNavigationMenuPort.existsById(platformMenuId)) {
            log.info("Los menús de navegación de la plataforma ya existen. Omitiendo inicialización.");
            platformNavigationMenu = platformNavigationMenuPort.findById(platformMenuId).orElse(null);
            return;
        }

        log.info("Inicializando menús de navegación de la plataforma...");

        platformNavigationMenu = PlatformNavigationMenuDocument.builder()
                .id(platformMenuId)
                .navigationMenus(MenuGenerator.generateNavigationMenus())
                .build();

        platformNavigationMenuPort.save(platformNavigationMenu);

        log.info("Menús de navegación de la plataforma inicializados exitosamente.");
    }

    private void initializeStore() {

        String storeTaxId = "1069759007";

        if (storePort.existsByTaxId(storeTaxId)) {
            log.info("La tienda ya existe. Omitiendo inicialización.");
            store = storePort.findByTaxId(storeTaxId).orElse(null);
            return;
        }

        log.info("Creando tienda inicial...");

        store = storePort.save(StoreGenerator.generateInitialStore());
        store.changeStatus(true);
        store = storePort.save(store);

        log.info("Tienda inicializada exitosamente.");
    }

    private void initializeBranch() {

        if (branchPort.existsById(Constants.GLOBAL_UUID)) {
            log.info("La sucursal ya existe. Omitiendo inicialización.");
            branch = branchPort.findById(Constants.GLOBAL_UUID).orElse(null);
            return;
        }

        log.info("Creando sucursal inicial...");

        branch = branchPort.save(BranchGenerator.generateInitialBranch(store));
        branch.changeStatus(true);
        branch = branchPort.save(branch);

        log.info("Sucursal inicializada exitosamente.");
    }

    private void initializeRole() {

        if (rolePort.existsById(Constants.GLOBAL_UUID)) {
            log.info("El rol ya existe. Omitiendo inicializacion...");
            role = rolePort.findById(Constants.GLOBAL_UUID).orElse(null);
            return;
        }

        log.info("Creando rol inicial...");

        role = rolePort.save(RoleGenerator.generateInitialRole(store));

        log.info("Rol inicializado exitosamente.");

    }

    private void initializeUser() {

        if (userPort.existsById(Constants.GLOBAL_UUID)) {
            log.info("El usuario ya existe. Omitiendo inicializacion...");
            user = userPort.findById(Constants.GLOBAL_UUID).orElse(null);
            return;
        }

        log.info("Creando usuario inicial...");

        user = userPort.save(UserGenerator.generateInitialUser(store,branch,role));
        user.changeStatus(true);
        user = userPort.save(user);

        log.info("Usuario inicializado exitosamente.");
    }

    private void initializeRoleNavigationMenus() {

        if (roleNavigationMenuPort.existsById(Constants.GLOBAL_UUID.toString())){
            log.info("El menu para el rol ya existe. Omitiendo inicializacion...");
            return;
        }

        log.info("Creando menu para rol inicial...");

        roleNavigationMenuPort.save(RoleNavigationMenuGenerator.generateRoleNavigationMenu(platformNavigationMenu.getNavigationMenus()));

        log.info("Menu para el rol incial inicializado exitosamente.");
    }

    private void initializeCatalogs() {

        if (catalogPort.count() > 0) {
            log.info("Los catálogos ya existen. Omitiendo inicialización.");
            return;
        }

        log.info("Inicializando catálogos iniciales (países, monedas)...");
        List<CatalogDocument> catalogs = CatalogsGenerator.generateInitialCatalogs();
        catalogPort.saveAll(catalogs);
        log.info("Catálogos iniciales guardados exitosamente.");
    }

}
