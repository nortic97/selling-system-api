package models

type Role struct {
	Id          string `json:"Id"`
	RoleName    string `json:"RoleName"`
	Description string `json:"Description"`
	Menu        []Menu `json:"Menu"`
}

type NewRole struct {
	Id          *string `json:"Id"`
	RoleName    string  `json:"RoleName"`
	Description string  `json:"Description"`
	Menu        []Menu  `json:"Menu"`
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

type RoleFilter struct {
	Roles         []Role `json:"Roles"`
	Size          int    `json:"Size"`
	StartAfterKey string `json:"startAfterKey"`
	TotalData     int    `json:"TotalData"`
}

type Health struct {
	Status      string
	Environment string
}
