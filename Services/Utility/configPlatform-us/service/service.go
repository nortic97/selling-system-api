package service

import (
	"configPlatform-us/models"
	"configPlatform-us/repository"
	"configPlatform-us/utils"
	"log"
)

// Initialize parameters to save in DB.
func ConfigPlatform() bool {

	log.Println("Start ConfigPlatform Service...")

	adminMenu, userMenu := InitializeMenu()
	repository.InitializeMenu(adminMenu)
	repository.InitializeMenu(userMenu)
	roleAdmin, roleUser := InitRole(&adminMenu.Menus, &userMenu.Menus)
	repository.InitializeRole(roleAdmin)
	repository.InitializeRole(roleUser)
	user := InitUser()
	repository.InitializeUser(user)

	log.Println("End ConfigPlatform Service...")
	return true
}

// Initialize menu for two users types, one is user and the other is Administrator.
//
// Returns:
//
//	*models.InitMenu: Menu for admin users.
//	*models.InitMenu: Menu for user that sing up.
func InitializeMenu() (*models.InitMenu, *models.InitMenu) {

	log.Println("Start InitializeMenu Service...")

	adminMenu := models.InitMenu{
		Id: "initMenu",
		Menus: []models.Menu{
			// Dashboard
			{
				Id:             "2bArYUUo4cGF7i2qRZzLIipk9gc",
				MenuName:       "Dashboard",
				Icon:           "",
				MenuPosition:   1,
				MenuHasSubMenu: false,
				MenuUrl:        "/home/dashboard",
				MenuActions:    nil,
			},
			// Products
			{
				Id:             "2bB1jbPEOCaYDTSIpLChn5Nmb9I",
				MenuName:       "Products",
				Icon:           "",
				MenuPosition:   2,
				MenuHasSubMenu: true,
				MenuUrl:        "Products",
				MenuActions:    nil,
				MenuSubMenu: []models.SubMenu{
					{
						Id:              "2bB31U2Sq1xaF6UEftKK8yBmrOJ",
						SubMenuName:     "List Product",
						SubMenuPosition: 1,
						SubMenuActions: []models.Action{
							{
								Id:           "2bB21AN6K6bvkFeJYxnMKoYnSAU",
								Value:        "List",
								ApiUrlAction: "",
								Action:       "GET",
							},
							{
								Id:           "2bB2BPXRIJXWyljcDkVl36eNNK7",
								Value:        "Create",
								ApiUrlAction: "",
								Action:       "POST",
							},
							{
								Id:           "2bB2CsvVnv48GmHvpTaVoMUplc3",
								Value:        "Update",
								ApiUrlAction: "",
								Action:       "PUT",
							},
							{
								Id:           "2bB2EydyAS0BKP3J4nRiItEsMr4",
								Value:        "Delete",
								ApiUrlAction: "",
								Action:       "DELETE",
							},
						},
						SubMenuUrl: "/home/ListProduct",
					},
					{
						Id:              "2bB3ZzEmoCawT9KzI3IlAQG0YhO",
						SubMenuName:     "Add Product",
						SubMenuPosition: 2,
						SubMenuActions: []models.Action{
							{
								Id:           "2bB3eF6zkQsVYI5rSlrppiYumzh",
								Value:        "Create",
								ApiUrlAction: "",
								Action:       "POST",
							},
						},
						SubMenuUrl: "/home/AddProduct",
					},
				},
			},
			// Sale
			{
				Id:             "2bBc9x5hFUmKLlTRbUZRls9tqJh",
				MenuName:       "Sale",
				Icon:           "",
				MenuPosition:   3,
				MenuHasSubMenu: true,
				MenuUrl:        "Sale",
				MenuActions:    nil,
				MenuSubMenu: []models.SubMenu{
					{
						Id:              "2bB31U2Sq1xaF6UEftKK8yBmrOJ",
						SubMenuName:     "List Sale",
						SubMenuPosition: 1,
						SubMenuActions: []models.Action{
							{
								Id:           "2bBcGftmrX4yPOQGHBuYLYy7wgS",
								Value:        "List",
								ApiUrlAction: "",
								Action:       "GET",
							},
							{
								Id:           "2bBcHXAJgbArWIo7GdfNNaK4Eqy",
								Value:        "Create",
								ApiUrlAction: "",
								Action:       "POST",
							},
							{
								Id:           "2bBcIApgMHibglUu1HIwtSxGGQG",
								Value:        "Update",
								ApiUrlAction: "",
								Action:       "PUT",
							},
							{
								Id:           "2bBcInIb2uO7qAjlwBRLqWmaupq",
								Value:        "Delete",
								ApiUrlAction: "",
								Action:       "DELETE",
							},
						},
						SubMenuUrl: "/home/ListSale",
					},
					{
						Id:              "2bBcJdcqqvuvtQeKv66cGy6MbuW",
						SubMenuName:     "Add Sale",
						SubMenuPosition: 2,
						SubMenuActions: []models.Action{
							{
								Id:           "2bBcKNuz2dvVgtXUcf0sW1Hyj74",
								Value:        "Create",
								ApiUrlAction: "",
								Action:       "POST",
							},
						},
						SubMenuUrl: "/home/AddSale",
					},
				},
			},
			// Purchases
			{
				Id:             "2bJRL8CxcD3jtZPD9k9Pj4VHfhR",
				MenuName:       "Purchases",
				Icon:           "",
				MenuPosition:   4,
				MenuHasSubMenu: true,
				MenuUrl:        "Purchases",
				MenuActions:    nil,
				MenuSubMenu: []models.SubMenu{
					{
						Id:              "2bB31U2Sq1xaF6UEftKK8yBmrOJ",
						SubMenuName:     "List Purchases",
						SubMenuPosition: 1,
						SubMenuActions: []models.Action{
							{
								Id:           "2bJRbSJcSCovwmjf2CYZ3nBfCh4",
								Value:        "List",
								ApiUrlAction: "",
								Action:       "GET",
							},
							{
								Id:           "2bJRc2bzqEvhRqxe1UVvhZA8caQ",
								Value:        "Create",
								ApiUrlAction: "",
								Action:       "POST",
							},
							{
								Id:           "2bJRcSjOTM9AIJH9zTG0JpEudWQ",
								Value:        "Update",
								ApiUrlAction: "",
								Action:       "PUT",
							},
							{
								Id:           "2bJRcucv87x86ToFZtpxiORdCad",
								Value:        "Delete",
								ApiUrlAction: "",
								Action:       "DELETE",
							},
						},
						SubMenuUrl: "/home/ListPurchases",
					},
					{
						Id:              "2bJRdbHsISlTZxsdrjc9DnFMHZ8",
						SubMenuName:     "Add Purchase",
						SubMenuPosition: 2,
						SubMenuActions: []models.Action{
							{
								Id:           "2bJReTxdDqq2oRDzmWHzXqsWLUN",
								Value:        "Create",
								ApiUrlAction: "",
								Action:       "POST",
							},
						},
						SubMenuUrl: "/home/AddPurchase",
					},
				},
			},
			// Returns
			{
				Id:             "2bJRL8CxcD3jtZPD9k9Pj4VHfhR",
				MenuName:       "Returns",
				Icon:           "",
				MenuPosition:   5,
				MenuHasSubMenu: true,
				MenuUrl:        "Returns",
				MenuActions:    nil,
				MenuSubMenu: []models.SubMenu{
					{
						Id:              "2bJUpd5B5GeJQAuy07qNcSO5PM7",
						SubMenuName:     "List Returns",
						SubMenuPosition: 1,
						SubMenuActions: []models.Action{
							{
								Id:           "2bJUpd5B5GeJQAuy07qNcSO5PM7",
								Value:        "List",
								ApiUrlAction: "",
								Action:       "GET",
							},
							{
								Id:           "2bJUpYg8IOoaC0SL1XkBDPIJtNz",
								Value:        "Create",
								ApiUrlAction: "",
								Action:       "POST",
							},
							{
								Id:           "2bJUpZIet0uaB5MqbteLWTQ703V",
								Value:        "Update",
								ApiUrlAction: "",
								Action:       "PUT",
							},
							{
								Id:           "2bJUs2qrLZuDMCRP57tCmQVpwPq",
								Value:        "Delete",
								ApiUrlAction: "",
								Action:       "DELETE",
							},
						},
						SubMenuUrl: "/home/ListReturns",
					},
					{
						Id:              "2bJUsn3uxp73Ay9rJN9lDnmhH0t",
						SubMenuName:     "Add Return",
						SubMenuPosition: 2,
						SubMenuActions: []models.Action{
							{
								Id:           "2bJUsrcbHQXxWeUdbrFTkJKedkQ",
								Value:        "Create",
								ApiUrlAction: "",
								Action:       "POST",
							},
						},
						SubMenuUrl: "/home/AddReturn",
					},
				},
			},
			// People
			{
				Id:             "2bJWfqtlcmjtQtSLNoGA1VSNuZ7",
				MenuName:       "People",
				Icon:           "",
				MenuPosition:   5,
				MenuHasSubMenu: true,
				MenuUrl:        "People",
				MenuActions:    nil,
				MenuSubMenu: []models.SubMenu{
					{
						Id:              "2bJWfobd4tJkYknnUobqrrKdeE7",
						SubMenuName:     "Costumers",
						SubMenuPosition: 1,
						SubMenuActions: []models.Action{
							{
								Id:           "2bJWfrkdyFHWXYm9zZgpYRKQd89",
								Value:        "List",
								ApiUrlAction: "",
								Action:       "GET",
							},
							{
								Id:           "2bJWfpUHjs4tJh3HnMPGSvVbiF7",
								Value:        "Create",
								ApiUrlAction: "",
								Action:       "POST",
							},
							{
								Id:           "2bJWfoKWzKADaqqA395dV2nXW3M",
								Value:        "Update",
								ApiUrlAction: "",
								Action:       "PUT",
							},
							{
								Id:           "2bJWfreOhxvB9wt7vOMByT5lRVU",
								Value:        "Delete",
								ApiUrlAction: "",
								Action:       "DELETE",
							},
						},
						SubMenuUrl: "/home/ListCostumers",
					},
					{
						Id:              "2bJWfrrkY84ZIyXccOfteGG6ZRl",
						SubMenuName:     "Add Costumers",
						SubMenuPosition: 2,
						SubMenuActions: []models.Action{
							{
								Id:           "2bJWfkke16pF7PafKiCIIphELVg",
								Value:        "Create",
								ApiUrlAction: "",
								Action:       "POST",
							},
						},
						SubMenuUrl: "/home/AddCostumers",
					},
				},
			},
		},
	}

	userMenu := models.InitMenu{
		Id: "userMenu",
		Menus: []models.Menu{
			// Dashboard
			{
				Id:             "2bArYUUo4cGF7i2qRZzLIipk9gc",
				MenuName:       "Dashboard",
				Icon:           "",
				MenuPosition:   1,
				MenuHasSubMenu: false,
				MenuUrl:        "/home/dashboard",
				MenuActions:    nil,
			},
		},
	}

	log.Println("End InitializeMenu Service...")
	return &adminMenu, &userMenu

}

