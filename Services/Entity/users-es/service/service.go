package service

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"users-es/models"
	"users-es/repository"

	"github.com/gorilla/mux"
)

func CreateUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Start CreateUser Service...")

	var newUser *models.NewUser

	err := json.NewDecoder(r.Body).Decode(&newUser) //Read the request message

	if err != nil {
		fmt.Fprintf(w, "Enter the correct data")
	}

	res, _ := repository.CreateUser(newUser)           //Save the user to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End CreateUser Service...")
}

func UpdateUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Start UpdateUser Service...")

	var newUser *models.NewUser

	err := json.NewDecoder(r.Body).Decode(&newUser) //Read the request message

	if err != nil {
		fmt.Fprintf(w, "Enter the correct data")
	}

	res, _ := repository.UpdateUser(newUser)           //Update the user to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End UpdateUser Service...")
}

func DeleteUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Start DeleteUser Service...")

	id := mux.Vars(r)["id"] //Read the URL Params

	res, _ := repository.DeleteUser(&id)               //Save the user to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End DeleteUser Service...")
}

func ReadUserById(w http.ResponseWriter, r *http.Request) {
	log.Println("Start ReadUser Service...")

	id := mux.Vars(r)["id"] //Read the URL Params

	res, _, _ := repository.ReadUserById(&id)          //Save the user to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End ReadUserById Service...")
}
