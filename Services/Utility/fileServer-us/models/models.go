package models

type NewFile struct {
	Id            *string `json:"Id"`
	FileType      string  `json:"FileType"`
	FileName      string  `json:"FileName"`
	FileFolder    string  `json:"FileFolder"`
	FileExtension string  `json:"FileExtension"`
	File          []byte  `json:"File"`
	FileUser      string  `json:"FileUser"`
	FileBucket    string  `json:"FileBucket"`
	URLIsPublic   bool    `json:"UrlIsPublic"`
	URL           string  `json:"Url"`
}

type Health struct {
	Status      string
	Environment string
}
