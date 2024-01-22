package repository

import (
	"configPlatform-us/models"
	"context"
	"log"

	"cloud.google.com/go/firestore"
	firebase "firebase.google.com/go"
	"google.golang.org/api/option"
)

var configPlatform, roles, users, ctx = initFirestore()

func initFirestore() (*firestore.CollectionRef, *firestore.CollectionRef, *firestore.CollectionRef, context.Context) {

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

	configPlatform := client.Collection("configPlatform")
	role := client.Collection("roles")
	users := client.Collection("users")

	return configPlatform, role, users, ctx
}

func InitializeMenu(initializeMenu *models.InitMenu) {

	log.Println("Start initializeMenu Repository...")

	menuFind, _ := configPlatform.Doc(initializeMenu.Id).Get(ctx)

	var menu *models.InitMenu

	menuFind.DataTo(&menu)

	if menu != nil {
		log.Println("Document already exists...")
		configPlatform.Doc(initializeMenu.Id).Delete(ctx)
	}

	_, err := configPlatform.Doc(initializeMenu.Id).Create(ctx, initializeMenu)
	if err != nil {
		log.Panic(err)
	}

	log.Println("Inserted configPlatform document", initializeMenu.Id)
	log.Println("End initializeMenu Repository...")

}

func InitializeRole(role *models.Role) {

	log.Println("Start InitializeRole Repository...")

	roleFind, _ := roles.Doc(role.Id).Get(ctx)

	var roleAux *models.Role

	roleFind.DataTo(&roleAux)

	if roleAux != nil {
		log.Println("Document already exists...")
		roles.Doc(role.Id).Delete(ctx)
	}

	_, err := roles.Doc(role.Id).Create(ctx, role)

	if err != nil {
		log.Panic(err)
	}

	log.Println("Inserted roles document", role.Id)
	log.Println("End InitializeRole Repository...")

}

func InitializeUser(user *models.User) {

	log.Println("Start InitializeUser Repository...")

	userFind, _ := users.Doc(user.Id).Get(ctx)

	var userAux *models.User

	userFind.DataTo(&userAux)

	if userAux != nil {
		log.Println("Document already exists...")
		users.Doc(user.Id).Delete(ctx)
	}

	_, err := users.Doc(user.Id).Create(ctx, user)

	if err != nil {
		log.Panic(err)
	}

	log.Println("Inserted users document", user.Id)
	log.Println("End InitializeUser Repository...")

}
