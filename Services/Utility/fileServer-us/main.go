package main

import (
	"fileServer-us/controller"
	"fileServer-us/utils"
	"log"
)

// @securityDefinitions.apikey ApiKeyAuth
// @in header
// @name Authorization
func main() {
	ENV := utils.Environment() // get environment variable
	REST_PORT := utils.PORTS() // get http port number
	// Show information about the service.
	log.Println("Start File Server Utility Service ...")
	log.Printf("Environment: %s", ENV)
	log.Printf("REST PORT: %s", REST_PORT)
	controller.Controller() // call controller method
}
