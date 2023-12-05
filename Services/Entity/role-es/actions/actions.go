package actions

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"role-es/models"
	"role-es/service"

	"github.com/gorilla/mux"
)

func CreateRole(w http.ResponseWriter, r *http.Request) {
	log.Println("Start CreateRole Action...")

	var newRole *models.NewRole

	err := json.NewDecoder(r.Body).Decode(&newRole) //Read the request message

	if err != nil {
		fmt.Fprintf(w, "Enter the correct data")
	}

	res, _ := service.CreateRole(newRole)              //Save the Role to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End CreateRole Action...")
}

func UpdateRole(w http.ResponseWriter, r *http.Request) {
	log.Println("Start UpdateRole Action...")

	var newRole *models.NewRole

	err := json.NewDecoder(r.Body).Decode(&newRole) //Read the request message

	if err != nil {
		fmt.Fprintf(w, "Enter the correct data")
	}

	res, _ := service.UpdateRole(newRole)              //Update the Role to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End UpdateRole Action...")
}

func DeleteRole(w http.ResponseWriter, r *http.Request) {
	log.Println("Start DeleteRole Action...")

	id := mux.Vars(r)["id"] //Read the URL Params

	res, _ := service.DeleteRole(&id)                  //Save the Role to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End DeleteRole Service...")
}

func ReadRoleById(w http.ResponseWriter, r *http.Request) {
	log.Println("Start ReadRoleById Action...")

	id := mux.Vars(r)["id"] //Read the URL Params

	res, _, _ := service.ReadRoleById(&id)             //Save the Role to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End ReadRoleById Action...")
}
