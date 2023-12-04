package repository

import (
	"context"
	"log"
	"users-es/models"
	"users-es/utils"

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

func CreateUser(user *models.NewUser) (*models.User, error) {

	log.Println("Start CreateUser Repository...")

	var id string

	if user.Id == nil {
		id = ksuid.New().String()
	} else {
		id = *user.Id
	}

	pass, _ := utils.EncodePassword(user.UserPassword)

	user.Id = &id

	user.UserPassword = pass

	_, err := users.Doc(id).Create(ctx, user)

	if err != nil {
		log.Fatal(err)
		return nil, err
	}

	userResult, _, err := ReadUserById(user.Id) //Find agenda document to return

	log.Println("Inserted user document", userResult.Id)
	log.Println("End CreateUser Repository...")

	return userResult, err

}

func ReadUserById(id *string) (*models.User, bool, error) {

	log.Println("Start ReadUserById Repository...")
	log.Printf("id : %s", *id)

	var userModel models.User
	UserDocument, err := users.Doc(*id).Get(ctx) //Find a user document by ID

	if err != nil {
		log.Fatal(err)
		return &userModel, false, err //Return false if the agenda doesn't exists
	}

	UserDocument.DataTo(&userModel) //Transform agenda document to agenda model

	log.Println("Found a user document", userModel.Id)
	log.Println("End ReadUserById Repository")

	return &userModel, true, nil

}

// TODO: this function not work's
func ReadAllUsers(id *string) (*models.User, bool, error) {

	log.Println("Start ReadUserById Repository...")
	log.Printf("id : %s", *id)

	var userModel models.User
	UserDocument, err := users.Doc(*id).Get(ctx) //Find a user document by ID

	if err != nil {
		log.Fatal(err)
		return &userModel, false, err //Return false if the agenda doesn't exists
	}

	UserDocument.DataTo(&userModel) //Transform agenda document to agenda model

	log.Println("Found a user document", userModel.Id)
	log.Println("End ReadUserById Repository")

	return &userModel, true, nil

}

func UpdateUser(user *models.NewUser) (*models.User, error) {

	log.Println("Start UpdateUser Repository...")

	userResult, _, err := ReadUserById(user.Id)

	if err != nil {
		log.Fatal(err)
		return nil, err
	}

	if user.UserPassword != userResult.UserPassword {
		log.Print("Password Changed!")
		passEncode, _ := utils.EncodePassword(user.UserPassword)
		user.UserPassword = passEncode
	}

	_, err = users.Doc(*user.Id).Set(ctx, user)

	userResult, _, err = ReadUserById(user.Id)

	if err != nil {
		log.Fatal(err)
	}
	log.Println("Updated user document", userResult.Id)
	log.Println("End UpdateUser Repository...")

	return userResult, err

}

func DeleteUser(id *string) (bool, error) {

	log.Println("Start DeleteUser Repository...")

	_, err := users.Doc(*id).Delete(ctx)

	if err != nil {
		log.Fatal(err)
		return false, err
	}

	log.Println("Delete user document", *id)
	log.Println("End DeleteUser Repository...")

	return true, nil

}
