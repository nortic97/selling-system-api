package utils

import "strings"

// This capability define a MIMEtype in base to extension of a file.
//
// Parameters:
//	extension (string) : Extension of the file.
//	example: ".jpg"
//
// Returns:
//	string: Mimetype of the file.
//	example: "image/jpeg"
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
