package service

import (
	"fileServer-us/models"
	"fileServer-us/repository"
	"log"
)

// Service to upload file at storage.
//
// Parameters:
//
//	fileModel (*models.NewFile): data about file.
//
// Returns:
//
//	*models.NewFile : Data to access file saved.
//	error: Posible Errors.
func UploadFile(fileModel *models.NewFile) (*models.NewFile, error) {
	log.Println("Start UploadFile Service...")
	log.Println("End UploadFile Service...")
	return repository.UploadFile(fileModel)
}
