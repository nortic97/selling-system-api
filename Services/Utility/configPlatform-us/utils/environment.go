package utils

import "os"

const (
	DEFAULT_PORT = "3000"
	DEFAULT_ENV  = "LOCALHOST"
)

// Function for read PORT by environment variable
// "REST_PORT" is the environment variable that will be used to define port number for rest api
// In a future it is posible define new variables like "GRAPH_PORT" or "KAFKA_PORT" etc.
//
// Returns:
//	string: the port number defined by the environment variable
func PORTS() string {

	REST_PORT := os.Getenv("REST_PORT") // Get port of env

	if REST_PORT == "" {
		REST_PORT = DEFAULT_PORT // In case of not have environment variable set port by default.
	}

	return REST_PORT
}

// Function to read development mode by environment variable
// "ENV" is the environment variable that can be either "DEVELOPMENT" or "PRODUCTION".
//
// Returns:
//	string: the environment variable
func Environment() string {

	ENV := os.Getenv("ENV") // Read environment variable.

	if ENV == "" {
		ENV = DEFAULT_ENV // Define environment variable.
	}

	return ENV // Return environment variable.
}
