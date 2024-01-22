package repository

import (
	"context"
	"fileServer-us/models"
	"fileServer-us/utils"
	"log"
	"net/url"
	"path/filepath"

	"cloud.google.com/go/storage"
	"github.com/segmentio/ksuid"
	"google.golang.org/api/option"
)

const (
	DEFAULT_BUCKET = "selling-system-cdc02.appspot.com"
)

var client, ctx = initFirestore()

// This function is responsible for gettin information of google firestore.
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
	id := ksuid.New().String()

	fileModel.Id = &id
	fileModel.FileExtension = filepath.Ext(fileModel.FileName)
	fileModel.FileType = utils.FileType(fileModel.FileType)
	fileModel.FileBucket = DEFAULT_BUCKET

	bucket := client.Bucket(fileModel.FileBucket)
	wc := bucket.Object(fileModel.FileFolder + "/" + fileModel.FileUser + fileModel.FileExtension).NewWriter(ctx)
	wc.ContentType = fileModel.FileType

	// Copiar los bytes al escritor en el bucket
	if _, err := wc.Write(fileModel.File); err != nil {
		return nil, err
	}

	// Confirmar la escritura
	if err := wc.Close(); err != nil {
		return nil, err
	}

	route := url.QueryEscape(fileModel.FileFolder + "/" + fileModel.FileUser)
	fileModel.URL = "https://firebasestorage.googleapis.com/v0/b/" + DEFAULT_BUCKET + "/o/" + route + fileModel.FileExtension

	log.Println("End UploadFile Repository...")
	return fileModel, nil

}
