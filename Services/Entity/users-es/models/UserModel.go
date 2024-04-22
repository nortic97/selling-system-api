package models

import (
	"time"

	"github.com/google/uuid"
)

type User struct {
	ID          *uuid.UUID `json:"id" gorm:"primary_key;type:uuid"`
	FirstName   string     `json:"first_name" gorm:"not null;default:null"`
	LastName    string     `json:"last_name" gorm:"not null;default:null"`
	Username    string     `json:"username" gorm:"not null;unique;default:null"`
	Password    string     `json:"password" gorm:"not null;default:null"`
	Email       string     `json:"email" gorm:"not null;unique;default:null"`
	Phone       string     `json:"phone" gorm:"not null;default:null"`
	RoleID      uuid.UUID  `json:"role_id" gorm:"type:uuid"`
	Status      string     `json:"status" gorm:"not null;default:null"`
	Address     string     `json:"address" gorm:"not null;default:null"`
	DateOfBirth time.Time  `json:"date_of_birth" gorm:"not null;default:null"`
	Photo       string     `json:"photo" gorm:"not null;default:null"`
	CreatedAt   time.Time  `json:"created_at" gorm:"not null;default:now()"`
	DeletedAt   time.Time  `json:"deleted_at" gorm:"default:null;index"`
	UpdatedAt   time.Time  `json:"updated_at" gorm:"default:now()"`
}
