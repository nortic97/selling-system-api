package actions

import (
	"auth-us/models"
	"auth-us/utils"
	"encoding/json"
	"log"
	"net/http"
)

const SERVER_UP string = "UP"

// EndPoint to show service status
// @Summary Service status
// @Description The actual endpoint show Service status.
// @Tags Actuator
// @Accept json
// @Produce json
// @Success 200 {object} models.Health
// @Router /auth/us/v1/actuator [GET]
func Actuator(w http.ResponseWriter, r *http.Request) {
	log.Println("Checking Health...")

	health := models.Health{
		Status:      SERVER_UP,
		Environment: utils.Environment(),
	}

	log.Printf("Server Status %s", SERVER_UP)
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 200 Created
	json.NewEncoder(w).Encode(health)
}
