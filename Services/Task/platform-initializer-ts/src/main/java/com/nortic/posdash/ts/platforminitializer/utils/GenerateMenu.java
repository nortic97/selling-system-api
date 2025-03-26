package com.nortic.posdash.ts.platforminitializer.utils;

import com.nortic.posdash.models.entity.RoleNavigationMenuEntity;

import java.util.List;

public class GenerateMenu {
    public static List<RoleNavigationMenuEntity.NavigationMenu> generateNavigationMenu() {
        return List.of(
                new RoleNavigationMenuEntity.NavigationMenu(
                        Constants.DASHBOARD_MENU, 1, false, "/home/dashboard", Icons.DASHBOARD, "Dashboard main menu", true, null
                ),
                new RoleNavigationMenuEntity.NavigationMenu(
                        Constants.PRODUCTS_MENU, 2, true, "/home/products", Icons.PRODUCTS, "Products Menu", true, List.of(
                        new RoleNavigationMenuEntity.SubMenu(Constants.PRODUCTS_LIST, 1, "List Product submenu", "/home/products/listProducts", true),
                        new RoleNavigationMenuEntity.SubMenu(Constants.PRODUCTS_ADD, 2, "Add Product submenu", "/home/products/addProduct", true),
                        new RoleNavigationMenuEntity.SubMenu(Constants.PRODUCTS_TRASH, 3, "Trash Product submenu", "/home/products/trashProducts", true)
                )
                ),
                new RoleNavigationMenuEntity.NavigationMenu(
                        Constants.CATEGORIES_MENU, 3, true, "/home/categories", Icons.CATEGORIES, "Categories Menu", true, List.of(
                        new RoleNavigationMenuEntity.SubMenu(Constants.CATEGORIES_LIST, 1, "List Categories submenu", "/home/categories/listCategories", true),
                        new RoleNavigationMenuEntity.SubMenu(Constants.CATEGORIES_ADD, 2, "Add Categories submenu", "/home/categories/addCategories", true),
                        new RoleNavigationMenuEntity.SubMenu(Constants.CATEGORIES_TRASH, 3, "Trash Categories submenu", "/home/categories/trashCategories", true)
                )
                ),
                new RoleNavigationMenuEntity.NavigationMenu(
                        Constants.SALE_MENU, 4, true, "/home/sale", Icons.SALE, "Sale Menu", true, List.of(
                        new RoleNavigationMenuEntity.SubMenu(Constants.SALE_LIST, 1, "List Sale submenu", "/home/sale/listSale", true),
                        new RoleNavigationMenuEntity.SubMenu(Constants.SALE_ADD, 2, "Add Sale submenu", "/home/sale/addSale", true),
                        new RoleNavigationMenuEntity.SubMenu(Constants.SALE_TRASH, 3, "Trash Sale submenu", "/home/sale/trashSale", true)
                )
                ),
                new RoleNavigationMenuEntity.NavigationMenu(
                        Constants.PURCHASES_MENU, 5, true, "/home/purchases", Icons.PURCHASES, "Purchases Menu", true, List.of(
                        new RoleNavigationMenuEntity.SubMenu(Constants.PURCHASES_LIST, 1, "List Purchases submenu", "/home/purchases/listPurchases", true),
                        new RoleNavigationMenuEntity.SubMenu(Constants.PURCHASES_ADD, 2, "Add Purchases submenu", "/home/purchases/addPurchases", true),
                        new RoleNavigationMenuEntity.SubMenu(Constants.PURCHASES_TRASH, 3, "Trash Purchases submenu", "/home/purchases/trashPurchases", true)
                )
                ),
                new RoleNavigationMenuEntity.NavigationMenu(
                        Constants.RETURNS_MENU, 6, true, "/home/returns", Icons.RETURNS, "Returns Menu", true, List.of(
                        new RoleNavigationMenuEntity.SubMenu(Constants.RETURNS_LIST, 1, "List Returns submenu", "/home/returns/listReturns", true),
                        new RoleNavigationMenuEntity.SubMenu(Constants.RETURNS_ADD, 2, "Add Returns submenu", "/home/returns/addReturns", true),
                        new RoleNavigationMenuEntity.SubMenu(Constants.RETURNS_TRASH, 3, "Trash Returns submenu", "/home/returns/trashReturns", true)
                )
                ),
                new RoleNavigationMenuEntity.NavigationMenu(
                        Constants.PEOPLE_MENU, 7, true, "/home/people", Icons.PEOPLE, "People Menu", true, List.of(
                        new RoleNavigationMenuEntity.SubMenu(Constants.PEOPLE_USERS, 1, "Users submenu", "/home/people/users", true)
                )
                ),
                new RoleNavigationMenuEntity.NavigationMenu(
                        Constants.REPORTS_MENU, 8, false, "/home/reports", Icons.REPORTS, "Reports Menu", true, null
                ),
                new RoleNavigationMenuEntity.NavigationMenu(
                        Constants.SETTINGS_MENU, 9, true, "/home/settings", Icons.SETTINGS, "Settings Menu", true, List.of(
                        new RoleNavigationMenuEntity.SubMenu(Constants.SETTINGS_PARAMETERS, 1, "Parameters submenu", "/home/settings/parameters", true),
                        new RoleNavigationMenuEntity.SubMenu(Constants.SETTINGS_ROLES, 2, "Roles submenu", "/home/settings/roles", true)
                )
                )
        );
    }
}
