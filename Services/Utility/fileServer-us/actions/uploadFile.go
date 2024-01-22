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

func UploadFile(w http.ResponseWriter, r *http.Request) {

	log.Println("Start UploadFile Action...")

	var fileModel *models.NewFile = new(models.NewFile) // New is necessary for not errors.

	// Obtiene el archivo del formulario
	file, handler, err := r.FormFile("file")
	fileModel.FileFolder = r.FormValue("folder")
	fileModel.FileUser = r.FormValue("user")

	if err != nil {
		http.Error(w, fmt.Sprintf("Error al obtener el archivo del formulario: %v", err), http.StatusBadRequest)
		return
	}
	defer file.Close()

	// Leemos el contenido del archivo
	fileContent, err := io.ReadAll(file)
	if err != nil {
		http.Error(w, fmt.Sprintf("Error al leer el contenido del archivo: %s", err), http.StatusInternalServerError)
		return
	}

	fileModel.FileName = handler.Filename

	// Crear un slice de bytes de destino con el mismo tamaño que el origen
	fileModel.File = make([]byte, len(fileContent))

	copy(fileModel.File, fileContent)

	res, err := service.UploadFile(fileModel)
	if err != nil {
		log.Panicln(err)
	} //Save the File to storage
	w.Header().Set("content-Type", "application/json") //Set content type
	w.WriteHeader(http.StatusOK)                       //Write the status code 201 Created
	json.NewEncoder(w).Encode(res)
	log.Println("End UploadFile Action...")

}
