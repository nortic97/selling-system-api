package models

type Role struct {
	Id          string        `json:"Id"`
	RoleName    string        `json:"RoleName"`
	Description string        `json:"Description"`
	Permissions []Permissions `json:"Permissions"`
}

type NewRole struct {
	Id          *string       `json:"Id"`
	RoleName    string        `json:"RoleName"`
	Description string        `json:"Description"`
	Permissions []Permissions `json:"Permissions"`
}

type Permissions struct {
	PermissionName       string          `json:"PermissionName"`
	PermissionPosition   int             `json:"PermissionPosition"`
	PermissionHasSubMenu bool            `json:"PermissionHasSubMenu"`
	PermissionSubMenu    []SubPermission `json:"PermissionSubMenu"`
	PermissionUrl        string          `json:"PermissionUrl"`
}

type SubPermission struct {
	SubPermissionName string `json:"SubPermissionName"`
	SubPermissionUrl  string `json:"SubPermissionUrl"`
}

type Health struct {
	Status string
	Env    string
}
