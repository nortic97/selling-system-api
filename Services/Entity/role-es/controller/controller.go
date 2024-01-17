package controller

import (
	"fmt"
	"log"
	"net/http"
	"role-es/actions"
	"role-es/docs"
	"role-es/utils"

	"github.com/gorilla/mux"
	httpSwagger "github.com/swaggo/http-swagger"
)

func Controller() {

	log.Println("Controller Start...")

	REST_PORT := utils.PORTS()

	// Swagger doc
	docs.SwaggerInfo.Title = "Roles Entity API"
	docs.SwaggerInfo.Version = "0.0.1"
	docs.SwaggerInfo.Description = "This is Roles Entity API."

	// Endpoints
	router := mux.NewRouter()
	router.HandleFunc("/", homeHandler).Methods("GET")
	router.HandleFunc("/roles/es/v1/CreateRole", actions.CreateRole).Methods("POST")
	router.HandleFunc("/roles/es/v1/ReadRoleById/{id}", actions.ReadRoleById).Methods("GET")
	router.HandleFunc("/roles/es/v1/UpdateRole", actions.UpdateRole).Methods("PUT")
	router.HandleFunc("/roles/es/v1/DeleteRole/{id}", actions.DeleteRole).Methods("DELETE")
	router.HandleFunc("/roles/es/v1/actuator", actions.Actuator).Methods("GET")
	router.PathPrefix("/roles/es/v1/swagger").Handler(httpSwagger.WrapHandler)

	// Start HTTP server
	log.Println("role-es listening on http://localhost:" + REST_PORT)
	err := http.ListenAndServe((":" + REST_PORT), router)

	if err != nil {
		log.Panic(err)
	}

}

// Function have the welcome message.
func homeHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "Entity Service For roles Enjoy :)")
}
