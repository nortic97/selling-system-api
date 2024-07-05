package com.nortic.posdash.ts.platforminitializer.service;

import com.nortic.posdash.models.entity.*;
import com.nortic.posdash.ts.platforminitializer.repository.*;
import com.nortic.posdash.ts.platforminitializer.utils.Icons;
import com.nortic.posdash.ts.platforminitializer.utils.PasswordUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PlatformInitializerServiceImpl implements IPlatformInitializerService {

    private static final Logger log = LoggerFactory.getLogger(PlatformInitializerServiceImpl.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final SubMenuRepository submenuRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordUtils passwordUtils;

    @Autowired
    public PlatformInitializerServiceImpl(RoleRepository roleRepository, UserRepository userRepository, MenuRepository menuRepository, SubMenuRepository submenuRepository, PermissionRepository permissionRepository, PasswordUtils passwordUtils) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
        this.submenuRepository = submenuRepository;
        this.permissionRepository = permissionRepository;
        this.passwordUtils = passwordUtils;
    }

    @Transactional
    @Override
    public void initializePlatform() {

        // Insert Roles
        RoleEntity adminRole;
        if (!roleRepository.existsByName("Administrator")) {
            adminRole = new RoleEntity();
            adminRole.setId(UUID.fromString("11111111-1111-1111-1111-111111111111"));
            adminRole.setName("Administrator");
            adminRole.setDescription("Administrator role");
            adminRole.setStatus(true);
            roleRepository.save(adminRole);
            roleRepository.flush();
            log.info("Admin role added");
        }

        // Insert users
        if (!userRepository.existsByUserName("posdashAdmin")) {
            adminRole = roleRepository.findByName("Administrator");
            UserEntity adminUser = new UserEntity();
            adminUser.setId(UUID.fromString("11111111-1111-1111-1111-111111111111"));
            adminUser.setFullName("PosDash");
            adminUser.setLastName("Admin");
            adminUser.setUserName("posdashAdmin");
            adminUser.setPassword(passwordUtils.encryptPassword("PosdashAdmin2024*"));
            adminUser.setEmail("admin@posdash.com");
            adminUser.setPhone("1234567890");
            adminUser.setRole(adminRole);
            adminUser.setStatus(true);
            adminUser.setAddress("123 Main St");
            adminUser.setBirthDate(LocalDate.of(1997, 5, 22));
            userRepository.save(adminUser);
            userRepository.flush();
            log.info("Admin user added");
        }

        // Insert menus
        if (!menuRepository.existsByName("Dashboard")) {
            MenuEntity dashboardMenu = new MenuEntity();
            dashboardMenu.setId(UUID.randomUUID());
            dashboardMenu.setName("Dashboard");
            dashboardMenu.setDescription("Dashboard main menu");
            dashboardMenu.setHasSubmenu(false);
            dashboardMenu.setIcon(Icons.DASHBOARD);
            dashboardMenu.setPosition(1);
            dashboardMenu.setStatus(true);
            dashboardMenu.setUrl("/home/dashboard");
            menuRepository.save(dashboardMenu);
            menuRepository.flush();
            log.info("Dashboard menu added");
        }

        if (!menuRepository.existsByName("Products")) {
            MenuEntity productsMenu = new MenuEntity();
            productsMenu.setId(UUID.randomUUID());
            productsMenu.setName("Products");
            productsMenu.setDescription("Products Menu");
            productsMenu.setHasSubmenu(true);
            productsMenu.setIcon(Icons.PRODUCTS);
            productsMenu.setPosition(2);
            productsMenu.setStatus(true);
            productsMenu.setUrl("/home/products");
            menuRepository.save(productsMenu);
            menuRepository.flush();
            log.info("Products menu added");
        }

        if (!menuRepository.existsByName("Categories")) {
            MenuEntity categoriesMenu = new MenuEntity();
            categoriesMenu.setId(UUID.randomUUID());
            categoriesMenu.setName("Categories");
            categoriesMenu.setDescription("Categories Menu");
            categoriesMenu.setHasSubmenu(true);
            categoriesMenu.setIcon(Icons.CATEGORIES);
            categoriesMenu.setPosition(3);
            categoriesMenu.setStatus(true);
            categoriesMenu.setUrl("/home/categories");
            menuRepository.save(categoriesMenu);
            menuRepository.flush();
            log.info("Categories menu added");
        }

        if (!menuRepository.existsByName("Sale")) {
            MenuEntity saleMenu = new MenuEntity();
            saleMenu.setId(UUID.randomUUID());
            saleMenu.setName("Sale");
            saleMenu.setDescription("Sale menu");
            saleMenu.setHasSubmenu(true);
            saleMenu.setIcon(Icons.SALE);
            saleMenu.setPosition(4);
            saleMenu.setStatus(true);
            saleMenu.setUrl("/home/sale");
            menuRepository.save(saleMenu);
            menuRepository.flush();
            log.info("Sale menu added");
        }

        if (!menuRepository.existsByName("Purchases")) {
            MenuEntity purchasesMenu = new MenuEntity();
            purchasesMenu.setId(UUID.randomUUID());
            purchasesMenu.setName("Purchases");
            purchasesMenu.setDescription("Purchases menu");
            purchasesMenu.setHasSubmenu(true);
            purchasesMenu.setIcon(Icons.PURCHASES);
            purchasesMenu.setPosition(5);
            purchasesMenu.setStatus(true);
            purchasesMenu.setUrl("/home/purchases");
            menuRepository.save(purchasesMenu);
            menuRepository.flush();
            log.info("Purchases menu added");
        }

        if (!menuRepository.existsByName("Returns")) {
            MenuEntity returnsMenu = new MenuEntity();
            returnsMenu.setId(UUID.randomUUID());
            returnsMenu.setName("Returns");
            returnsMenu.setDescription("Returns menu");
            returnsMenu.setHasSubmenu(true);
            returnsMenu.setIcon(Icons.RETURNS);
            returnsMenu.setPosition(6);
            returnsMenu.setStatus(true);
            returnsMenu.setUrl("/home/returns");
            menuRepository.save(returnsMenu);
            menuRepository.flush();
            log.info("Returns menu added");
        }

        if (!menuRepository.existsByName("People")) {
            MenuEntity peopleMenu = new MenuEntity();
            peopleMenu.setId(UUID.randomUUID());
            peopleMenu.setName("People");
            peopleMenu.setDescription("People menu");
            peopleMenu.setHasSubmenu(true);
            peopleMenu.setIcon(Icons.PEOPLE);
            peopleMenu.setPosition(7);
            peopleMenu.setStatus(true);
            peopleMenu.setUrl("/home/people");
            menuRepository.save(peopleMenu);
            menuRepository.flush();
            log.info("People menu added");
        }

        if (!menuRepository.existsByName("Reports")) {
            MenuEntity reportsMenu = new MenuEntity();
            reportsMenu.setId(UUID.randomUUID());
            reportsMenu.setName("Reports");
            reportsMenu.setDescription("Reports menu");
            reportsMenu.setHasSubmenu(false);
            reportsMenu.setIcon(Icons.REPORTS);
            reportsMenu.setPosition(8);
            reportsMenu.setStatus(true);
            reportsMenu.setUrl("/home/reports");
            menuRepository.save(reportsMenu);
            menuRepository.flush();
            log.info("Reports menu added");
        }

        // Insert submenus
        MenuEntity menu;
        if (!submenuRepository.existsByName("List Product")) {
            menu = menuRepository.findByName("Products");
            SubMenuEntity productsListSubmenu = new SubMenuEntity();
            productsListSubmenu.setId(UUID.randomUUID());
            productsListSubmenu.setName("List Product");
            productsListSubmenu.setPosition(1);
            productsListSubmenu.setDescription("List Product submenu");
            productsListSubmenu.setStatus(true);
            productsListSubmenu.setUrl("/home/products/listProducts");
            productsListSubmenu.setMenu(menu);
            submenuRepository.save(productsListSubmenu);
            submenuRepository.flush();
            log.info("List Product submenu added");
        }

        if (!submenuRepository.existsByName("Add Product")) {
            menu = menuRepository.findByName("Products");
            SubMenuEntity addProductsSubmenu = new SubMenuEntity();
            addProductsSubmenu.setId(UUID.randomUUID());
            addProductsSubmenu.setName("Add Product");
            addProductsSubmenu.setPosition(2);
            addProductsSubmenu.setDescription("Add Product submenu");
            addProductsSubmenu.setStatus(true);
            addProductsSubmenu.setUrl("/home/products/addProduct");
            addProductsSubmenu.setMenu(menu);
            submenuRepository.save(addProductsSubmenu);
            submenuRepository.flush();
            log.info("Add Product submenu added");
        }

        if (!submenuRepository.existsByName("Users")) {
            menu = menuRepository.findByName("People");
            SubMenuEntity userListSubmenu = new SubMenuEntity();
            userListSubmenu.setId(UUID.randomUUID());
            userListSubmenu.setName("Users");
            userListSubmenu.setPosition(3);
            userListSubmenu.setDescription("User list submenu");
            userListSubmenu.setStatus(true);
            userListSubmenu.setUrl("/home/people/users");
            userListSubmenu.setMenu(menu);
            submenuRepository.save(userListSubmenu);
            submenuRepository.flush();
            log.info("Users submenu added");
        }

        // Add all permissions to the admin role
        List<PermissionsEntity> allPermissions = permissionRepository.findAll();
        adminRole = roleRepository.findByName("Administrator");
        adminRole.setPermissions(allPermissions);
        roleRepository.save(adminRole);
        roleRepository.flush();
        log.info("Permissions added");

        // Add all menus to the admin role
        List<MenuEntity> allMenus = menuRepository.findAll();
        adminRole = roleRepository.findByName("Administrator");
        adminRole.setMenus(allMenus);
        roleRepository.save(adminRole);
        roleRepository.flush();
        log.info("Menus added");

    }
}
