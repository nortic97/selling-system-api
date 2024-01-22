package controller

import (
	"fileServer-us/actions"
	"fileServer-us/docs"
	"fileServer-us/utils"
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
	docs.SwaggerInfo.Title = "File Server Service"
	docs.SwaggerInfo.Version = "0.0.1"
	docs.SwaggerInfo.Description = "Service for Files"

	// EndPoints
	router := mux.NewRouter().StrictSlash(true)
	//router.Use(auth.Authorize())
	router.HandleFunc("/", homeHandler).Methods("GET")
	router.HandleFunc("/api/fileServer/us/v1/UploadFile", actions.UploadFile).Methods("POST")
	//router.HandleFunc("/api/fileServer/us/v1/actuator", actions.Actuator).Methods("GET")
	router.PathPrefix("/swagger").Handler(httpSwagger.WrapHandler)

	// CORS configuration
	c := cors.New(cors.Options{
		AllowedOrigins:   []string{"*"},
		AllowedHeaders:   []string{"*"},
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
	fmt.Fprintf(w, "Utility Service For Files Enjoy :)")
}
