package repository

import (
	"context"
	"fileServer-us/models"
	"fileServer-us/utils"
	"log"
	"path/filepath"

	"cloud.google.com/go/storage"
	"github.com/segmentio/ksuid"
	"google.golang.org/api/option"
)

const (
	DEFAULT_BUCKET = "selling-system-cdc02.appspot.com"
)

var client, ctx = initFirestore()

// This function is responsible for getting information of google firestore.
func initFirestore() (*storage.Client, context.Context) {

	defer func() {
		recuperado := recover()
		if recuperado != nil {
			log.Println("Server error initFirestore")
		}
	}()

	ctx := context.Background()
	sa := option.WithCredentialsFile("./KeyService.json")
	app, err := storage.NewClient(ctx, sa)
	if err != nil {
		log.Panic(err)
	}

	return app, ctx
}

func UploadFile(fileModel *models.NewFile) (*models.NewFile, error) {

	log.Println("Start UploadFile Repository...")
	log.Printf("Cargando archivo: %s", fileModel.FileName)

	// Generate Data file.
	id := ksuid.New().String()
	fileModel.Id = &id
	fileModel.FileExtension = filepath.Ext(fileModel.FileName)
	fileModel.FileType = utils.FileType(fileModel.FileExtension)
	fileModel.FileBucket = DEFAULT_BUCKET

	// Init storage client
	bucket := client.Bucket(fileModel.FileBucket) // default bucket
	filePath := fileModel.FileFolder + "/" + fileModel.Owner + fileModel.FileExtension
	wc := bucket.Object(filePath).NewWriter(ctx) // Path to save file.
	wc.ContentType = fileModel.FileType          // Content type of file.

	// Copy bytes to writer in the bucket.
	if _, err := wc.Write(fileModel.File); err != nil {
		return nil, err
	}

	// Confirm writer.
	if err := wc.Close(); err != nil {
		return nil, err
	}

	// TODO: It's possible this generate some errors.
	fileModel.URL = filePath

	log.Println("End UploadFile Repository...")
	return fileModel, nil

}
