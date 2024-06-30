package com.nortic.posdash.ms.chat.config;

import com.nortic.posdash.ms.chat.controller.ChatController;
import com.nortic.posdash.ms.chat.service.MessageService;
import com.nortic.posdash.ms.chat.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final MessageService messageService;
    private final UserServiceImpl userService;

    @Autowired
    public WebSocketConfig(MessageService messageService, UserServiceImpl userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        registry.addHandler(new ChatController(messageService, userService), "/api/v1/ms/chat").setAllowedOrigins("*");
    }
}
