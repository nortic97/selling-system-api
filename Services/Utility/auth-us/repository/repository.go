package repository

import (
	"auth-us/models"
	"auth-us/utils"
	"context"
	"log"

	"cloud.google.com/go/firestore"
	firebase "firebase.google.com/go"
	"google.golang.org/api/iterator"
	"google.golang.org/api/option"
)

var users, ctx = initFirestore()

func initFirestore() (*firestore.CollectionRef, context.Context) {

	defer func() {
		recuperado := recover()
		if recuperado != nil {
			log.Println("Server error initFirestore")
		}
	}()

	ctx := context.Background()
	sa := option.WithCredentialsFile("./KeyService.json")
	app, err := firebase.NewApp(ctx, nil, sa)

	if err != nil {
		log.Panic(err)
	}

	client, err := app.Firestore(ctx)

	if err != nil {
		log.Panic(err)
	}

	return client.Collection("users"), ctx
}

func Login(login *models.Login) ([]*models.User, bool, error) {

	log.Println("Start Login Repository...")

	var usersList []*models.User

	defer func() {
		recuperado := recover()
		if recuperado != nil {
			log.Println("Recovered Error Login")
		}
	}()

	query := users.Where("UserName", "==", login.UserName)

	it := query.Documents(ctx)

	for {

		doc, err := it.Next()

		if err == iterator.Done {
			break
		}

		if err != nil {
			return nil, false, err
		}

		var userData *models.User
		doc.DataTo(&userData)

		if !utils.CheckPasswordHash(login.UserPassword, userData.UserPassword) {
			return nil, true, nil
		}

		usersList = append(usersList, userData)

	}

	log.Println("End Login Repository...")

	if len(usersList) == 0 {
		return nil, false, nil
	} else {
		return usersList, true, nil
	}

}
