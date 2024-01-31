package models

type Role struct {
	Id          string     `json:"Id"`
	RoleName    string     `json:"RoleName"`
	Description string     `json:"Description"`
	Menu        []Menu     `json:"Menu"`
	Resources   []Resource `json:"Resources"`
}

type NewRole struct {
	Id          *string    `json:"Id"`
	RoleName    string     `json:"RoleName"`
	Description string     `json:"Description"`
	Menu        []Menu     `json:"Menu"`
	Resources   []Resource `json:"Resources"`
}

type Menu struct {
	Id             string     `json:"Id"`
	MenuName       string     `json:"MenuName"`
	Icon           string     `json:"Icon"`
	MenuPosition   int        `json:"MenuPosition"`
	MenuHasSubMenu bool       `json:"MenuHasSubMenu"`
	MenuUrl        string     `json:"MenuUrl"`
	MenuSubMenu    []SubMenu  `json:"MenuSubMenu"`
	Actions        []Resource `json:"Actions"`
}

type SubMenu struct {
	Id              string     `json:"Id"`
	SubMenuName     string     `json:"SubMenuName"`
	SubMenuPosition int        `json:"SubMenuPosition"`
	SubMenuUrl      string     `json:"SubMenuUrl"`
	Actions         []Resource `json:"Actions"`
}

type Resource struct {
	Id     string `json:"Id"`
	Value  string `json:"Value"`
	Url    string `json:"Url"`
	Action string `json:"Action"`
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
