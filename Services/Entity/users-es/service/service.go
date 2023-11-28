package service

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"users-es/models"
	"users-es/repository"
)

func AddUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Start AddUser Service...")

	var newUser *models.NewUser

	err := json.NewDecoder(r.Body).Decode(&newUser) //Read the request message

	if err != nil {
		fmt.Fprintf(w, "Enter the correct data")
	}

	res, _ := repository.AddUser(newUser) //Save the agenda to Database
	w.WriteHeader(http.StatusOK)          //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End AddUser Service...")
}
