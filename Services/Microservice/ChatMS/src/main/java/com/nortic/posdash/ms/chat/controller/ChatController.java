package com.nortic.posdash.ms.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nortic.posdash.models.dto.MessageDTO;
import com.nortic.posdash.models.dto.UserDTO;
import com.nortic.posdash.models.entity.UserEntity;
import com.nortic.posdash.ms.chat.service.MessageService;
import com.nortic.posdash.ms.chat.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ms/chat")
public class ChatController extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<UUID, WebSocketSession> sessions = new LinkedHashMap<>();
    private final MessageService messageService;
    private final UserServiceImpl userService;

    @Autowired
    public ChatController(MessageService messageService, UserServiceImpl userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        UUID userId = getUserIdFromSession(session);
        UserEntity userEntity = userService.findById(userId);
        if(userEntity == null){
            log.info("User {} Not Exists", userId);
        }else{
            log.info("User {} connected", userEntity.getUserName());
            sessions.put(userId, session);
        }
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, TextMessage textMessage) throws Exception {
        // Parse the incoming message
        MessageDTO messageDTO = objectMapper.readValue(textMessage.getPayload(), MessageDTO.class);

        UUID senderUserId = getUserIdFromSession(session);
        UUID receiverUserId = messageDTO.getToUserId();

        if (receiverUserId == null || sessions.get(receiverUserId) == null) {
            log.warn("Receiver {} is not connected or does not exist", receiverUserId);
            return;
        }

        // Send the message to the receiver
        WebSocketSession receiverSession = sessions.get(receiverUserId);
        if (receiverSession != null && receiverSession.isOpen()) {
            receiverSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(messageDTO)));
            messageService.sendMessage(messageDTO);
        } else {
            log.warn("Receiver {} session is closed", receiverUserId);
        }
    }



    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session,@NonNull CloseStatus status) throws Exception {
        UUID userId = getUserIdFromSession(session);
        UserEntity userEntity = userService.findById(userId);
        log.info("User {} disconnected", userEntity.getUserName());
        sessions.remove(userId);
    }

    private UUID getUserIdFromSession(WebSocketSession session) {
        return UUID.fromString(Objects.requireNonNull(session.getUri()).getQuery().split("=")[1]);
    }

}
