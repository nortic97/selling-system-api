package service

import (
	"configPlatform-us/models"
	"configPlatform-us/repository"
	"configPlatform-us/utils"
	"log"
)

func ConfigPlatform() bool {

	log.Println("Start ConfigPlatform Service...")

	menu := initializeMenu()
	repository.InitializeMenu(menu)
	role := initRole(&menu.Menus)
	repository.InitializeRole(role)
	user := initUser()
	repository.InitializeUser(user)

	log.Println("End ConfigPlatform Service...")
	return true
}

func initializeMenu() *models.InitMenu {

	log.Println("Start initializeMenu Service...")

	menu := models.InitMenu{
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
				MenuUrl:        "product",
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
		},
	}

	log.Println("End initializeMenu Service...")
	return &menu

}

func initRole(menu *[]models.Menu) *models.Role {

	log.Println("Start initRole Service...")

	role := models.Role{
		Id:          "ADMIN",
		RoleName:    "Administrador",
		Description: "Rol por defecto",
		Menu:        *menu,
	}

	log.Println("End initRole Service...")

	return &role

}

func initUser() *models.User {

	log.Println("Start initUser Service...")

	pass, _ := utils.EncodePassword("PosDashAdmin2023-*")

	user := models.User{
		Id:           "initUser",
		UserName:     "Administrador",
		UserEmail:    "admin@posdash.com",
		UserPassword: pass,
		UserRoleId:   "ADMIN",
		UserImageUrl: "",
	}

	log.Println("End initUser Service...")

	return &user
}
