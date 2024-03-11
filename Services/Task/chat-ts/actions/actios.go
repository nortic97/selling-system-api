package actions

import (
	"chat-ts/models"
	"encoding/json"
	"log"
	"net/http"

	"github.com/gorilla/websocket"
)

var (
	clients        = make(map[string]*models.Client) // Mapa de clientes conectados (key: id, value: cliente)
	upgrader       = websocket.Upgrader{}            // Upgrader para actualizar una conexión HTTP a WebSocket
	broadcast      = make(chan models.Message)       // Canal para transmitir mensajes a todos los clientes
	register       = make(chan *models.Client)       // Canal para registrar un nuevo cliente
	unregister     = make(chan *models.Client)       // Canal para desregistrar un cliente
	connectedUsers = make(map[string]*websocket.Conn)
)

func HandleWebSocket(w http.ResponseWriter, r *http.Request) {

	// Permitir todas las solicitudes de origen
	upgrader.CheckOrigin = func(r *http.Request) bool { return true }

	conn, err := upgrader.Upgrade(w, r, nil)
	if err != nil {
		log.Println("Error al actualizar la conexión HTTP a WebSocket:", err)
		return
	}

	// Lee el ID y el nickname del usuario del query param
	id := r.URL.Query().Get("id")
	if id == "" {
		log.Println("ID del usuario no especificado.")
		conn.Close()
		return
	}

	connectedUsers[id] = conn

	c := &models.Client{Id: id, Conn: conn}
	register <- c

	// Escuchar mensajes entrantes del cliente
	go func() {
		defer func() {
			unregister <- c
			conn.Close()
		}()
		for {
			var msg models.Message
			err := conn.ReadJSON(&msg)
			if err != nil {
				log.Println("Error al leer el mensaje:", err)
				break
			}
			broadcast <- msg
		}
	}()
}

func StartChat() {
	for {
		select {
		case c := <-register:
			clients[c.Id] = c
			log.Printf("Cliente %s conectado.\n", c.Id)

		case c := <-unregister:
			delete(clients, c.Id)
			log.Printf("Cliente %s desconectado.\n", c.Id)

		case msg := <-broadcast:
			if destClient, ok := clients[msg.To]; ok {
				err := destClient.Conn.WriteJSON(msg)
				if err != nil {
					log.Printf("Error al enviar mensaje a %s: %v\n", destClient.Id, err)
				}
			} else {
				log.Printf("Cliente %s no encontrado.\n", msg.To)
			}
		}
	}
}

// Función para obtener la lista de usuarios conectados
func GetUsersHandler(w http.ResponseWriter, r *http.Request) {
	// Crear una lista de IDs de usuarios conectados a partir del mapa de conexiones WebSocket
	var users []string
	for userID := range connectedUsers {
		users = append(users, userID)
	}

	// Escribir la lista de usuarios como una respuesta HTTP
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(users)
}
