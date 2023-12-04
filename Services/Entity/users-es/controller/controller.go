package controller

import (
	"fmt"
	"log"
	"net/http"
	"users-es/actions"
	"users-es/utils"

	"github.com/gorilla/mux"
	"github.com/sinhashubham95/go-actuator"
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
	router.Handle("/actuator", actuator.GetActuatorHandler(&actuator.Config{}))

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
