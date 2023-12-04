package controller

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"users-es/actions"
	"users-es/utils"

	"github.com/gorilla/mux"
)

var ENV, PORT = utils.Environment()

func Controller() {
	log.Println("Controller Start...")
	log.Printf("Environment: %s", ENV)

	router := mux.NewRouter()
	router.HandleFunc("/", homeHandler).Methods("GET")
	router.HandleFunc("/users/es/v1/CreateUser", actions.CreateUser).Methods("POST")
	router.HandleFunc("/users/es/v1/ReadUserById/{id}", actions.ReadUserById).Methods("GET")
	router.HandleFunc("/users/es/v1/UpdateUser", actions.UpdateUser).Methods("PUT")
	router.HandleFunc("/users/es/v1/DeleteUser/{id}", actions.DeleteUser).Methods("DELETE")
	router.HandleFunc("/actuator", actuator).Methods("GET")

	log.Println("users-es listening on http://localhost" + PORT)
	err := http.ListenAndServe(PORT, router)

	if err != nil {
		log.Fatal(err)
	}

}

func homeHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "Entity Service For Users Enjoy :)")
}

func actuator(w http.ResponseWriter, r *http.Request) {
	log.Println("Checking Health...")

	health := struct {
		Status string
		Env    string
	}{
		"UP",
		ENV,
	}

	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 200 Created
	json.NewEncoder(w).Encode(health)
}
