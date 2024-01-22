package service

import (
	"fileServer-us/models"
	"fileServer-us/repository"
	"log"
)

func UploadFile(fileModel *models.NewFile) (*models.NewFile, error) {
	log.Println("Start UploadFile Service...")
	log.Println("End UploadFile Service...")
	return repository.UploadFile(fileModel)
}
