package actions

import (
	"encoding/json"
	"log"
	"net/http"
	"role-es/models"
	"role-es/utils"
)

var ENV, PORT = utils.Environment()

const SERVER_UP string = "UP"

func Actuator(w http.ResponseWriter, r *http.Request) {
	log.Println("Checking Health...")

	health := models.Health{
		Status: SERVER_UP,
		Env:    ENV,
	}

	log.Printf("Server Status %s", SERVER_UP)
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 200 Created
	json.NewEncoder(w).Encode(health)
}
