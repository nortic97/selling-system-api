package service

import (
	"log"
	"users-es/models"
	"users-es/repository"
)

func CreateUser(user *models.NewUser) (*models.User, error) {

	log.Println("Start CreateUser Service...")

	res, err := repository.CreateUser(user) //Save the user to Database

	if err != nil {
		log.Panic(err)
	}

	log.Println("End CreateUser Service...")

	return res, err
}

func UpdateUser(user *models.NewUser) (*models.User, error) {
	log.Println("Start UpdateUser Service...")

	res, err := repository.UpdateUser(user) //Update the user to Database

	if err != nil {
		log.Panic(err)
	}

	log.Println("End UpdateUser Service...")
	return res, err
}

func DeleteUser(id *string) (bool, error) {
	log.Println("Start DeleteUser Service...")

	res, err := repository.DeleteUser(id) //Save the user to Database

	if err != nil {
		log.Panic(err)
	}

	log.Println("End DeleteUser Service...")

	return res, err
}

func ReadUserById(id *string) (*models.User, bool, error) {
	log.Println("Start ReadUser Service...")

	res, b, err := repository.ReadUserById(id) //Save the user to Database

	if err != nil {
		log.Panic(err)
	}

	log.Println("End ReadUserById Service...")

	return res, b, err
}

func ReadAllUsers(size *int, startAfterKey *string) (*models.UserFilter, error) {
	log.Println("End ReadAllUsers Service...")
	log.Println("End ReadAllUsers Service...")
	return repository.ReadAllUsers(size, startAfterKey)
}
