package actions

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"role-es/models"
	"role-es/service"

	"github.com/gorilla/mux"
)

// EndPoint to create a role
// @Summary Create role
// @Description The actual endpoint create a role and save in database.
// @Tags CreateRole
// @Accept json
// @Produce json
// @Param user body models.NewRole true "Created Role"
// @Success 200 {object} models.Role
// @Security ApiKeyAuth
// @Router /api/roles/es/v1/CreateRole [POST]
func CreateRole(w http.ResponseWriter, r *http.Request) {
	log.Println("Start CreateRole Action...")

	var newRole *models.NewRole

	err := json.NewDecoder(r.Body).Decode(&newRole) //Read the request message

	if err != nil {
		fmt.Fprintf(w, "Enter the correct data")
	}

	res, _ := service.CreateRole(newRole)              //Save the Role to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End CreateRole Action...")
}

// EndPoint to update a role
// @Summary Update role
// @Description The actual endpoint Update a role and save in database.
// @Tags UpdateRole
// @Accept json
// @Produce json
// @Param user body models.NewRole true "Update Role"
// @Success 200 {object} models.Role
// @Security ApiKeyAuth
// @Router /api/roles/es/v1/UpdateRole [PUT]
func UpdateRole(w http.ResponseWriter, r *http.Request) {
	log.Println("Start UpdateRole Action...")

	var newRole *models.NewRole

	err := json.NewDecoder(r.Body).Decode(&newRole) //Read the request message

	if err != nil {
		fmt.Fprintf(w, "Enter the correct data")
	}

	res, _ := service.UpdateRole(newRole)              //Update the Role to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End UpdateRole Action...")
}

// EndPoint to delete a role
// @Summary Delete role
// @Description The actual endpoint Delete a role.
// @Tags DeleteRole
// @Accept json
// @Produce json
// @Param id path string true "Role Id"
// @Success 200 {boolean} boolean
// @Security ApiKeyAuth
// @Router /api/roles/es/v1/DeleteRole/{id} [DELETE]
func DeleteRole(w http.ResponseWriter, r *http.Request) {
	log.Println("Start DeleteRole Action...")

	id := mux.Vars(r)["id"] //Read the URL Params

	res, _ := service.DeleteRole(&id)                  //Save the Role to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End DeleteRole Service...")
}

// EndPoint to read a role
// @Summary Read role by ID
// @Description The actual endpoint Search a role by Id.
// @Tags ReadRoleById
// @Accept json
// @Produce json
// @Param id path string true "Role Id"
// @Success 200 {object} models.Role
// @Security ApiKeyAuth
// @Router /api/roles/es/v1/ReadRoleById/{id} [GET]
func ReadRoleById(w http.ResponseWriter, r *http.Request) {
	log.Println("Start ReadRoleById Action...")

	id := mux.Vars(r)["id"] //Read the URL Params

	res, _, _ := service.ReadRoleById(&id)             //Save the Role to Database
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End ReadRoleById Action...")
}
