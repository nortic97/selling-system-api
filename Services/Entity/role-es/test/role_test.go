package test

import (
	"role-es/models"
	"role-es/service"
	"testing"
)

var role = RoleInit()

func TestCreateRole(t *testing.T) {

	userTest, _ := service.CreateRole(role)

	got := userTest.Id

	expected := *role.Id

	if got != expected {
		t.Error("Error creating role")
	}

}

func TestReadRoleById(t *testing.T) {

	got, _, _ := service.ReadRoleById(role.Id)

	expected := *role.Id

	if got.Id != expected {
		t.Error("Error reading role")
	}

}

func TestUpdateRole(t *testing.T) {

	role.RoleName = "test_update_user"

	got, _ := service.UpdateRole(role)

	expected := "test_update_user"
	if got.RoleName != expected {
		t.Error("Error updating role")
	}
}

func TestDeleteRole(t *testing.T) {

	got, _ := service.DeleteRole(role.Id)

	expected := true

	if got != expected {
		t.Error("Error deleting role")
	}
}

func RoleInit() *models.NewRole {

	id := "2Z5IeLdwlWjED8CKOAXIVv9swM0"

	Menus := []models.Menu{
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
			MenuUrl:        "#product",
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
		//
	}

	return &models.NewRole{
		Id:          &id,
		RoleName:    "test_name",
		Description: "test_description",
		Menu:        Menus,
	}

}
