package service

import (
	"auth-us/models"
	"auth-us/repository"
	"log"
	"time"

	"github.com/golang-jwt/jwt"
)

func Login(login *models.Login) ([]*models.User, bool, error) {
	log.Println("Start Login Service...")
	log.Println("End Login Service...")
	return repository.Login(login)
}

func GenerateToken(info *models.User) models.Token {

	log.Println("Start GenerateToken Service...")

	// Recover for errors
	defer func() {
		recuperado := recover()
		if recuperado != nil {
			log.Println("Error Recover")
		}
	}()

	// Create the claims
	claims := models.TokenClaims{
		Id:           info.Id,
		UserName:     info.UserName,
		UserEmail:    info.UserEmail,
		UserPassword: info.UserPassword,
		UserRoleId:   info.UserRoleId,
		UserImageUrl: info.UserImageUrl,
		StandardClaims: jwt.StandardClaims{
			ExpiresAt: time.Now().Add(time.Hour * 8).Unix(),
			Issuer:    info.UserName,
		},
	}

	token := jwt.NewWithClaims(jwt.SigningMethodHS256, claims) // Create token without sign
	mySecret := "P05d4sh"                                      // Secret sign for token
	signedToken, err := token.SignedString([]byte(mySecret))   // Sign token with

	if err != nil {
		log.Panicln(err)
	}

	// Create refresh token
	refreshToken := jwt.New(jwt.SigningMethodHS256)
	refreshTokenClaims := refreshToken.Claims.(jwt.MapClaims)
	refreshTokenClaims["sub"] = 1
	refreshTokenClaims["exp"] = time.Now().Add(time.Hour * 24).Unix()

	rt, err := refreshToken.SignedString([]byte("P05d4sh")) // Sign refresh token

	if err != nil {
		log.Panicln(err)
	}

	// Generate token model
	tokenModel := models.Token{
		Token:        signedToken,
		RefreshToken: rt,
		Type:         "Bearer",
	}

	log.Println("End GenerateToken Service...")
	return tokenModel
}
