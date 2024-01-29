package models

type User struct {
	Id           string `json:"Id"`
	UserName     string `json:"UserName"`
	UserEmail    string `json:"UserEmail"`
	UserPassword string `json:"UserPassword"`
	UserRoleId   string `json:"UserRoleId"`
	UserImageUrl string `json:"UserImageUrl"`
}

type UserFilter struct {
	Users         []User `json:"Users"`
	Size          int    `json:"Size"`
	StartAfterKey string `json:"startAfterKey"`
	TotalData     int    `json:"TotalData"`
}

type NewUser struct {
	Id           *string `json:"Id"`
	UserName     string  `json:"UserName"`
	UserEmail    string  `json:"UserEmail"`
	UserPassword string  `json:"UserPassword"`
	UserRoleId   string  `json:"UserRoleId"`
	UserImageUrl string  `json:"UserImageUrl"`
}

type Health struct {
	Status      string
	Environment string
}
