package actions

import (
	"auth-us/models"
	"auth-us/service"
	"encoding/json"
	"log"
	"net/http"
	"strings"

	"github.com/golang-jwt/jwt"
)

// EndPoint to Check Access at api.
// @Summary Check Access to api's URL.
// @Description The actual show if have permissions.
// @Tags CheckAccess
// @Accept json
// @Produce json
// @Param resource query string true "resource"
// @Param action query string true "action"
// @Success 200 boolean boolean
// @Security ApiKeyAuth
// @Router /api/ms/auth/CheckAccessApi [GET]
func CheckAccess(w http.ResponseWriter, r *http.Request) {

	var roleModel *models.Role

	var result bool = false

	ti, _ := validToken(r) //Validate the token received

	claims, _ := extractClaims(ti)

	UserRoleId := claims["UserRoleId"].(string) //Get claim user
	resource := r.URL.Query()["resource"][0]    //Obtains the request resource param
	action := r.URL.Query()["action"][0]        //Obtains the request action param

	result = exceptions(resource, action) // Confirm if URL is exception o resource role and only need Authenticated.

	roleModel, err := service.GetRoleById(&UserRoleId)

	for _, v := range roleModel.Resources {
		if v.Url == resource && v.Action == action {
			result = true
		}
	}

	if err != nil { //Validate if error
		http.Error(w, "Doesn't access to resource :(", http.StatusUnauthorized) //Write the status code 401 Unauthorized
	} else {
		w.Header().Set("content-Type", "application/json") //Set content type
		w.WriteHeader(http.StatusOK)
		json.NewEncoder(w).Encode(result)
	}
}

func validToken(r *http.Request) (*jwt.Token, error) {

	auth := r.Header.Get("Authorization")
	prefix := "Bearer "
	tokenString := ""

	if auth != "" && strings.HasPrefix(auth, prefix) {
		tokenString = auth[len(prefix):]
	}

	token, err := jwt.Parse(tokenString, func(token *jwt.Token) (interface{}, error) {
		return []byte("P05d4sh"), nil
	})

	if token.Valid {
		return token, nil
	} else if ve, ok := err.(*jwt.ValidationError); ok {
		if ve.Errors&jwt.ValidationErrorMalformed != 0 {
			log.Println("That's not even a token")
			return token, err
		} else if ve.Errors&(jwt.ValidationErrorExpired|jwt.ValidationErrorNotValidYet) != 0 {
			// Token is either expired or not active yet
			log.Println("Timing is everything")
			return token, err
		} else {
			log.Println("Couldn't handle this token:", err)
			return token, err
		}
	} else {
		log.Println("Couldn't handle this token:", err)
		return token, err
	}
}

func extractClaims(token *jwt.Token) (jwt.MapClaims, bool) {

	if claims, ok := token.Claims.(jwt.MapClaims); ok && token.Valid {
		return claims, true
	} else {
		log.Printf("Invalid JWT Token")
		return nil, false
	}
}

func exceptions(resource string, action string) bool {

	if action == "GET" {

		if resource == "/api/roles/es/v1" {
			return true
		}

		if resource == "/api/users/es/v1" {
			return true
		}

	}

	if action == "POST" {

		if resource == "/api/fileServer/us/v1" {
			return true
		}

	}

	return false

}
