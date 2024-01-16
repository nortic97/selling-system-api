package service

import (
	"auth-us/models"
	"auth-us/repository"
	"log"

	"github.com/golang-jwt/jwt"
)

func Login(login *models.Login) ([]*models.User, bool, error) {
	log.Println("Start Login Service...")
	log.Println("End Login Service...")
	return repository.Login(login)
}

func GenerateToken(info *models.User) models.Token {

	log.Println("Start GenerateToken Service...")

	// Create the claims
	claims := models.TokenClaims{
		Id:           info.Id,
		UserName:     info.UserName,
		UserEmail:    info.UserEmail,
		UserPassword: info.UserPassword,
		UserRoleId:   info.UserRoleId,
		UserImageUrl: info.UserImageUrl,
		StandardClaims: jwt.StandardClaims{
			ExpiresAt: 15000,
			Issuer:    info.UserName,
		},
	}

	token := jwt.NewWithClaims(jwt.SigningMethodHS256, claims)
	mySecret := "P05d4sh*"
	signedToken, err := token.SignedString([]byte(mySecret))

	if err != nil {
		log.Panicln(err)
	}

	tokenModel := models.Token{
		Token:        signedToken,
		RefreshToken: "",
		Type:         "Bearer",
	}
	log.Println("End GenerateToken Service...")
	return tokenModel
}
