package actions

import (
	"encoding/json"
	"fileServer-us/models"
	"fileServer-us/service"
	"fmt"
	"io"
	"log"
	"net/http"
)

// EndPoint to UploadFile
// @Summary Upload File
// @Description The actual endpoint Upload File to server.
// @Tags UploadFile
// @Accept mpfd
// @Produce json
// @Param file formData file true "File"
// @Param folder formData string true "Folder"
// @Param user formData string true "owner"
// @Success 200 {object} models.NewFile
// @Security ApiKeyAuth
// @Router /api/fileServer/us/v1/UploadFile [POST]
func UploadFile(w http.ResponseWriter, r *http.Request) {

	log.Println("Start UploadFile Action...")

	var fileModel *models.NewFile = new(models.NewFile) // New is necessary for not errors.

	// Get form.
	file, handler, err := r.FormFile("file")
	fileModel.FileFolder = r.FormValue("folder")
	fileModel.Owner = r.FormValue("user")

	if err != nil {
		http.Error(w, fmt.Sprintf("Error to get file: %v", err), http.StatusBadRequest)
		return
	}
	defer file.Close()

	// Read file.
	fileContent, err := io.ReadAll(file)
	if err != nil {
		http.Error(w, fmt.Sprintf("Error to read file: %s", err), http.StatusInternalServerError)
		return
	}

	fileModel.FileName = handler.Filename // Get original file name.

	fileModel.File = make([]byte, len(fileContent)) // make slice empty of bytes.

	copy(fileModel.File, fileContent) // Copy bytes in model.

	res, err := service.UploadFile(fileModel) //Save the File to storage
	if err != nil {
		log.Panicln(err)
	}

	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End UploadFile Action...")

}
