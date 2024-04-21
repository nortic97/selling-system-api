package utils

import "golang.org/x/crypto/bcrypt"

// Encrypt password.
//
// Parameters:
//	password (string): The password to encrypt.
//
// Returns:
//	string: The encrypted password.
//	error: possible errors.
func EncodePassword(password *string) (string, error) {
	bytes, err := bcrypt.GenerateFromPassword([]byte(*password), 14)
	return string(bytes), err
}

// check if hash matches.
//
// Parameters:
//	password (string): The password to check.
//
// Returns:
//	bool: true if the password matches.
func CheckPasswordHash(password, hash string) bool {
	err := bcrypt.CompareHashAndPassword([]byte(hash), []byte(password))
	return err == nil
}
