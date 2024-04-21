package utils

import "github.com/google/uuid"

// Función de utilidad para convertir un UUID a un puntero a UUID
func UUIDPtr(u uuid.UUID) *uuid.UUID {
	return &u
}

// Función de utilidad para convertir una cadena de texto a un puntero a UUID
func StringToUUIDPtr(s string) *uuid.UUID {
	id, err := uuid.Parse(s)
	if err != nil {
		return nil
	}
	return &id
}

// Función de utilidad para convertir un string a un puntero a string
func StringPtr(s string) *string {
	return &s
}
