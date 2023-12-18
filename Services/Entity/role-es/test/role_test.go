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

	permissions := []models.Permissions{
		{

			PermissionName:       "Users",
			PermissionPosition:   1,
			PermissionHasSubMenu: true,
			PermissionUrl:        "/users",
			PermissionSubMenu: []models.SubPermission{
				{

					SubPermissionName: "List Users",
					SubPermissionUrl:  "/UsersList",
				},
				{

					SubPermissionName: "Add User",
					SubPermissionUrl:  "/AddUser",
				},
			},
		},
		{

			PermissionName:       "Roles",
			PermissionPosition:   2,
			PermissionHasSubMenu: true,
			PermissionUrl:        "/roles",
			PermissionSubMenu: []models.SubPermission{
				{

					SubPermissionName: "List Roles",
					SubPermissionUrl:  "/RolesList",
				},
				{

					SubPermissionName: "Add Role",
					SubPermissionUrl:  "/RoleAdd",
				},
			},
		},
		{
			PermissionName:       "Dashboard",
			PermissionPosition:   0,
			PermissionUrl:        "/Dashboard",
			PermissionHasSubMenu: false,
			PermissionSubMenu:    nil,
		},
	}

	return &models.NewRole{
		Id:          &id,
		RoleName:    "test_name",
		Description: "test_description",
		Permissions: permissions,
	}

}
