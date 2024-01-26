package controller

import (
	"auth-us/actions"
	"auth-us/docs"
	"auth-us/utils"
	"fmt"
	"log"
	"net/http"

	"github.com/gorilla/mux"
	"github.com/rs/cors"
	httpSwagger "github.com/swaggo/http-swagger"
)

// Function in charge of starting the HTTP server and its endpoints.
func Controller() {

	log.Println("Controller Start...")

	REST_PORT := utils.PORTS()

	// Swagger doc
	docs.SwaggerInfo.Title = "Auth Service"
	docs.SwaggerInfo.Version = "0.0.1"
	docs.SwaggerInfo.Description = "Service for Authentication Users"

	// EndPoints
	router := mux.NewRouter()
	router.HandleFunc("/", homeHandler).Methods("GET")
	router.HandleFunc("/api/auth/us/v1/Auth", actions.Auth).Methods("POST")
	router.HandleFunc("/api/auth/us/v1/actuator", actions.Actuator).Methods("GET")
	router.HandleFunc("/api/ms/auth/CheckAccessApi", actions.CheckAccessApi).Methods("GET")
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
	log.Println("Listening on http://localhost:" + REST_PORT)
	err := http.ListenAndServe(":"+REST_PORT, handler)

	if err != nil {
		log.Fatal(err)
	}

}

// Function have the welcome message.
func homeHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "Utility Service For Auth Enjoy :)")
}
