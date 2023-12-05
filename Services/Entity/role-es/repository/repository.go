package repository

import (
	"context"
	"log"
	"role-es/models"

	"cloud.google.com/go/firestore"
	firebase "firebase.google.com/go"
	"github.com/segmentio/ksuid"
	"google.golang.org/api/option"
)

var roles, ctx = initFirestore()

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

	return client.Collection("roles"), ctx
}

func CreateRole(role *models.NewRole) (*models.Role, error) {

	log.Println("Start CreateRole Repository...")

	defer func() {
		recuperado := recover()
		if recuperado != nil {
			log.Println("Recovered Error CreateRole")
		}
	}()

	var id string

	if role.Id == nil {
		id = ksuid.New().String()
	} else {
		id = *role.Id
	}

	role.Id = &id

	_, err := roles.Doc(id).Create(ctx, role)

	if err != nil {
		log.Panic(err)
		return nil, err
	}

	roleResult, _, err := ReadRoleById(role.Id) //Find agenda document to return

	if err != nil {
		log.Panic(err)
		return nil, err
	}

	log.Println("Inserted role document", roleResult.Id)
	log.Println("End CreateRole Repository...")

	return roleResult, err

}

func ReadRoleById(id *string) (*models.Role, bool, error) {

	log.Println("Start ReadRoleById Repository...")
	log.Printf("id : %s", *id)

	defer func() {
		recuperado := recover()
		if recuperado != nil {
			log.Println("Recovered Error ReadRoleById")
		}
	}()

	var userModel models.Role
	RoleDocument, err := roles.Doc(*id).Get(ctx) //Find a role document by ID

	if err != nil {
		log.Panic(err)
		return &userModel, false, err //Return false if the agenda doesn't exists
	}

	RoleDocument.DataTo(&userModel) //Transform agenda document to agenda model

	log.Println("Found a role document", userModel.Id)
	log.Println("End ReadRoleById Repository")

	return &userModel, true, nil

}

// TODO: this function not work's
func ReadAllRoles(id *string) (*models.Role, bool, error) {

	log.Println("Start ReadRoleById Repository...")
	log.Printf("id : %s", *id)

	var userModel models.Role
	RoleDocument, err := roles.Doc(*id).Get(ctx) //Find a role document by ID

	if err != nil {
		log.Panic(err)
		return &userModel, false, err //Return false if the agenda doesn't exists
	}

	RoleDocument.DataTo(&userModel) //Transform agenda document to agenda model

	log.Println("Found a role document", userModel.Id)
	log.Println("End ReadRoleById Repository")

	return &userModel, true, nil

}

func UpdateRole(role *models.NewRole) (*models.Role, error) {

	log.Println("Start UpdateRole Repository...")

	defer func() {
		recuperado := recover()
		if recuperado != nil {
			log.Println("Recovered Error UpdateRole")
		}
	}()

	roleResult, _, err := ReadRoleById(role.Id)

	if err != nil {
		log.Panic(err)
		return nil, err
	}

	_, err = roles.Doc(*role.Id).Set(ctx, role)

	if err != nil {
		log.Panic(err)
	}

	roleResult, _, err = ReadRoleById(role.Id)

	if err != nil {
		log.Panic(err)
	}
	log.Println("Updated role document", roleResult.Id)
	log.Println("End UpdateRole Repository...")

	return roleResult, err

}

func DeleteRole(id *string) (bool, error) {

	log.Println("Start DeleteRole Repository...")

	defer func() {
		recuperado := recover()
		if recuperado != nil {
			log.Println("Recovered Error DeleteRole")
		}
	}()

	_, err := roles.Doc(*id).Delete(ctx)

	if err != nil {
		log.Panic(err)
		return false, err
	}

	log.Println("Delete role document", *id)
	log.Println("End DeleteRole Repository...")

	return true, nil

}
