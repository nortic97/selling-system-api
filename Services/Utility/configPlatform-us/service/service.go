package service

import (
	"configPlatform-us/models"
	"configPlatform-us/repository"
	"configPlatform-us/utils"
	"log"

	"github.com/segmentio/ksuid"
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
				MenuSubMenu:    nil,
				Actions: []models.Resource{
					{
						Id:     ksuid.New().String(),
						Value:  "Read",
						Url:    "/home/dashboard",
						Action: "GET",
					},
				},
			},
			// Products
			{
				Id:             "2bB1jbPEOCaYDTSIpLChn5Nmb9I",
				MenuName:       "Products",
				Icon:           "",
				MenuPosition:   2,
				MenuHasSubMenu: true,
				MenuUrl:        "Products",
				MenuSubMenu: []models.SubMenu{
					{
						Id:              "2bB31U2Sq1xaF6UEftKK8yBmrOJ",
						SubMenuName:     "List Product",
						SubMenuPosition: 1,
						SubMenuUrl:      "/home/ListProduct",
						Actions:         nil,
					},
					{
						Id:              "2bB3ZzEmoCawT9KzI3IlAQG0YhO",
						SubMenuName:     "Add Product",
						SubMenuPosition: 2,
						SubMenuUrl:      "/home/AddProduct",
						Actions:         nil,
					},
				},
				Actions: nil,
			},
			// Sale
			{
				Id:             "2bBc9x5hFUmKLlTRbUZRls9tqJh",
				MenuName:       "Sale",
				Icon:           "",
				MenuPosition:   3,
				MenuHasSubMenu: true,
				MenuUrl:        "Sale",
				MenuSubMenu: []models.SubMenu{
					{
						Id:              "2bB31U2Sq1xaF6UEftKK8yBmrOJ",
						SubMenuName:     "List Sale",
						SubMenuPosition: 1,
						SubMenuUrl:      "/home/ListSale",
					},
					{
						Id:              "2bBcJdcqqvuvtQeKv66cGy6MbuW",
						SubMenuName:     "Add Sale",
						SubMenuPosition: 2,
						SubMenuUrl:      "/home/AddSale",
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
				MenuSubMenu: []models.SubMenu{
					{
						Id:              "2bB31U2Sq1xaF6UEftKK8yBmrOJ",
						SubMenuName:     "List Purchases",
						SubMenuPosition: 1,

						SubMenuUrl: "/home/ListPurchases",
					},
					{
						Id:              "2bJRdbHsISlTZxsdrjc9DnFMHZ8",
						SubMenuName:     "Add Purchase",
						SubMenuPosition: 2,

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

				MenuSubMenu: []models.SubMenu{
					{
						Id:              "2bJUpd5B5GeJQAuy07qNcSO5PM7",
						SubMenuName:     "List Returns",
						SubMenuPosition: 1,

						SubMenuUrl: "/home/ListReturns",
					},
					{
						Id:              "2bJUsn3uxp73Ay9rJN9lDnmhH0t",
						SubMenuName:     "Add Return",
						SubMenuPosition: 2,

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
				MenuSubMenu: []models.SubMenu{
					{
						Id:              "2bJWfobd4tJkYknnUobqrrKdeE7",
						SubMenuName:     "Costumers",
						SubMenuPosition: 1,
						SubMenuUrl:      "/home/ListCostumers",
					},
					{
						Id:              "2bJWfrrkY84ZIyXccOfteGG6ZRl",
						SubMenuName:     "Add Costumers",
						SubMenuPosition: 2,
						SubMenuUrl:      "/home/AddCostumers",
					},
					{
						Id:              "2bV9ibEPOjGBarSQSbLznkIIo11",
						SubMenuName:     "List Roles",
						SubMenuPosition: 3,
						SubMenuUrl:      "/home/ListRoles",
						Actions: []models.Resource{
							{
								Id:     ksuid.New().String(),
								Value:  "Create",
								Url:    "/api/roles/es/v1",
								Action: "POST",
							},
							{
								Id:     ksuid.New().String(),
								Value:  "Read",
								Url:    "/api/roles/es/v1",
								Action: "GET",
							},
							{
								Id:     ksuid.New().String(),
								Value:  "Update",
								Url:    "/api/roles/es/v1",
								Action: "PUT",
							},
							{
								Id:     ksuid.New().String(),
								Value:  "Delete",
								Url:    "/api/roles/es/v1",
								Action: "DELETE",
							},
						},
					},
					{
						Id:              ksuid.New().String(),
						SubMenuName:     "List Users",
						SubMenuPosition: 4,
						SubMenuUrl:      "/home/ListUsers",
						Actions: []models.Resource{
							{
								Id:     ksuid.New().String(),
								Value:  "Create",
								Url:    "/api/users/es/v1",
								Action: "POST",
							},
							{
								Id:     ksuid.New().String(),
								Value:  "Read",
								Url:    "/api/users/es/v1",
								Action: "GET",
							},
							{
								Id:     ksuid.New().String(),
								Value:  "Update",
								Url:    "/api/users/es/v1",
								Action: "PUT",
							},
							{
								Id:     ksuid.New().String(),
								Value:  "Delete",
								Url:    "/api/users/es/v1",
								Action: "DELETE",
							},
						},
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
		Resources: []models.Resource{
			// Role APi
			{
				Id:     ksuid.New().String(),
				Value:  "Create",
				Url:    "/api/roles/es/v1",
				Action: "POST",
			},
			{
				Id:     ksuid.New().String(),
				Value:  "Read",
				Url:    "/api/roles/es/v1",
				Action: "GET",
			},
			{
				Id:     ksuid.New().String(),
				Value:  "Update",
				Url:    "/api/roles/es/v1",
				Action: "PUT",
			},
			{
				Id:     ksuid.New().String(),
				Value:  "Delete",
				Url:    "/api/roles/es/v1",
				Action: "DELETE",
			},
			// Users API
			{
				Id:     ksuid.New().String(),
				Value:  "Create",
				Url:    "/api/users/es/v1",
				Action: "POST",
			},
			{
				Id:     ksuid.New().String(),
				Value:  "Read",
				Url:    "/api/users/es/v1",
				Action: "GET",
			},
			{
				Id:     ksuid.New().String(),
				Value:  "Update",
				Url:    "/api/users/es/v1",
				Action: "PUT",
			},
			{
				Id:     ksuid.New().String(),
				Value:  "Delete",
				Url:    "/api/users/es/v1",
				Action: "DELETE",
			},
			// File Server
			{
				Id:     ksuid.New().String(),
				Value:  "Create",
				Url:    "/api/fileServer/us/v1",
				Action: "POST",
			},
		},
	}

	roleUser := models.Role{
		Id:          "USER",
		RoleName:    "Usuario",
		Description: "Rol por defecto para primer Registro",
		Menu:        *menuUser,
		Resources:   nil,
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
