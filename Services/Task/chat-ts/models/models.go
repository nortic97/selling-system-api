package models

import (
	"time"

	"github.com/gorilla/websocket"
)

// MessageType enumera los posibles tipos de mensaje
type MessageType string

const (
	MessageTypeMessage MessageType = "message" // Tipo de mensaje regular
	MessageTypeClient  MessageType = "client"  // Tipo de mensaje de información de cliente
)

// ClientMessage es una estructura que representa un mensaje de tipo "client" que contiene un array de objetos de cliente.
type ClientMessage struct {
	Type    MessageType `json:"type"`    // Tipo de mensaje: "client"
	Content []*Client   `json:"content"` // Contenido del mensaje (array de clientes)
}

// Message es una estructura que representa un mensaje de tipo "message".
type Message struct {
	Type    MessageType    `json:"type"`    // Tipo de mensaje: "message"
	Content MessageContent `json:"content"` // Contenido del mensaje
}

// Client representa a un usuario conectado al chat.
type Client struct {
	Id         string          `json:"Id"`         // ID único del cliente
	Conn       *websocket.Conn `json:"Conn"`       // La conexión websocket del cliente
	ProfilePic string          `json:"ProfilePic"` // URL de la foto de perfil del cliente
	NickName   string          `json:"NickName"`   // Nickname del cliente
}

// MessageContent es una estructura que representa el contenido de un mensaje.
type MessageContent struct {
	From    string    `json:"from"`    // ID del remitente
	To      string    `json:"to"`      // ID del destinatario
	Content string    `json:"content"` // Contenido del mensaje
	SentAt  time.Time `json:"sentAt"`  // Hora de envío del mensaje
}
