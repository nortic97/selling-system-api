package models

type User struct {
	Id           string `json:"Id"`
	UserName     string `json:"UserName"`
	UserEmail    string `json:"UserEmail"`
	UserPassword string `json:"UserPassword"`
	UserRoleId   string `json:"UserRoleId"`
	UserImageUrl string `json:"UserImageUrl"`
}

type NewUser struct {
	Id           *string `json:"Id"`
	UserName     string  `json:"UserName"`
	UserEmail    string  `json:"UserEmail"`
	UserPassword string  `json:"UserPassword"`
	UserRoleId   string  `json:"UserRoleId"`
	UserImageUrl string  `json:"UserImageUrl"`
}
