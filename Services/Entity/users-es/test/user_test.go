package test

import (
	"bytes"
	"encoding/json"
	"net/http"
	"net/http/httptest"
	"testing"
	"time"
	"users-es/actions"
	"users-es/models"
	"users-es/utils"

	"github.com/google/uuid"
)

var id = "38f2391c-7017-4b88-a5e4-59c84b3c320e"

func TestCreateUserEndpoint(t *testing.T) {

	// Crear un usuario con datos de ejemplo
	user := models.User{
		ID:          utils.StringToUUIDPtr(id),
		FirstName:   utils.StringPtr("John"),
		LastName:    utils.StringPtr("Doe"),
		Username:    utils.StringPtr("johndoe"),
		Password:    utils.StringPtr("password"),
		Email:       utils.StringPtr("johndoe@example.com"),
		Phone:       utils.StringPtr("1234567890"),
		RoleID:      utils.UUIDPtr(uuid.New()),
		Status:      utils.StringPtr("active"),
		Address:     utils.StringPtr("123 Main St"),
		DateOfBirth: &time.Time{},
		Photo:       utils.StringPtr("default.jpg"),
		CreatedAt:   &time.Time{},
		DeletedAt:   nil,
		UpdatedAt:   &time.Time{},
	}

	// Convierte el usuario a JSON
	jsonData, err := json.Marshal(user)
	if err != nil {
		t.Fatalf("Error al convertir el usuario a JSON: %v", err)
	}

	// Crea un nuevo Request usando httptest.NewRequest
	req := httptest.NewRequest("POST", "/api/users/es/v1/CreateUser", bytes.NewBuffer(jsonData))

	// Crea un ResponseRecorder (implementa http.ResponseWriter) para grabar la respuesta.
	rr := httptest.NewRecorder()

	// Llama al manejador directamente con el método ServeHTTP y pasa el ResponseRecorder y el Request falso.
	handler := http.HandlerFunc(actions.CreateUser)
	handler.ServeHTTP(rr, req)

	// Verifica el código de estado de la respuesta.
	if status := rr.Code; status != http.StatusCreated {
		t.Errorf("handler returned wrong status code: got %v want %v",
			status, http.StatusOK)
	}

	// Deserializa el cuerpo de la respuesta en la estructura Response
	var respBody models.User
	if err := json.Unmarshal(rr.Body.Bytes(), &respBody); err != nil {
		t.Errorf("error decoding response body: %v", err)
	}

	// Verifica el campo Message
	expectedMessage := id
	if respBody.ID.String() != expectedMessage {
		t.Errorf("handler returned unexpected message: got %v want %v",
			respBody.Status, expectedMessage)
	}

}
