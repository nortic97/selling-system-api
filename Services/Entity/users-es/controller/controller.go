package controller

import (
	"fmt"
	"log"
	"net/http"
	"users-es/actions"
	"users-es/docs"
	"users-es/utils"

	"github.com/gorilla/mux"
	httpSwagger "github.com/swaggo/http-swagger"
)

func Controller() {

	REST_PORT := utils.Environment()

	log.Println("Controller Start...")
	log.Printf("REST PORT: %s", REST_PORT)

	// Swagger doc
	docs.SwaggerInfo.Title = "Users Entity API"
	docs.SwaggerInfo.Version = "0.0.1"
	docs.SwaggerInfo.Description = "This is Users Entity API."

	// Endpoints
	router := mux.NewRouter()
	router.HandleFunc("/", homeHandler).Methods("GET")
	router.HandleFunc("/users/es/v1/CreateUser", actions.CreateUser).Methods("POST")
	router.HandleFunc("/users/es/v1/ReadUserById/{id}", actions.ReadUserById).Methods("GET")
	router.HandleFunc("/users/es/v1/UpdateUser", actions.UpdateUser).Methods("PUT")
	router.HandleFunc("/users/es/v1/DeleteUser/{id}", actions.DeleteUser).Methods("DELETE")
	router.HandleFunc("/users/es/v1/actuator", actions.Actuator).Methods("GET")
	router.PathPrefix("/users/es/v1/swagger").Handler(httpSwagger.WrapHandler)

	log.Println("users-es listening on http://localhost:" + REST_PORT)
	err := http.ListenAndServe((":" + REST_PORT), router)

	if err != nil {
		log.Panic(err)
	}

}

func homeHandler(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "Entity Service For Users Enjoy :)")
}
