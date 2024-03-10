package service

import (
	"log"
	"models"
	"role-es/repository"
)

func CreateRole(role *models.NewRole) (*models.Role, error) {

	log.Println("Start CreateRole Service...")

	res, err := repository.CreateRole(role) //Save the role to Database

	if err != nil {
		log.Panic(err)
	}

	log.Println("End CreateRole Service...")

	return res, err
}

func UpdateRole(role *models.NewRole) (*models.Role, error) {
	log.Println("Start UpdateRole Service...")

	res, err := repository.UpdateRole(role) //Update the role to Database

	if err != nil {
		log.Panic(err)
	}

	log.Println("End UpdateRole Service...")
	return res, err
}

func DeleteRole(id *string) (bool, error) {
	log.Println("Start DeleteRole Service...")

	res, err := repository.DeleteRole(id) //Save the role to Database

	if err != nil {
		log.Panic(err)
	}

	log.Println("End DeleteRole Service...")

	return res, err
}

func ReadRoleById(id *string) (*models.Role, bool, error) {
	log.Println("Start ReadRole Service...")

	res, b, err := repository.ReadRoleById(id) //Save the role to Database

	if err != nil {
		log.Panic(err)
	}

	log.Println("End ReadRoleById Service...")

	return res, b, err
}

// Get all roles with pagination
// Parameters:
//
//	size (*int): size of page to retrieve.
//	startAfterKey (*string): id of last document.
//
// Returns:
//
//	*models.RoleFilter
//	error
func ReadAllRolesPaginate(size *int, startAfterKey *string) (*models.RoleFilter, error) {
	log.Println("Start ReadAllRolesPaginate Service...")
	log.Println("End ReadAllRolesPaginate Service...")
	return repository.ReadAllRolesPaginate(size, startAfterKey)
}

// Get all roles without pagination
//
// Returns:
//
//	*models.Role
//	error
func GetAllRoles() ([]*models.Role, error) {
	log.Println("Start GetAllRoles Service...")
	log.Println("End GetAllRoles Service...")
	return repository.GetAllRoles()
}
