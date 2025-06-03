package com.nortic.pos.ts.platforminitializer.infrastructure.util;

import com.nortic.pos.models.document.NavigationMenuDocument;
import com.nortic.pos.models.document.SubmenuDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MenuGenerator {

    public static List<NavigationMenuDocument> generateNavigationMenus() {
        List<NavigationMenuDocument> navigationMenus = new ArrayList<>();

        // Dashboard Menu
        navigationMenus.add(NavigationMenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.DASHBOARD_MENU)
                .position(1)
                .hasSubmenu(false)
                .url("/home/dashboard")
                .icon(Icons.DASHBOARD)
                .description("Dashboard main menu")
                .isActive(true)
                .build());

        // Products Menu
        List<SubmenuDocument> productsSubmenus = new ArrayList<>();
        productsSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.PRODUCTS_LIST)
                .position(1)
                .description("List Product submenu")
                .url("/home/products/listProducts")
                .isActive(true)
                .build());
        productsSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.PRODUCTS_ADD)
                .position(2)
                .description("Add Product submenu")
                .url("/home/products/addProduct")
                .isActive(true)
                .build());
        productsSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.PRODUCTS_TRASH)
                .position(3)
                .description("Trash Product submenu")
                .url("/home/products/trashProducts")
                .isActive(true)
                .build());
        navigationMenus.add(NavigationMenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.PRODUCTS_MENU)
                .position(2)
                .hasSubmenu(true)
                .url("/home/products")
                .icon(Icons.PRODUCTS)
                .description("Products Menu")
                .isActive(true)
                .submenu(productsSubmenus)
                .build());

        // Categories Menu
        List<SubmenuDocument> categoriesSubmenus = new ArrayList<>();
        categoriesSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.CATEGORIES_LIST)
                .position(1)
                .description("List Categories submenu")
                .url("/home/categories/listCategories")
                .isActive(true)
                .build());
        categoriesSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.CATEGORIES_ADD)
                .position(2)
                .description("Add Categories submenu")
                .url("/home/categories/addCategories")
                .isActive(true)
                .build());
        categoriesSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.CATEGORIES_TRASH)
                .position(3)
                .description("Trash Categories submenu")
                .url("/home/categories/trashCategories")
                .isActive(true)
                .build());
        navigationMenus.add(NavigationMenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.CATEGORIES_MENU)
                .position(3)
                .hasSubmenu(true)
                .url("/home/categories")
                .icon(Icons.CATEGORIES)
                .description("Categories Menu")
                .isActive(true)
                .submenu(categoriesSubmenus)
                .build());

        // Sale Menu
        List<SubmenuDocument> saleSubmenus = new ArrayList<>();
        saleSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.SALE_LIST)
                .position(1)
                .description("List Sale submenu")
                .url("/home/sale/listSale")
                .isActive(true)
                .build());
        saleSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.SALE_ADD)
                .position(2)
                .description("Add Sale submenu")
                .url("/home/sale/addSale")
                .isActive(true)
                .build());
        saleSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.SALE_TRASH)
                .position(3)
                .description("Trash Sale submenu")
                .url("/home/sale/trashSale")
                .isActive(true)
                .build());
        navigationMenus.add(NavigationMenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.SALE_MENU)
                .position(4)
                .hasSubmenu(true)
                .url("/home/sale")
                .icon(Icons.SALE)
                .description("Sale Menu")
                .isActive(true)
                .submenu(saleSubmenus)
                .build());

        // Purchases Menu
        List<SubmenuDocument> purchasesSubmenus = new ArrayList<>();
        purchasesSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.PURCHASES_LIST)
                .position(1)
                .description("List Purchases submenu")
                .url("/home/purchases/listPurchases")
                .isActive(true)
                .build());
        purchasesSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.PURCHASES_ADD)
                .position(2)
                .description("Add Purchases submenu")
                .url("/home/purchases/addPurchases")
                .isActive(true)
                .build());
        purchasesSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.PURCHASES_TRASH)
                .position(3)
                .description("Trash Purchases submenu")
                .url("/home/purchases/trashPurchases")
                .isActive(true)
                .build());
        navigationMenus.add(NavigationMenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.PURCHASES_MENU)
                .position(5)
                .hasSubmenu(true)
                .url("/home/purchases")
                .icon(Icons.PURCHASES)
                .description("Purchases Menu")
                .isActive(true)
                .submenu(purchasesSubmenus)
                .build());

        // Returns Menu
        List<SubmenuDocument> returnsSubmenus = new ArrayList<>();
        returnsSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.RETURNS_LIST)
                .position(1)
                .description("List Returns submenu")
                .url("/home/returns/listReturns")
                .isActive(true)
                .build());
        returnsSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.RETURNS_ADD)
                .position(2)
                .description("Add Returns submenu")
                .url("/home/returns/addReturns")
                .isActive(true)
                .build());
        returnsSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.RETURNS_TRASH)
                .position(3)
                .description("Trash Returns submenu")
                .url("/home/returns/trashReturns")
                .isActive(true)
                .build());
        navigationMenus.add(NavigationMenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.RETURNS_MENU)
                .position(6)
                .hasSubmenu(true)
                .url("/home/returns")
                .icon(Icons.RETURNS)
                .description("Returns Menu")
                .isActive(true)
                .submenu(returnsSubmenus)
                .build());

        // People Menu
        List<SubmenuDocument> peopleSubmenus = new ArrayList<>();
        peopleSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.PEOPLE_USERS)
                .position(1)
                .description("Users submenu")
                .url("/home/people/users")
                .isActive(true)
                .build());
        navigationMenus.add(NavigationMenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.PEOPLE_MENU)
                .position(7)
                .hasSubmenu(true)
                .url("/home/people")
                .icon(Icons.PEOPLE)
                .description("People Menu")
                .isActive(true)
                .submenu(peopleSubmenus)
                .build());

        // Reports Menu
        navigationMenus.add(NavigationMenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.REPORTS_MENU)
                .position(8)
                .hasSubmenu(false)
                .url("/home/reports")
                .icon(Icons.REPORTS)
                .description("Reports Menu")
                .isActive(true)
                .build());

        // Settings Menu
        List<SubmenuDocument> settingsSubmenus = new ArrayList<>();
        settingsSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.SETTINGS_PARAMETERS)
                .position(1)
                .description("Parameters submenu")
                .url("/home/settings/parameters")
                .isActive(true)
                .build());
        settingsSubmenus.add(SubmenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.SETTINGS_ROLES)
                .position(2)
                .description("Roles submenu")
                .url("/home/settings/roles")
                .isActive(true)
                .build());
        navigationMenus.add(NavigationMenuDocument.builder()
                .id(UUID.randomUUID().toString())
                .name(Constants.SETTINGS_MENU)
                .position(9)
                .hasSubmenu(true)
                .url("/home/settings")
                .icon(Icons.SETTINGS)
                .description("Settings Menu")
                .isActive(true)
                .submenu(settingsSubmenus)
                .build());

        return navigationMenus;
    }
}
