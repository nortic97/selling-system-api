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
	MenuName       string    `json:"PermissionName"`
	MenuPosition   int       `json:"PermissionPosition"`
	MenuHasSubMenu bool      `json:"PermissionHasSubMenu"`
	MenuUrl        string    `json:"PermissionUrl"`
	MenuActions    []string  `json:"MenuAction"`
	MenuSubMenu    []SubMenu `json:"MenuSubMenu"`
}

type SubMenu struct {
	Id             string   `json:"Id"`
	SubMenuName    string   `json:"SubMenuName"`
	SubMenuActions []string `json:"SubMenuActions"`
	SubMenuUrl     string   `json:"SubMenuUrl"`
}

type Health struct {
	Status      string
	Environment string
}
