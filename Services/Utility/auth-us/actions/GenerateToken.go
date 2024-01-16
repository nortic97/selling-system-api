package actions

import (
	"auth-us/models"
	"auth-us/service"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	_ "time"
)

func GenerateToken(w http.ResponseWriter, r *http.Request) {
	log.Println("Start GenerateToken Action...")

	var login *models.Login

	err := json.NewDecoder(r.Body).Decode(&login) //Read the request message

	if err != nil {
		fmt.Fprintf(w, "Enter the correct data")
	}

	usersList, exist, _ := service.Login(login)

	if exist && len(usersList) > 0 {
		token := service.GenerateToken(usersList[0])
		w.Header().Set("content-Type", "application/json") //Set content type
		w.WriteHeader(http.StatusOK)                       //Write the status code 200
		json.NewEncoder(w).Encode(token)
		log.Println("End GenerateToken Action...")
	} else if !exist {
		w.Header().Set("content-Type", "application/json") //Set content type
		w.WriteHeader(http.StatusBadRequest)               //Write the status code 400
		json.NewEncoder(w).Encode("User not found")
		log.Println("End GenerateToken Action...")
	} else {
		w.Header().Set("content-Type", "application/json") //Set content type
		w.WriteHeader(http.StatusBadRequest)               //Write the status code 400
		json.NewEncoder(w).Encode("Incorrect Password")
		log.Println("End GenerateToken Action...")
	}

}
