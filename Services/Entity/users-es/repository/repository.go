package repository

import (
	"context"
	"log"
	"users-es/models"

	"cloud.google.com/go/firestore"
	firebase "firebase.google.com/go"
	"github.com/segmentio/ksuid"
	"google.golang.org/api/option"
)

var users, ctx = initFirestore()

func initFirestore() (*firestore.CollectionRef, context.Context) {

	ctx := context.Background()
	sa := option.WithCredentialsFile("./KeyService.json")
	app, err := firebase.NewApp(ctx, nil, sa)

	if err != nil {
		log.Fatalln(err)
	}

	client, err := app.Firestore(ctx)

	if err != nil {
		log.Fatalln(err)
	}

	return client.Collection("users"), ctx
}

func AddUser(user *models.NewUser) (*models.User, error) {

	log.Println("Start AddUser Repository...")

	id := ksuid.New().String()

	user.Id = &id

	_, err := users.Doc(id).Create(ctx, user)

	if err != nil {
		log.Fatal(err)
		return nil, err
	}

	userResult, _, err := GetUserById(user.Id) //Find agenda document to return

	log.Println("Inserted user document", userResult.Id)
	log.Println("End AddUser Repository...")

	return userResult, err

}

func GetUserById(id *string) (*models.User, bool, error) {

	log.Println("Start GetUserById Repository...")
	log.Printf("id : %s", *id)

	var userModel models.User
	UserDocument, err := users.Doc(*id).Get(ctx) //Find a user document by ID

	if err != nil {
		log.Fatal(err)
		return &userModel, false, err //Return false if the agenda doesn't exists
	}

	UserDocument.DataTo(&userModel) //Transform agenda document to agenda model

	log.Println("Found a user document", userModel.Id)
	log.Println("End GetUserById Repository")

	return &userModel, true, nil

}
