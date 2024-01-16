package controller

import (
	"auth-us/actions"
	"auth-us/utils"
	"fmt"
	"log"
	"net/http"

	"github.com/gorilla/mux"
)

func Controller() {
	REST_PORT := utils.Environment()

	log.Println("Controller Start...")
	log.Printf("REST PORT: %s", REST_PORT)

	router := mux.NewRouter()
	router.HandleFunc("/", homeHandler).Methods("GET")
	router.HandleFunc("/auth/us/v1/GenerateToken", actions.GenerateToken).Methods("POST")
	// router.HandleFunc("/users/es/v1/ReadUserById/{id}", service.ReadUserById).Methods("GET")
	// router.HandleFunc("/users/es/v1/UpdateUser", service.UpdateUser).Methods("PUT")
	// router.HandleFunc("/users/es/v1/DeleteUser/{id}", service.DeleteUser).Methods("DELETE")

	err := http.ListenAndServe(":"+REST_PORT, router)

	if err != nil {
		log.Fatal(err)
	}

}

func homeHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "Utility Service For Auth Enjoy :)")
}
