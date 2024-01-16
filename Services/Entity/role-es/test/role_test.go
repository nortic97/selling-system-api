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
		{

			MenuName:       "Users",
			MenuPosition:   1,
			MenuHasSubMenu: true,
			MenuUrl:        "/users",
			MenuActions: []string{
				"POST",
				"GET",
				"PUT",
				"DELETE",
			},
			MenuSubMenu: []models.SubMenu{
				{

					SubMenuName: "List Users",
					SubMenuActions: []string{
						"POST",
						"GET",
						"PUT",
						"DELETE",
					},
					SubMenuUrl: "/UsersList",
				},
				{

					SubMenuName: "Add User",
					SubMenuActions: []string{
						"POST",
						"GET",
						"PUT",
						"DELETE",
					},
					SubMenuUrl: "/AddUser",
				},
			},
		},
		{

			MenuName:       "Roles",
			MenuPosition:   2,
			MenuHasSubMenu: true,
			MenuUrl:        "/roles",
			MenuActions: []string{
				"POST",
				"GET",
				"PUT",
				"DELETE",
			},
			MenuSubMenu: []models.SubMenu{
				{

					SubMenuName: "List Roles",
					SubMenuActions: []string{
						"POST",
						"GET",
						"PUT",
						"DELETE",
					},
					SubMenuUrl: "/RolesList",
				},
				{

					SubMenuName: "Add Role",
					SubMenuActions: []string{
						"POST",
						"GET",
						"PUT",
						"DELETE",
					},
					SubMenuUrl: "/RoleAdd",
				},
			},
		},
		{
			MenuName:       "Dashboard",
			MenuPosition:   0,
			MenuUrl:        "/Dashboard",
			MenuHasSubMenu: false,
			MenuSubMenu:    nil,
		},
	}

	return &models.NewRole{
		Id:          &id,
		RoleName:    "test_name",
		Description: "test_description",
		Menu:        Menus,
	}

}
