package auth

import (
	"encoding/json"
	"io"
	"log"
	"net/http"
	"net/url"
	"os"
	"strings"
)

// Authorize is a middleware for Auth user.
func Authorize() func(http.Handler) http.Handler {
	return func(next http.Handler) http.Handler {
		return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {

			header := r.Header.Get("Authorization")

			// Allow unauthenticated users in
			if header == "" && (strings.Contains(r.URL.Path, "/swagger/") || strings.Contains(r.URL.Path, "actuator")) {
				next.ServeHTTP(w, r)
				return
			}

			if strings.Contains(r.URL.Path, "/api/") { // Validate if request is to rest api

				url := "/api/roles/es/v1"

				log.Println("Checking access for rest api", url, r.Method)
				access, err := checkAccess(header, url, r.Method) // Valid access for url and method : GET, POST, PUT, DELETE
				if !access || err != nil {                        // Validate if access
					log.Println("Access denied for rest api", url, r.Method)
					http.Error(w, "Access denied", http.StatusForbidden) // Write and return the status code 403 Status Forbidden
					return
				}
				log.Println("Access succeed for rest api", url, r.Method)

			}

			next.ServeHTTP(w, r)
		})
	}
}

func checkAccess(token string, resource string, action string) (bool, error) {

	var req *http.Request
	var err error

	if os.Getenv("ENV") == "PRODUCTION" {

		req, err = http.NewRequest("GET", "http://192.168.1.39:3000/api/ms/auth/CheckAccessApi?resource="+url.QueryEscape(resource)+"&action="+action, nil)

	} else {
		req, err = http.NewRequest("GET", "http://localhost:3000/api/ms/auth/CheckAccessApi?resource="+url.QueryEscape(resource)+"&action="+action, nil)
	}

	if err != nil {
		return false, err
	}

	req.Header.Add("Authorization", token) // Add authorization header to the req

	client := &http.Client{} // Send req using http Client
	resp, err := client.Do(req)
	if err != nil {
		return false, err
	}

	var access bool

	body, _ := io.ReadAll(resp.Body) // Read the request message
	json.Unmarshal(body, &access)    // Parse message to var access
	return access, nil

}
