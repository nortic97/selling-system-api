package controller

import (
	"fmt"
	"log"
	"net/http"
	"role-es/actions"
	"role-es/utils"

	"github.com/gorilla/mux"
)

var ENV, PORT = utils.Environment()

func Controller() {
	log.Println("Controller Start...")
	log.Printf("Environment: %s", ENV)

	router := mux.NewRouter()
	router.HandleFunc("/", homeHandler).Methods("GET")
	router.HandleFunc("/roles/es/v1/CreateRole", actions.CreateRole).Methods("POST")
	router.HandleFunc("/roles/es/v1/ReadRoleById/{id}", actions.ReadRoleById).Methods("GET")
	router.HandleFunc("/roles/es/v1/UpdateRole", actions.UpdateRole).Methods("PUT")
	router.HandleFunc("/roles/es/v1/DeleteRole/{id}", actions.DeleteRole).Methods("DELETE")
	router.HandleFunc("/actuator", actions.Actuator).Methods("GET")

	log.Println("role-es listening on http://localhost" + PORT)
	err := http.ListenAndServe(PORT, router)

	if err != nil {
		log.Panic(err)
	}

}

func homeHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "Entity Service For roles Enjoy :)")
}
