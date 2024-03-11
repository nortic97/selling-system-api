package models

import (
	"github.com/gorilla/websocket"
)

// client representa a un usuario conectado al chat.
type Client struct {
	Id   string          // ID único del cliente
	Conn *websocket.Conn // La conexión websocket del cliente
}

// message es la estructura que usaremos para enviar y recibir mensajes JSON.
type Message struct {
	From     string `json:"from"`
	To       string `json:"to"`
	Content  string `json:"content"`
	Nickname string `json:"nickname"`
}
