package test

import (
	"auth-us/models"
	"auth-us/service"
	"testing"
)

var login *models.Login = initModelLogin()

func TestLogin(t *testing.T) {

	_, exist, _ := service.Login(login)

	got := true

	expected := exist

	if got != expected {
		t.Error("Error Login")
	}

}

func TestGenerateToken(t *testing.T) {

	data, _, _ := service.Login(login)

	if len(data) == 0 || data == nil {
		t.Error("Error Login")
	}

	tokenModel := service.GenerateToken(data[0])

	got := "Bearer"

	expected := tokenModel.Type

	if got != expected {
		t.Error("Error Generating token")
	}

}

func initModelLogin() *models.Login {

	return &models.Login{
		UserEmail:    "admin@posdash.com",
		UserPassword: "PosDashAdmin2023-*",
	}

}
