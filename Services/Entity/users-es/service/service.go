package service

import (
	"log"
	"users-es/models"
	"users-es/repository"
	"users-es/utils"

	"github.com/google/uuid"
)

func CreateUser(user *models.User) (*models.User, error) {

	log.Println("Start CreateUser Service...")

	// Generar un nuevo ID si no se proporciona uno
	if user.ID == nil {
		id := uuid.New()
		user.ID = &id
	}

	// Codificar la contraseña antes de almacenarla
	pass, err := utils.EncodePassword(&user.Password)
	if err != nil {
		log.Printf("\n\n repository/CreateUser \n\n %v \n\n", err)
		return nil, err
	}
	user.Password = pass

	res, err := repository.CreateUser(user) //Save the user to Database

	log.Println("End CreateUser Service...")

	return res, err
}
