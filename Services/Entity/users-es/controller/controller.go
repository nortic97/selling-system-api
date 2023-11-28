package controller

import (
	"fmt"
	"log"
	"net/http"
	"users-es/service"

	"github.com/gorilla/mux"
)

func Controller() {
	log.Println("Controller Start...")

	router := mux.NewRouter()
	router.HandleFunc("/", homeHandler).Methods("GET")
	router.HandleFunc("/users/es/v1/AddUser", service.AddUser).Methods("POST")

	err := http.ListenAndServe(":3000", router)

	if err != nil {
		log.Fatal(err)
	}

}

func homeHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "Entity Service For Users Enjoy :)")
}
