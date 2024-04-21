package repository

import (
	"log"
	"users-es/config"
	"users-es/models"
)

func CreateUser(user *models.User) (*models.User, error) {
	log.Println("Start CreateUser Repository...")

	// Crear el usuario en la base de datos
	result := config.DB.Create(&user)
	if result.Error != nil {
		log.Printf("\n\n repository/CreateUser \n\n %v \n\n", result.Error)
		return nil, result.Error
	}

	log.Println("Usuario insertado:", user.ID)
	log.Printf("Registros insertados: %d", result.RowsAffected)
	log.Println("Fin de CreateUser Repository...")

	return user, nil
}
