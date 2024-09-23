package actions

import (
	"chat-ts/models"
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
	connectedUsers = make(map[string]*models.Client) // Mapa de usuarios conectados (key: id, value: cliente)
)

func HandleWebSocket(w http.ResponseWriter, r *http.Request) {
	upgrader.CheckOrigin = func(r *http.Request) bool { return true }

	conn, err := upgrader.Upgrade(w, r, nil)
	if err != nil {
		log.Println("Error al actualizar la conexión HTTP a WebSocket:", err)
		return
	}

	id := r.URL.Query().Get("id")
	nickName := r.URL.Query().Get("nickName")
	if id == "" || nickName == "" {
		log.Println("ID o NickName del usuario no especificado.")
		conn.Close()
		return
	}
	profilePic := r.URL.Query().Get("profilePic")

	c := &models.Client{Id: id, Conn: conn, ProfilePic: profilePic, NickName: nickName}
	connectedUsers[id] = c
	register <- c

	defer func() {
		unregister <- c
		conn.Close()
	}()

	for {
		var msg models.MessageContent
		// Crear el mensaje completo
		err := conn.ReadJSON(&msg)

		if err != nil {
			if websocket.IsUnexpectedCloseError(err, websocket.CloseGoingAway) {
				log.Println("Error al leer el mensaje:", err)
			}
			break
		}

		message := models.Message{
			Type:    models.MessageTypeMessage,
			Content: msg,
		}

		broadcast <- message
	}
}

func StartChat() {
	go HandleUserEvents() // Lanzar HandleUserEvents en una goroutine separada
	for {
		select {
		case c := <-register:
			clients[c.Id] = c
			log.Printf("Cliente %s conectado.\n", c.NickName)
			broadcastOnlineUsers()

		case c := <-unregister:
			delete(clients, c.Id)
			log.Printf("Cliente %s desconectado.\n", c.NickName)
			delete(connectedUsers, c.Id)
			broadcastOnlineUsers()

		case msg := <-broadcast:
			// Asignar el tipo de mensaje
			msg.Type = models.MessageTypeMessage

			// Verificar si el destinatario está conectado
			if destClient, ok := clients[msg.Content.To]; ok {
				// Enviar el mensaje al cliente destinatario
				err := destClient.Conn.WriteJSON(msg)
				if err != nil {
					log.Printf("Error al enviar mensaje a %s: %v\n", destClient.NickName, err)
				}
			} else {
				log.Printf("Cliente %s no encontrado.\n", msg.Content.To)
			}
		}
	}
}

func broadcastOnlineUsers() {
	var users []*models.Client
	for _, client := range connectedUsers {
		userDetails := &models.Client{
			Id:         client.Id,
			ProfilePic: client.ProfilePic,
			NickName:   client.NickName,
		}
		users = append(users, userDetails)
	}

	for _, client := range connectedUsers {
		err := client.Conn.WriteJSON(models.ClientMessage{
			Type:    models.MessageTypeClient,
			Content: users,
		})
		if err != nil {
			log.Printf("Error al enviar lista de usuarios a %s: %v\n", client.NickName, err)
		}
	}
}

func HandleUserEvents() {
	for {
		select {
		case c := <-register:
			clients[c.Id] = c
			log.Printf("Cliente %s conectado.\n", c.NickName)
			broadcastOnlineUsers()

		case c := <-unregister:
			delete(clients, c.Id)
			log.Printf("Cliente %s desconectado.\n", c.NickName)
			delete(connectedUsers, c.Id)
			broadcastOnlineUsers()
		}
	}
}
