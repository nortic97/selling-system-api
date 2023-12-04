package test

import (
	"testing"
	"users-es/models"
	"users-es/service"
)

var user = UserInit()

func TestCreateUser(t *testing.T) {

	userTest, _ := service.CreateUser(user)

	got := userTest.Id

	expected := *user.Id

	if got != expected {
		t.Error("Error creating user")
	}

}

func TestReadUserById(t *testing.T) {

	got, _, _ := service.ReadUserById(user.Id)

	expected := *user.Id

	if got.Id != expected {
		t.Error("Error reading user")
	}

}

func TestUpdateUser(t *testing.T) {

	user.UserName = "test_update_user"

	got, _ := service.UpdateUser(user)

	expected := "test_update_user"
	if got.UserName != expected {
		t.Error("Error updating user")
	}
}

func TestDeleteUser(t *testing.T) {

	got, _ := service.DeleteUser(user.Id)

	expected := true

	if got != expected {
		t.Error("Error deleting user")
	}
}

func UserInit() *models.NewUser {

	id := "2Z5IeLdwlWjED8CKOAXIVv9swM0"

	return &models.NewUser{
		Id:           &id,
		UserName:     "test_name",
		UserEmail:    "test_email@developer.com",
		UserPassword: "test_password",
		UserRoleId:   "test_role_id",
		UserImageUrl: "https://google.com",
	}

}
