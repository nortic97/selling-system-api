package models

import "github.com/golang-jwt/jwt"

type Login struct {
	UserEmail    string `json:"UserEmail"`
	UserPassword string `json:"UserPassword"`
}

type User struct {
	Id           string `json:"Id"`
	UserName     string `json:"UserName"`
	UserEmail    string `json:"UserEmail"`
	UserPassword string `json:"UserPassword"`
	UserRoleId   string `json:"UserRoleId"`
	UserImageUrl string `json:"UserImageUrl"`
}

type TokenClaims struct {
	Id           string `json:"Id"`
	UserName     string `json:"UserName"`
	UserEmail    string `json:"UserEmail"`
	UserPassword string `json:"UserPassword"`
	UserRoleId   string `json:"UserRoleId"`
	UserImageUrl string `json:"UserImageUrl"`
	jwt.StandardClaims
}

type Token struct {
	Token        string `json:"access_token"`
	RefreshToken string `json:"refresh_token"`
	Type         string `json:"token_type"`
}

type Health struct {
	Status      string
	Environment string
}
