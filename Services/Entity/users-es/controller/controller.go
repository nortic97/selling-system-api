package controller

import (
	"fmt"
	"log"
	"net/http"
	"os"
	"users-es/actions"

	"github.com/gorilla/mux"
)

var ENV = os.Getenv("ENV")

var PORT string = ":3000"

func Controller() {
	log.Println("Controller Start...")
	log.Printf("Environment: %s", ENV)

	router := mux.NewRouter()
	router.HandleFunc("/", homeHandler).Methods("GET")
	router.HandleFunc("/users/es/v1/CreateUser", actions.CreateUser).Methods("POST")
	router.HandleFunc("/users/es/v1/ReadUserById/{id}", actions.ReadUserById).Methods("GET")
	router.HandleFunc("/users/es/v1/UpdateUser", actions.UpdateUser).Methods("PUT")
	router.HandleFunc("/users/es/v1/DeleteUser/{id}", actions.DeleteUser).Methods("DELETE")

	if ENV != "" {
		PORT = ":80"
	} else {
		PORT = ":3000"
	}

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
