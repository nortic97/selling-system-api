package actions

import (
	"encoding/json"
	"fmt"
	"log"
	"models"
	"net/http"
	"strconv"

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
// @Success 201 {object} models.User
// @Router /api/users/es/v1/CreateUser [POST]
func CreateUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Start CreateUser Action...")

	var newUser *models.NewUser

	err := json.NewDecoder(r.Body).Decode(&newUser) //Read the request message

	if err != nil {
		fmt.Fprintf(w, "Enter the correct data")
	}

	res, _ := service.CreateUser(newUser)              //Save the user to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusCreated)                  //Write the status code
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
// @Security ApiKeyAuth
// @Router /api/users/es/v1/UpdateUser [PUT]
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
// @Security ApiKeyAuth
// @Router /api/users/es/v1/DeleteUser/{id} [DELETE]
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
// @Security ApiKeyAuth
// @Router /api/users/es/v1/ReadUserById/{id} [GET]
func ReadUserById(w http.ResponseWriter, r *http.Request) {
	log.Println("Start ReadUser Action...")

	id := mux.Vars(r)["id"] //Read the URL Params

	res, _, _ := service.ReadUserById(&id)             //read the user to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End ReadUserById Action...")
}

// EndPoint to read all user by page
// @Summary Read all users by page
// @Description The actual endpoint Search a users by page.
// @Tags ReadAllUsersPaginate
// @Accept json
// @Produce json
// @Param size path int true "size"
// @Param startAfterKey path string true "send null string if is empty"
// @Success 200 {object} models.UserFilter
// @Security ApiKeyAuth
// @Router /api/users/es/v1/ReadAllUsersPaginate/{size}/{startAfterKey} [GET]
func ReadAllUsersPaginate(w http.ResponseWriter, r *http.Request) {
	log.Println("Start ReadAllUsersPaginate Action...")

	//Read the URL Params
	sizeStr := mux.Vars(r)["size"]
	startAfterKey := mux.Vars(r)["startAfterKey"]

	size, err := strconv.Atoi(sizeStr)
	if err != nil {
		// Check if have error
		log.Panic("Error converting size to int")
		return
	}

	var key *string

	if startAfterKey == "null" {
		key = nil
	} else {
		key = &startAfterKey
	}

	res, err := service.ReadAllUsersPaginate(&size, key) //read user to Database

	if err != nil {
		log.Panic(err)
	}

	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End ReadAllUsersPaginate Action...")
}
