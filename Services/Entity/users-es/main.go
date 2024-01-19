package main

import (
	"log"
	"users-es/controller"
	"users-es/utils"
)

// @securityDefinitions.apikey ApiKeyAuth
// @in header
// @name Authorization
func main() {
	ENV := utils.Environment() // get environment variable
	REST_PORT := utils.PORTS() // get http port number
	// Show information about the service.
	log.Println("Start Service users-es...")
	log.Printf("Environment: %s", ENV)
	log.Printf("REST PORT: %s", REST_PORT)
	controller.Controller() // call controller method
}
