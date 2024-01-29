package controller

import (
	"fmt"
	"log"
	"net/http"
	"role-es/actions"
	"role-es/auth"
	"role-es/docs"
	"role-es/utils"

	"github.com/gorilla/mux"
	"github.com/rs/cors"
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
	router := mux.NewRouter().StrictSlash(true)
	router.Use(auth.Authorize()) // Authorize with token
	router.HandleFunc("/", homeHandler).Methods("GET")
	router.HandleFunc("/api/roles/es/v1/CreateRole", actions.CreateRole).Methods("POST")
	router.HandleFunc("/api/roles/es/v1/ReadRoleById/{id}", actions.ReadRoleById).Methods("GET")
	router.HandleFunc("/api/roles/es/v1/ReadAllRolesPaginate/{size}/{startAfterKey}", actions.ReadAllRolesPaginate).Methods("GET")
	router.HandleFunc("/api/roles/es/v1/GetAllRoles", actions.GetAllRoles).Methods("GET")
	router.HandleFunc("/api/roles/es/v1/UpdateRole", actions.UpdateRole).Methods("PUT")
	router.HandleFunc("/api/roles/es/v1/DeleteRole/{id}", actions.DeleteRole).Methods("DELETE")
	router.HandleFunc("/api/roles/es/v1/actuator", actions.Actuator).Methods("GET")
	router.PathPrefix("/swagger").Handler(httpSwagger.WrapHandler)

	// CORS configuration
	c := cors.New(cors.Options{
		AllowedOrigins:   []string{"*"},
		AllowedHeaders:   []string{"*"},
		AllowedMethods:   []string{"POST", "PUT", "GET", "DELETE"},
		AllowCredentials: true,
	})

	handler := c.Handler(router)

	// Start HTTP server
	log.Println("listening on http://localhost:" + REST_PORT)
	err := http.ListenAndServe((":" + REST_PORT), handler)

	if err != nil {
		log.Panic(err)
	}

}

// Function have the welcome message.
func homeHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "Entity Service For roles Enjoy :)")
}
