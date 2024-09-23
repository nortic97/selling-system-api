package com.nortic.posdash.ms.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nortic.posdash.models.dto.UserDTO;
import com.nortic.posdash.models.entity.UserEntity;
import com.nortic.posdash.ms.chat.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

@Component
public class ActiveUsersWebSocketHandler extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(ActiveUsersWebSocketHandler.class);

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<UUID, WebSocketSession> sessions = new LinkedHashMap<>();
    private final UserServiceImpl userService;

    @Autowired
    public ActiveUsersWebSocketHandler(UserServiceImpl userService) {
        this.userService = userService;
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        UUID userId = getUserIdFromSession(session);
        UserDTO userEntity = userService.findById(userId);
        if (userEntity == null) {
            log.info("User {} Not Exists", userId);
        } else {
            log.info("User {} connected", userEntity.getUserName());
            sessions.put(userId, session);
            broadcastActiveUsers();
        }
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception {
        UUID userId = getUserIdFromSession(session);
        UserDTO userEntity = userService.findById(userId);
        if (userEntity != null) {
            log.info("User {} disconnected", userEntity.getUserName());
            sessions.remove(userId);
            broadcastActiveUsers();
        }
    }

    private void broadcastActiveUsers() throws Exception {
        // Obtener los IDs de los usuarios conectados de las sesiones activas
        Set<UUID> userIds = sessions.keySet();

        // Realizar la búsqueda por lotes en la base de datos para obtener los usuarios conectados
        List<UserDTO> activeUsers = userService.findByIds(userIds);

        // Convertir la lista de usuarios activos a JSON
        String activeUsersJson = objectMapper.writeValueAsString(activeUsers);

        // Enviar la lista de usuarios activos a todas las sesiones activas
        for (WebSocketSession session : sessions.values()) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(activeUsersJson));
            }
        }
    }

    private UUID getUserIdFromSession(WebSocketSession session) {
        // Obtener el ID del usuario desde la URI de la sesión
        String query = Objects.requireNonNull(session.getUri()).getQuery();
        if (query != null && query.startsWith("userId=")) {
            return UUID.fromString(query.split("=")[1]);
        }
        throw new IllegalArgumentException("No userId found in session URI");
    }
}
