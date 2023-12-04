package actions

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"users-es/models"
	"users-es/service"

	"github.com/gorilla/mux"
)

func CreateUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Start CreateUser Action...")

	var newUser *models.NewUser

	err := json.NewDecoder(r.Body).Decode(&newUser) //Read the request message

	if err != nil {
		fmt.Fprintf(w, "Enter the correct data")
	}

	res, _ := service.CreateUser(newUser)              //Save the user to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End CreateUser Action...")
}

func UpdateUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Start UpdateUser Action...")

	var newUser *models.NewUser

	err := json.NewDecoder(r.Body).Decode(&newUser) //Read the request message

	if err != nil {
		fmt.Fprintf(w, "Enter the correct data")
	}

	res, _ := service.UpdateUser(newUser)              //Update the user to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End UpdateUser Action...")
}

func DeleteUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Start DeleteUser Action...")

	id := mux.Vars(r)["id"] //Read the URL Params

	res, _ := service.DeleteUser(&id)                  //Save the user to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End DeleteUser Service...")
}

func ReadUserById(w http.ResponseWriter, r *http.Request) {
	log.Println("Start ReadUser Action...")

	id := mux.Vars(r)["id"] //Read the URL Params

	res, _, _ := service.ReadUserById(&id)             //Save the user to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End ReadUserById Action...")
}
