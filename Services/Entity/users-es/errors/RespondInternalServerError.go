package errors

import (
	"encoding/json"
	"net/http"
	"users-es/models"
)

// Function to return a error of system
func RespondInternalServerError(w http.ResponseWriter, err error) {
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusInternalServerError)
	json.NewEncoder(w).Encode(models.ErrorResponse{Message: "Error interno del servidor. Por favor, contacte al administrador."})
}
