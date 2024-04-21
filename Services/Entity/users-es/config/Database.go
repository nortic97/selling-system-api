package config

import (
	"fmt"
	"log"
	"users-es/models"
	"users-es/utils"

	"gorm.io/driver/postgres" // Importar el driver de PostgreSQL para GORM
	"gorm.io/gorm"
)

var (
	ENV                                             = utils.Environment()
	PORT                                            = utils.PORTS()
	DB                                              *gorm.DB
	DB_HOST, DB_PORT, DB_USER, DB_PASSWORD, DB_NAME = utils.DataBaseConf()
)

func ConnectDB() {
	dsn := fmt.Sprintf("host=%s port=%s user=%s password=%s dbname=%s sslmode=disable",
		DB_HOST,     // Reemplazar con la dirección del servidor de la base de datos
		DB_PORT,     // Reemplazar con el puerto de la base de datos
		DB_USER,     // Reemplazar con el nombre de usuario de la base de datos
		DB_PASSWORD, // Reemplazar con la contraseña de la base de datos
		DB_NAME,     // Reemplazar con el nombre de la base de datos
	)

	database, err := gorm.Open(postgres.Open(dsn))
	if err != nil {
		log.Fatal("Error al conectar con la base de datos:", err)
	}

	DB = database
	log.Println("Conexión a la base de datos establecida correctamente")

	AutoMigrate()
}

func CloseDB() error {
	sqlDB, err := DB.DB()
	if err != nil {
		return err
	}
	return sqlDB.Close()
}

func AutoMigrate() {
	err := DB.AutoMigrate(&models.User{}) // Replace with your struct models
	if err != nil {
		log.Fatal("Error auto-migrating database:", err)
	}

	log.Println("Database schema auto-migrated successfully")
}
