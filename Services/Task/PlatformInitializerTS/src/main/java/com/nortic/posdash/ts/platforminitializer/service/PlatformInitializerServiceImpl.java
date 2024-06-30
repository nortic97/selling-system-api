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
public class PlatformInitializerServiceImpl implements IPlatformInitializerService{

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

        if (!menuRepository.existsByName("People")) {
            MenuEntity dashboardMenu = new MenuEntity();
            dashboardMenu.setId(UUID.randomUUID());
            dashboardMenu.setName("People");
            dashboardMenu.setDescription("Administrator Users");
            dashboardMenu.setHasSubmenu(false);
            dashboardMenu.setIcon(Icons.PEOPLE);
            dashboardMenu.setPosition(7);
            dashboardMenu.setStatus(true);
            dashboardMenu.setUrl("/home/people");
            menuRepository.save(dashboardMenu);
            menuRepository.flush();
            log.info("People menu added");
        }

        // Insert submenus
        if (!submenuRepository.existsByName("Users")) {
            MenuEntity userMenu = menuRepository.findByName("People");
            SubMenuEntity userListSubmenu = new SubMenuEntity();
            userListSubmenu.setId(UUID.randomUUID());
            userListSubmenu.setName("Users");
            userListSubmenu.setPosition(2);
            userListSubmenu.setDescription("User list submenu");
            userListSubmenu.setStatus(true);
            userListSubmenu.setUrl("/home/people/users");
            userListSubmenu.setMenu(userMenu);
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
