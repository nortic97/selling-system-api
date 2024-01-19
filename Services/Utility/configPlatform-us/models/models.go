package models

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

type InitMenu struct {
	Id    string `json:"id"`
	Menus []Menu `json:"menus"`
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

type Health struct {
	Status      string
	Environment string
}