// Initialize roles parameters.
//
// Parameters:
//
//	menuAdmin (*[]models.Menu): Have menu for Admin.
//	menuUser (*[]models.Menu): Initialize role for User.
//
// Returns:
//
//	*models.Role: Menu for admin users.
//	*models.Role: Menu for user that sing up.
func InitRole(menuAdmin *[]models.Menu, menuUser *[]models.Menu) (*models.Role, *models.Role) {

	log.Println("Start InitRole Service...")

	roleAdmin := models.Role{
		Id:          "ADMIN",
		RoleName:    "Administrador",
		Description: "Rol por defecto",
		Menu:        *menuAdmin,
	}

	roleUser := models.Role{
		Id:          "USER",
		RoleName:    "Usuario",
		Description: "Rol por defecto para primer Registro",
		Menu:        *menuUser,
	}

	log.Println("End InitRole Service...")

	return &roleAdmin, &roleUser

}

// Initialize a user.
//
// Returns:
//
//	*models.User: Return a user.
func InitUser() *models.User {

	log.Println("Start InitUser Service...")

	pass, _ := utils.EncodePassword("PosDashAdmin2023-*")

	user := models.User{
		Id:           "initUser",
		UserName:     "Administrador",
		UserEmail:    "admin@posdash.com",
		UserPassword: pass,
		UserRoleId:   "ADMIN",
		UserImageUrl: "",
	}

	log.Println("End InitUser Service...")

	return &user
}
