package models

type User struct {
	Id           string `json:"Id"`
	UserName     string `json:"UserName"`
	UserPassword string `json:"UserPassword"`
	UserRoleId   string `json:"UserRoleId"`
}

type NewUser struct {
	Id           *string `json:"Id"`
	UserName     string  `json:"UserName"`
	UserPassword string  `json:"UserPassword"`
	UserRoleId   string  `json:"UserRoleId"`
}
