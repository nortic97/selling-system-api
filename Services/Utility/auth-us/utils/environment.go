package utils

import "os"

const DEFAULT_PORT = "3000"

func Environment() string {

	REST_PORT := os.Getenv("REST_PORT")

	if REST_PORT == "" {
		REST_PORT = DEFAULT_PORT
	}

	return REST_PORT
}
