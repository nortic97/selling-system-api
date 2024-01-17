package main

import (
	"auth-us/controller"
	"auth-us/utils"
	"log"
)

func main() {
	ENV := utils.Environment() // get environment variable
	REST_PORT := utils.PORTS() // get http port number
	// Show information about the service.
	log.Println("Start Auth Utility Service...")
	log.Printf("Environment: %s", ENV)
	log.Printf("REST PORT: %s", REST_PORT)
	controller.Controller() // call controller method
}
