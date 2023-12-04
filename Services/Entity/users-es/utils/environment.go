package utils

import "os"

func Environment() (string, string) {
	ENV := os.Getenv("ENV")

	PORT := ":3000"

	if ENV != "" {
		PORT = ":80"
	} else {
		PORT = ":3000"
	}

	return ENV, PORT
}
