package test

import (
	"testing"
	"time"
	"users-es/config"
	"users-es/models"
	"users-es/service"
	"users-es/utils"

	"github.com/google/uuid"
)

var id = "38f2391c-7017-4b88-a5e4-59c84b3c320e"

func TestCreateUser(t *testing.T) {

	config.ConnectDB()
	defer config.CloseDB()

	// Crear un usuario con datos de ejemplo
	user := models.User{
		ID:          utils.StringToUUIDPtr(id),
		FirstName:   "John",
		LastName:    "Doe",
		Username:    "johndoe",
		Password:    "password",
		Email:       "johndoe@example.com",
		Phone:       "1234567890",
		RoleID:      uuid.New(),
		Status:      "active",
		Address:     "123 Main St",
		DateOfBirth: time.Now(),
		Photo:       "default.jpg",
		CreatedAt:   time.Time{},
		DeletedAt:   time.Time{},
		UpdatedAt:   time.Time{},
	}

	testUser, err := service.CreateUser(&user)

	if err != nil {
		t.Errorf("System error: %v", err.Error())
	}

	got := testUser.ID.String()

	expected := id

	if expected != got {
		t.Error("Test CreateUser Failed!")
	}

	t.Log("Test CreateUser Donde")
}
