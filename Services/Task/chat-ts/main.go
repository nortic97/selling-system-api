package main

import (
	"chat-ts/actions"
	"log"
	"net/http"

	"github.com/gorilla/mux"
	"github.com/rs/cors"
)

func main() {
	go actions.StartChat()
	router := mux.NewRouter().StrictSlash(true)
	router.HandleFunc("/chat", actions.HandleWebSocket).Methods("GET")
	router.HandleFunc("/online-users", actions.GetUsersHandler).Methods("GET")

	// CORS configuration
	c := cors.New(cors.Options{
		AllowedOrigins:   []string{"*"},
		AllowedHeaders:   []string{"*"},
		AllowedMethods:   []string{"POST", "PUT", "GET", "DELETE"},
		AllowCredentials: true,
	})

	handler := c.Handler(router)

	log.Println("Server running on :8080")
	err := http.ListenAndServe(":8080", handler)
	if err != nil {
		log.Fatal("ListenAndServe: ", err)
	}
}
