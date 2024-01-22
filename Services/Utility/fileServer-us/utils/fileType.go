package utils

import "strings"

func FileType(extension string) string {

	op := strings.ToLower(extension)

	switch op {
	case ".jpg":
		return JPG
	case ".png":
		return PNG
	default:
		return ""
	}

}
