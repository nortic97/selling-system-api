package auth

import (
	"context"
	"log"
	"net/http"
	"strings"
)

var (
	userCtxKey = &contextKey{"user"}
)

type contextKey struct {
	name string
}

// Authorize godoc
func Authorize() func(http.Handler) http.Handler {
	return func(next http.Handler) http.Handler {
		return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {

			header := r.Header.Get("Authorization")

			// Allow unauthenticated users in
			if header == "" && (strings.Contains(r.URL.Path, "/swagger/") || strings.Contains(r.URL.Path, "actuator") || strings.Contains(r.URL.Path, "CreateUser")) {
				next.ServeHTTP(w, r)
				return
			}

			if strings.Contains(r.URL.Path, "/api/") { // Validate if request is to rest api
				url := split(r.URL.Path, "/") // Split to url

				log.Println("Checking access for rest api", url, r.Method)
				access, err := checkAccess(header, url, r.Method) // Valid access for url and method : GET, POST, PUT, DELETE
				if !access || err != nil {                        // Validate if access
					log.Println("Access denied for rest api", url, r.Method)
					http.Error(w, "Access denied", http.StatusForbidden) // Write and return the status code 403 Status Forbidden
					return
				}
				log.Println("Access succeed for rest api", url, r.Method)

			} else { // If request is to graphql api
				ctx := context.WithValue(r.Context(), userCtxKey, header) // Add resources in context
				r = r.WithContext(ctx)                                    // And call the next with our new context
			}

			next.ServeHTTP(w, r)
		})
	}
}

func checkAccess(token string, resource string, action string) (bool, error) {

	if token != "" {
		return true, nil
	}

	return false, nil
}

func split(value, char string) string {
	index := strings.LastIndex(value, char)
	url := value[:index+1]
	return url
}
