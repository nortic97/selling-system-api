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

var users, roles, ctx = initFirestore()

// This function is responsible for gettin information of google firestore.
func initFirestore() (*firestore.CollectionRef, *firestore.CollectionRef, context.Context) {

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

	return client.Collection("users"), client.Collection("roles"), ctx
}

// "Login" is a function to confirm that the account exists.
func Login(login *models.Login) ([]*models.User, bool, error) {

	log.Println("Start Login Repository...")

	var usersList []*models.User

	defer func() {
		recuperado := recover()
		if recuperado != nil {
			log.Println("Recovered Error Login")
		}
	}()

	query := users.Where("UserEmail", "==", login.UserEmail)

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

		// Confirm the password for user is correct.
		if !utils.CheckPasswordHash(login.UserPassword, userData.UserPassword) {
			return nil, true, nil // Return the account exist but the pass is incorrect.
		}

		usersList = append(usersList, userData) // Add data about the account to a list.

	}

	log.Println("End Login Repository...")

	if len(usersList) == 0 {
		return nil, false, nil // Return the account not exist.
	} else {
		return usersList, true, nil // Return the account exist.
	}

}

func GetRoleById(id *string) (*models.Role, error) {

	log.Println("Start GetRoleById Repository")

	roleData, err := roles.Doc(*id).Get(ctx)
	if err != nil {
		return nil, err
	}
	var role *models.Role
	roleData.DataTo(&role)
	log.Println("End GetRoleById Repository")
	return role, nil

}
