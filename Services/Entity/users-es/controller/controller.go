package controller

import (
	"fmt"
	"log"
	"net/http"
	"users-es/actions"
	"users-es/auth"
	"users-es/docs"
	"users-es/utils"

	"github.com/gorilla/mux"
	"github.com/rs/cors"
	httpSwagger "github.com/swaggo/http-swagger"
)

// Function in charge of starting the HTTP server and its endpoints.
func Controller() {

	log.Println("Controller Start...")

	REST_PORT := utils.PORTS()

	// Swagger doc
	docs.SwaggerInfo.Title = "Users Entity API"
	docs.SwaggerInfo.Version = "0.0.1"
	docs.SwaggerInfo.Description = "This is Users Entity API."

	// Endpoints
	router := mux.NewRouter().StrictSlash(true)
	router.Use(auth.Authorize()) // Authorize with token
	router.HandleFunc("/", homeHandler).Methods("GET")
	router.HandleFunc("/api/users/es/v1/CreateUser", actions.CreateUser).Methods("POST")
	router.HandleFunc("/api/users/es/v1/ReadUserById/{id}", actions.ReadUserById).Methods("GET")
	router.HandleFunc("/api/users/es/v1/ReadAllUsersPaginate/{size}/{startAfterKey}", actions.ReadAllUsersPaginate).Methods("GET")
	router.HandleFunc("/api/users/es/v1/UpdateUser", actions.UpdateUser).Methods("PUT")
	router.HandleFunc("/api/users/es/v1/DeleteUser/{id}", actions.DeleteUser).Methods("DELETE")
	router.HandleFunc("/api/users/es/v1/actuator", actions.Actuator).Methods("GET")
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
	log.Println("users-es listening on http://localhost:" + REST_PORT)
	err := http.ListenAndServe((":" + REST_PORT), handler)

	if err != nil {
		log.Panic(err)
	}

}

// Function have the welcome message.
func homeHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "Entity Service For Users Enjoy :)")
}
