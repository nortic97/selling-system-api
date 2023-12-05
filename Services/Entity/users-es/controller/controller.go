package controller

import (
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
	router.HandleFunc("/actuator", actions.Actuator).Methods("GET")

	log.Println("users-es listening on http://localhost" + PORT)
	err := http.ListenAndServe(PORT, router)

	if err != nil {
		log.Panic(err)
	}

}

func homeHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "Entity Service For Users Enjoy :)")
}
