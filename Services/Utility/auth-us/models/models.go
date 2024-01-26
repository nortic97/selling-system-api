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

type Role struct {
	Id          string `json:"Id"`
	RoleName    string `json:"RoleName"`
	Description string `json:"Description"`
	Menu        []Menu `json:"Menu"`
}

type Menu struct {
	Id             string    `json:"Id"`
	MenuName       string    `json:"MenuName"`
	Icon           string    `json:"Icon"`
	MenuPosition   int       `json:"MenuPosition"`
	MenuHasSubMenu bool      `json:"MenuHasSubMenu"`
	MenuUrl        string    `json:"MenuUrl"`
	MenuActions    []Action  `json:"MenuActions"`
	MenuSubMenu    []SubMenu `json:"MenuSubMenu"`
}

type SubMenu struct {
	Id              string   `json:"Id"`
	SubMenuName     string   `json:"SubMenuName"`
	SubMenuPosition int      `json:"SubMenuPosition"`
	SubMenuActions  []Action `json:"SubMenuActions"`
	SubMenuUrl      string   `json:"SubMenuUrl"`
}

type Action struct {
	Id           string `json:"Id"`
	Value        string `json:"Value"`
	ApiUrlAction string `json:"ApiUrlAction"`
	Action       string `json:"Action"`
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
