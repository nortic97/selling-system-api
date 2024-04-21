package actions

import (
	"encoding/json"
	"log"
	"net/http"
	"users-es/errors"
	"users-es/models"

	"users-es/service"
)

// EndPoint to create a user
// @Summary Create user
// @Description The actual endpoint create a user and save in database.
// @Tags CreateUser
// @Accept json
// @Produce json
// @Param user body models.NewUser true "Created User"
// @Success 201 {object} models.User
// @Router /api/users/es/v1/CreateUser [POST]
func CreateUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Start CreateUser Action...")

	var newUser *models.User

	err := json.NewDecoder(r.Body).Decode(&newUser) // Leer el mensaje de la solicitud

	if err != nil {
		http.Error(w, "Ingrese los datos correctamente", http.StatusBadRequest)
		log.Println("Error al decodificar el cuerpo de la solicitud:", err)
		return
	}

	createdUser, err := service.CreateUser(newUser) // Guardar el usuario en la base de datos
	if err != nil {
		errors.RespondInternalServerError(w, err)
		log.Println("Error al crear el usuario:", err)
		return
	}

	// Si se creó correctamente, responder con el usuario creado
	w.Header().Set("Content-Type", "application/json") // Establecer el tipo de contenido
	w.WriteHeader(http.StatusCreated)                  // Escribir el código de estado
	json.NewEncoder(w).Encode(createdUser)

	log.Println("End CreateUser Action...")
}
