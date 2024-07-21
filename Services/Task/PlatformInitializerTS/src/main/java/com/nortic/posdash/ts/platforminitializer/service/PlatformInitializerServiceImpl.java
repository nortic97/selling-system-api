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
        insertRoleIfNotExist("Administrator");
        insertRoleIfNotExist("User");
        insertUserIfNotExist(LocalDate.of(1997, 5, 22));
        insertMenuIfNotExist("Dashboard", "Dashboard main menu", false, Icons.DASHBOARD, 1, "/home/dashboard");
        insertMenuIfNotExist("Products", "Products Menu", true, Icons.PRODUCTS, 2, "/home/products");
        insertMenuIfNotExist("Categories", "Categories Menu", true, Icons.CATEGORIES, 3, "/home/categories");
        insertMenuIfNotExist("Sale", "Sale menu", true, Icons.SALE, 4, "/home/sale");
        insertMenuIfNotExist("Purchases", "Purchases menu", true, Icons.PURCHASES, 5, "/home/purchases");
        insertMenuIfNotExist("Returns", "Returns menu", true, Icons.RETURNS, 6, "/home/returns");
        insertMenuIfNotExist("People", "People menu", true, Icons.PEOPLE, 7, "/home/people");
        insertMenuIfNotExist("Reports", "Reports menu", false, Icons.REPORTS, 8, "/home/reports");
        insertMenuIfNotExist("Settings", "Settings menu", true, Icons.SETTINGS, 9, "/home/settings");

        insertSubMenuIfNotExist("Products", "List Product", 1, "/home/products/listProducts", "List Product submenu");
        insertSubMenuIfNotExist("Products", "Add Product", 2, "/home/products/addProduct", "Add Product submenu");
        insertSubMenuIfNotExist("Products", "Trash Product", 3, "/home/products/trashProducts", "Trash Product submenu");

        insertSubMenuIfNotExist("Categories", "List Categories", 1, "/home/categories/listCategories", "List Categories submenu");
        insertSubMenuIfNotExist("Categories", "Add Categories", 2, "/home/categories/addCategories", "Add Categories submenu");
        insertSubMenuIfNotExist("Categories", "Trash Categories", 3, "/home/categories/trashCategories", "Trash Categories submenu");

        insertSubMenuIfNotExist("Sale", "List Sale", 1, "/home/sale/listSale", "List Sale submenu");
        insertSubMenuIfNotExist("Sale", "Add Sale", 2, "/home/sale/addSale", "Add Sale submenu");
        insertSubMenuIfNotExist("Sale", "Trash Sale", 3, "/home/sale/trashSale", "Trash Sale submenu");

        insertSubMenuIfNotExist("Purchases", "List Purchases", 1, "/home/purchases/listPurchases", "List Purchases submenu");
        insertSubMenuIfNotExist("Purchases", "Add Purchases", 2, "/home/purchases/addPurchases", "Add Purchases submenu");
        insertSubMenuIfNotExist("Purchases", "Trash Purchases", 3, "/home/purchases/trashPurchases", "Trash Purchases submenu");

        insertSubMenuIfNotExist("Returns", "List Returns", 1, "/home/returns/listReturns", "List Returns submenu");
        insertSubMenuIfNotExist("Returns", "Add Returns", 2, "/home/returns/addReturns", "Add Returns submenu");
        insertSubMenuIfNotExist("Returns", "Trash Returns", 3, "/home/returns/trashReturns", "Trash Returns submenu");

        insertSubMenuIfNotExist("People", "Users", 3, "/home/people/users", "User list submenu");

        insertSubMenuIfNotExist("Settings", "Parameters", 1, "/home/settings/parameters", "Parameters submenu");
        insertSubMenuIfNotExist("Settings", "Roles", 2, "/home/settings/roles", "Roles submenu");

        addPermissionsToRole();
        addMenusToRole();
    }

    private void insertRoleIfNotExist(String roleName) {
        if (!roleRepository.existsByName(roleName)) {
            RoleEntity role = new RoleEntity();
            role.setId(UUID.fromString("11111111-1111-1111-1111-111111111111"));
            role.setName(roleName);
            role.setDescription("Platform role created");
            role.setStatus(true);
            roleRepository.save(role);
            roleRepository.flush();
            log.info("{} role added", roleName);
        }
    }

    private void insertUserIfNotExist(LocalDate birthDate) {
        if (!userRepository.existsByUserName("posdashadmin")) {
            RoleEntity role = roleRepository.findByName("Administrator");
            UserEntity user = new UserEntity();
            user.setId(UUID.fromString("11111111-1111-1111-1111-111111111111"));
            user.setFullName("PosDash");
            user.setLastName("Admin");
            user.setUserName("posdashadmin");
            user.setPassword(passwordUtils.encryptPassword("PosdashAdmin2024*"));
            user.setEmail("admin@posdash.com");
            user.setPhone("1234567890");
            user.setRole(role);
            user.setStatus(true);
            user.setAddress("123 Main St");
            user.setBirthDate(birthDate);
            userRepository.save(user);
            userRepository.flush();
            log.info("{} user added", "posdashadmin");
        }
    }

    private void insertMenuIfNotExist(String name, String description, boolean hasSubmenu, String icon, int position, String url) {
        if (!menuRepository.existsByName(name)) {
            MenuEntity menu = new MenuEntity();
            menu.setId(UUID.randomUUID());
            menu.setName(name);
            menu.setDescription(description);
            menu.setHasSubmenu(hasSubmenu);
            menu.setIcon(icon);
            menu.setPosition(position);
            menu.setStatus(true);
            menu.setUrl(url);
            menuRepository.save(menu);
            menuRepository.flush();
            log.info("{} menu added", name);
        }
    }

    private void insertSubMenuIfNotExist(String menuName, String subMenuName, int position, String url, String description) {
        if (!submenuRepository.existsByName(subMenuName)) {
            MenuEntity menu = menuRepository.findByName(menuName);
            SubMenuEntity subMenu = new SubMenuEntity();
            subMenu.setId(UUID.randomUUID());
            subMenu.setName(subMenuName);
            subMenu.setPosition(position);
            subMenu.setDescription(description);
            subMenu.setStatus(true);
            subMenu.setUrl(url);
            subMenu.setMenu(menu);
            submenuRepository.save(subMenu);
            submenuRepository.flush();
            log.info("{} submenu added", subMenuName);
        }
    }

    private void addPermissionsToRole() {
        List<PermissionsEntity> allPermissions = permissionRepository.findAll();
        RoleEntity role = roleRepository.findByName("Administrator");
        role.setPermissions(allPermissions);
        roleRepository.save(role);
        roleRepository.flush();
        log.info("Permissions added to " + "Administrator");
    }

    private void addMenusToRole() {
        List<MenuEntity> allMenus = menuRepository.findAll();
        RoleEntity role = roleRepository.findByName("Administrator");
        role.setMenus(allMenus);
        roleRepository.save(role);
        roleRepository.flush();
        log.info("Menus added to " + "Administrator");
    }
}
