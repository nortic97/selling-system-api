package utils

import "os"

const (
	DEFAULT_PORT = "30001"
	DEFAULT_ENV  = "LOCALHOST"
)

// Function for set PORT by environment variable
// "REST_PORT" is the environment variable that will be used to define port number for rest api
// In a future it is posible define new variables like "GRAPH_PORT" or "KAFKA_PORT" etc.
func PORTS() string {

	REST_PORT := os.Getenv("REST_PORT") // Get port of env

	if REST_PORT == "" {
		REST_PORT = DEFAULT_PORT // In case of not have environment variable set port by default.
	}

	return REST_PORT
}

// Function to set development mode by environment variable
// "ENV" is the environment variable that can be either "DEVELOPMENT" or "PRODUCTION".
func Environment() string {

	ENV := os.Getenv("ENV") // Read environment variable.

	if ENV == "" {
		ENV = DEFAULT_ENV // Define environment variable.
	}

	return ENV // Return environment variable.
}
