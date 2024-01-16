package actions

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"users-es/models"
	"users-es/service"

	"github.com/gorilla/mux"
)

// EndPoint to create a user
// @Summary Create user
// @Description The actual endpoint create a user and save in database.
// @Tags CreateUser
// @Accept json
// @Produce json
// @Param user body models.NewUser true "Created User"
// @Success 200 {object} models.User
// @Router /users/es/v1/CreateUser [POST]
func CreateUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Start CreateUser Action...")

	var newUser *models.NewUser

	err := json.NewDecoder(r.Body).Decode(&newUser) //Read the request message

	if err != nil {
		fmt.Fprintf(w, "Enter the correct data")
	}

	res, _ := service.CreateUser(newUser)              //Save the user to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End CreateUser Action...")
}

// EndPoint to update a user
// @Summary Update user
// @Description The actual endpoint Update a user and save in database.
// @Tags UpdateUser
// @Accept json
// @Produce json
// @Param user body models.NewUser true "Update User"
// @Success 200 {object} models.User
// @Router /users/es/v1/UpdateUser [PUT]
func UpdateUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Start UpdateUser Action...")

	var newUser *models.NewUser

	err := json.NewDecoder(r.Body).Decode(&newUser) //Read the request message

	if err != nil {
		fmt.Fprintf(w, "Enter the correct data")
	}

	res, _ := service.UpdateUser(newUser)              //Update the user to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End UpdateUser Action...")
}

// EndPoint to delete a user
// @Summary Delete user
// @Description The actual endpoint Delete a user.
// @Tags DeleteUser
// @Accept json
// @Produce json
// @Param id path string true "User Id"
// @Success 200 {boolean} boolean
// @Router /users/es/v1/DeleteUser/{id} [DELETE]
func DeleteUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Start DeleteUser Action...")

	id := mux.Vars(r)["id"] //Read the URL Params

	res, _ := service.DeleteUser(&id)                  //Save the user to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End DeleteUser Service...")
}

// EndPoint to read a user
// @Summary Read user by ID
// @Description The actual endpoint Search a user by Id.
// @Tags ReadUserById
// @Accept json
// @Produce json
// @Param id path string true "User Id"
// @Success 200 {object} models.User
// @Router /users/es/v1/ReadUserById/{id} [GET]
func ReadUserById(w http.ResponseWriter, r *http.Request) {
	log.Println("Start ReadUser Action...")

	id := mux.Vars(r)["id"] //Read the URL Params

	res, _, _ := service.ReadUserById(&id)             //Save the user to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End ReadUserById Action...")
}
