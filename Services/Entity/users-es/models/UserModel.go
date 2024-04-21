package models

import (
	"time"

	"github.com/google/uuid"
)

type User struct {
	ID          *uuid.UUID `gorm:"primary_key;type:uuid"`
	FirstName   *string    `gorm:"not null"`
	LastName    *string    `gorm:"not null"`
	Username    *string    `gorm:"not null;unique"`
	Password    *string    `gorm:"not null"`
	Email       *string    `gorm:"not null;unique"`
	Phone       *string    `gorm:"not null"`
	RoleID      *uuid.UUID `gorm:"type:uuid"`
	Status      *string    `gorm:"not null"`
	Address     *string    `gorm:"not null"`
	DateOfBirth *time.Time `gorm:"not null"`
	Photo       *string    `gorm:"not null"`
	CreatedAt   *time.Time `gorm:"not null;default:now()"`
	DeletedAt   *time.Time `gorm:"default:null"`
	UpdatedAt   *time.Time `gorm:"default:now()"`
}
