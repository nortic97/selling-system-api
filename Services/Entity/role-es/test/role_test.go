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
		},
		// Products
		{
			Id:             "2bB1jbPEOCaYDTSIpLChn5Nmb9I",
			MenuName:       "Products",
			Icon:           "",
			MenuPosition:   2,
			MenuHasSubMenu: true,
			MenuUrl:        "#product",

			MenuSubMenu: []models.SubMenu{
				{
					Id:              "2bB31U2Sq1xaF6UEftKK8yBmrOJ",
					SubMenuName:     "List Product",
					SubMenuPosition: 1,
					SubMenuUrl:      "/home/ListProduct",
				},
				{
					Id:              "2bB3ZzEmoCawT9KzI3IlAQG0YhO",
					SubMenuName:     "Add Product",
					SubMenuPosition: 2,
					SubMenuUrl:      "/home/AddProduct",
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
		Resources: []models.Resource{
			{
				Id:     "test_id",
				Url:    "/api/",
				Action: "GET",
			},
		},
	}

}
