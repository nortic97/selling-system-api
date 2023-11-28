package models

type User struct {
	Id           string `json:"id"`
	UserName     string `json:"user_name"`
	UserPassword string `json:"user_password"`
	UserRoleId   string `json:"user_role_id"`
}

type NewUser struct {
	Id           *string `json:"id"`
	UserName     string  `json:"user_name"`
	UserPassword string  `json:"user_password"`
	UserRoleId   string  `json:"user_role_id"`
}
