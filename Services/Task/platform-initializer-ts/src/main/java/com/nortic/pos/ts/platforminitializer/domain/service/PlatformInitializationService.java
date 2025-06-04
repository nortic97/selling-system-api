package com.nortic.pos.ts.platforminitializer.domain.service;

import com.nortic.pos.models.document.PlatformNavigationMenuDocument;
import com.nortic.pos.models.entity.BranchEntity;
import com.nortic.pos.models.entity.StoreEntity;
import com.nortic.pos.ts.platforminitializer.domain.port.BranchPort;
import com.nortic.pos.ts.platforminitializer.domain.port.PlatformNavigationMenuPort;
import com.nortic.pos.ts.platforminitializer.domain.port.RoleNavigationMenuPort;
import com.nortic.pos.ts.platforminitializer.domain.port.StorePort;
import com.nortic.pos.ts.platforminitializer.infrastructure.util.BranchGenerator;
import com.nortic.pos.ts.platforminitializer.infrastructure.util.Constants;
import com.nortic.pos.ts.platforminitializer.infrastructure.util.MenuGenerator;
import com.nortic.pos.ts.platforminitializer.infrastructure.util.StoreGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlatformInitializationService {

    private final StorePort storePort;
    private final BranchPort branchPort;
    private final PlatformNavigationMenuPort platformNavigationMenuPort;
    private final RoleNavigationMenuPort roleNavigationMenuPort;
    private StoreEntity store = null;
    private BranchEntity branch = null;
    // TODO: Roles, Users;

    public PlatformInitializationService(
            StorePort storePort,
            BranchPort branchPort,
            PlatformNavigationMenuPort platformNavigationMenuPort,
            RoleNavigationMenuPort roleNavigationMenuPort
          ) {
        this.storePort = storePort;
        this.branchPort = branchPort;
        this.platformNavigationMenuPort = platformNavigationMenuPort;
        this.roleNavigationMenuPort = roleNavigationMenuPort;
    }

    public void initializePlatformData() {
        log.info("Iniciando la inicialización de datos de la plataforma...");

        initializePlatformNavigationMenus();
        initializeStore();
        initializeBranch();

        log.info("Inicialización de datos de la plataforma completada.");
    }

    private void initializePlatformNavigationMenus() {
        String platformMenuId = Constants.PLATFORM_NAVIGATION_MENU_VERSION;
        if (platformNavigationMenuPort.existsById(platformMenuId)) {
            log.info("Los menús de navegación de la plataforma ya existen. Omitiendo inicialización.");
            return;
        }

        log.info("Inicializando menús de navegación de la plataforma...");

        PlatformNavigationMenuDocument platformNavigationMenuDocument = PlatformNavigationMenuDocument.builder()
                .id(platformMenuId)
                .navigationMenus(MenuGenerator.generateNavigationMenus())
                .build();

        platformNavigationMenuPort.save(platformNavigationMenuDocument);

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

        branch = branchPort.save(BranchGenerator.generateInitialBranch(store));
        branch.changeStatus(true);
        branch = branchPort.save(branch);

        log.info("Inicializando branch...");
    }

}
