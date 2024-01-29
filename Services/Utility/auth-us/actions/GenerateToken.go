package actions

import (
	"auth-us/models"
	"auth-us/service"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	_ "time"
)

// EndPoint to generate a JWT Token and Login.
// @Summary Generate Token and Login.
// @Description The actual endpoint generate a JWT Token and identifies if the user can be login.
// @Tags Login
// @Accept json
// @Produce json
// @Param login body models.Login true "Login"
// @Success 200 {object} models.Token
// @Router /api/auth/us/v1/Auth [POST]
func Auth(w http.ResponseWriter, r *http.Request) {
	log.Println("Start GenerateToken Action...")

	var login *models.Login

	err := json.NewDecoder(r.Body).Decode(&login) //Read the request message

	if err != nil {
		fmt.Fprintf(w, "Enter the correct data")
	}

	usersList, exist, _ := service.Login(login)

	if exist && len(usersList) > 0 {
		token := service.GenerateToken(usersList[0])       // Generate the token
		w.Header().Set("content-Type", "application/json") //Set content type
		w.WriteHeader(http.StatusOK)                       //Write the status code 200
		json.NewEncoder(w).Encode(token)
		log.Println("End GenerateToken Action...")
	} else if !exist {
		w.Header().Set("content-Type", "application/json") //Set content type
		w.WriteHeader(http.StatusBadRequest)               //Write the status code 400
		json.NewEncoder(w).Encode("User not found")
		log.Println("End GenerateToken Action...")
	} else {
		w.Header().Set("content-Type", "application/json") //Set content type
		w.WriteHeader(http.StatusBadRequest)               //Write the status code 400
		json.NewEncoder(w).Encode("Incorrect Password")
		log.Println("End GenerateToken Action...")
	}

}
