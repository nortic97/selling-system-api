package utils

import "os"

const (
	DEFAULT_PORT        = "3002"
	DEFAULT_ENV         = "LOCALHOST"
	DEFAULT_DB_HOST     = "localhost"
	DEFAULT_DB_PORT     = "5432"
	DEFAULT_DB_USER     = "postgres"
	DEFAULT_DB_PASSWORD = "1234"
	DEFAULT_DB_NAME     = "store"
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

// Function to read database Config
// Returns:
//	string: host db
//	string: port db
//	string: user db
//	string: password db
//	string: name db
func DataBaseConf() (string, string, string, string, string) {

	DB_HOST := os.Getenv("DB_HOST")         // Read environment variable.
	DB_PORT := os.Getenv("DB_PORT")         // Read environment variable.
	DB_USER := os.Getenv("DB_USER")         // Read environment variable.
	DB_PASSWORD := os.Getenv("DB_PASSWORD") // Read environment variable.
	DB_NAME := os.Getenv("DB_NAME")         // Read environment variable.

	if DB_HOST == "" {
		DB_HOST = DEFAULT_DB_HOST // Define environment variable.
	}

	if DB_PORT == "" {
		DB_PORT = DEFAULT_DB_PORT // Define environment variable.
	}

	if DB_USER == "" {
		DB_USER = DEFAULT_DB_USER // Define environment variable.
	}

	if DB_PASSWORD == "" {
		DB_PASSWORD = DEFAULT_DB_PASSWORD // Define environment variable.
	}

	if DB_NAME == "" {
		DB_NAME = DEFAULT_DB_NAME // Define environment variable.
	}

	return DB_HOST, DB_PORT, DB_USER, DB_PASSWORD, DB_NAME // Return environment variable.

}
